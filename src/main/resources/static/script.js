const inputFile = document.getElementById('inputFile');
const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');
const templateName = document.getElementById('templateName');
const uploadButton = document.getElementById('uploadButton');
const submitButton = document.getElementById('submitButton');
const showTemplatesButton = document.getElementById('showTemplatesButton');
const showFilesButton = document.getElementById('showFilesButton');
const templateDropdown = document.getElementById('templateDropdown');
const fileCheckboxes = document.getElementById('fileCheckboxes');
const processOcrButton = document.getElementById('processOcrButton');
const resultList = document.getElementById('resultList');
const rectangleList = document.getElementById('rectangleList');

let img = new Image();
let isDrawing = false;
let startX;
let startY;
let rectangles = [];

inputFile.addEventListener('change', () => {
    const file = inputFile.files[0];
    const reader = new FileReader();

    reader.onload = (e) => {
        img.src = e.target.result;
        img.onload = () => {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.drawImage(img, 0, 0);
        };
    };

    reader.readAsDataURL(file);
});

canvas.addEventListener('mousedown', (e) => {
    startX = e.clientX - canvas.offsetLeft;
    startY = e.clientY - canvas.offsetTop;
    isDrawing = true;
});

canvas.addEventListener('mousemove', (e) => {
    if (!isDrawing) return;
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.drawImage(img, 0, 0);

    const endX = e.clientX - canvas.offsetLeft;
    const endY = e.clientY - canvas.offsetTop;

    rectangles.forEach((rect) => {
        drawRectangle(rect);
    });

    drawRectangle({
        name: '',
        x: startX,
        y: startY,
        width: endX - startX,
        height: endY - startY,
    });
});

canvas.addEventListener('mouseup', (e) => {
    if (!isDrawing) return;

    const endX = e.clientX - canvas.offsetLeft;
    const endY = e.clientY - canvas.offsetTop;

    const name = prompt('Enter a name for this rectangle:');
    const newRectangle = {
        name: name,
        x: startX,
        y: startY,
        width: endX - startX,
        height: endY - startY,
    };

    rectangles.push(newRectangle);

    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.drawImage(img, 0, 0);

    rectangles.forEach((rect) => {
        drawRectangle(rect);
    });

    displayRectangleList();
    isDrawing = false;
});

function drawRectangle(rect) {
    const { x, y, width, height, name } = rect;
    ctx.beginPath();
    ctx.rect(x, y, width, height);
    ctx.strokeStyle = 'red';
    ctx.lineWidth = 2;
    ctx.stroke();

    // Draw label
    ctx.font = '14px Arial';
    ctx.fillStyle = 'blue';
    const textWidth = ctx.measureText(name).width;
    const textX = x + width / 2 - textWidth / 2;
    const textY = y + height / 2 + 7;
    ctx.fillText(name, textX, textY);
}

function displayRectangleList() {
    rectangleList.innerHTML = '';

    rectangles.forEach((rect, index) => {
        const listItem = document.createElement('li');
        listItem.textContent = `${rect.name}: x=${rect.x}, y=${rect.y}, width=${rect.width}, height=${rect.height}`;

        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.addEventListener('click', () => {
            rectangles.splice(index, 1);
            displayRectangleList();
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(img, 0, 0);

            rectangles.forEach((rect) => {
                drawRectangle(rect.x, rect.y, rect.width, rect.height);
            });
        });

        listItem.appendChild(deleteButton);
        rectangleList.appendChild(listItem);
    });
}

async function submitTemplate() {
    const name = templateName.value;

    if (!name || rectangles.length === 0) {
        alert('Please enter a template name and draw at least one rectangle');
        return;
    }

// ...

    const response = await fetch('/api/v1/templates', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            name: name,
            textLocations: rectangles,
        }),
    });

    if (!response.ok) {
        alert('Error: Template submission failed');
    }
}

async function uploadImage() {
    const file = inputFile.files[0];
    const formData = new FormData();
    formData.append('file', file);
    formData.append('lang', 'CES');

    try {
        const response = await fetch('/api/v1/files', {
            method: 'POST',
            body: formData,
        });

        if (!response.ok) {
            throw new Error('Upload failed');
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

async function fetchTemplates() {
    const response = await fetch('/api/v1/templates');
    const templates = await response.json();
    displayTemplates(templates);
}

async function fetchFiles() {
    const response = await fetch('/api/v1/files');
    const files = await response.json();
    displayFiles(files);
}

function displayTemplates(templates) {
    templateDropdown.innerHTML = '';

    templates.forEach(template => {
        const option = document.createElement('option');
        option.value = template.id;
        option.textContent = template.name;
        templateDropdown.appendChild(option);
    });
}

function displayFiles(files) {
    fileCheckboxes.innerHTML = '';

    files.forEach(file => {
        const label = document.createElement('label');
        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.value = file.id;
        label.appendChild(checkbox);
        label.appendChild(document.createTextNode(file.name));
        fileCheckboxes.appendChild(label);
        fileCheckboxes.appendChild(document.createElement('br'));
    });
}

async function processOcr(templateId, fileId) {
    try {
        const response = await fetch(`/api/v1/ocrs?templateId=${templateId}&fileId=${fileId}`, {
            method: 'POST'
        });

        if (!response.ok) {
            throw new Error('OCR processing failed');
        }

        const result = await response.json();
        return result;
    } catch (error) {
        console.error('Error:', error);
    }
}

processOcrButton.addEventListener('click', async () => {
    const templateId = templateDropdown.value;
    const fileCheckboxes = document.querySelectorAll('#fileCheckboxes input[type="checkbox"]:checked');

    if (!templateId || fileCheckboxes.length === 0) {
        alert('Please select a template and at least one file');
        return;
    }

    resultList.innerHTML = '';

    for (const checkbox of fileCheckboxes) {
        const fileId = checkbox.value;
        const result = await processOcr(templateId, fileId);
        const listItem = document.createElement('li');
        listItem.textContent = JSON.stringify(result);
        resultList.appendChild(listItem);
    }
});

// Automatically fetch templates and files when a new one is added
async function afterSubmit() {
    await fetchTemplates();
    await fetchFiles();
}

submitButton.addEventListener('click', async () => {
    await submitTemplate();
    afterSubmit();
});

uploadButton.addEventListener('click', async () => {
    await uploadImage();
    afterSubmit();
});

// Initialize templates and files
afterSubmit();
