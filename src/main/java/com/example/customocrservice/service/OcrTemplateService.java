package com.example.customocrservice.service;

import com.example.customocrservice.domain.Template;
import com.example.customocrservice.domain.TextLocation;
import com.example.customocrservice.model.Pair;
import com.example.customocrservice.model.ocr.UploadedFile;
import com.example.customocrservice.model.response.ocr.OcrFieldDto;
import com.example.customocrservice.model.response.ocr.OcrTemplateResponseDto;
import com.example.customocrservice.repository.FileRepository;
import com.example.customocrservice.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OcrTemplateService {

    private final Tesseract tesseract;
    private final FileRepository fileRepository;
    private final TemplateRepository templateRepository;

    public OcrTemplateResponseDto ocrTemplate(String templateId, String fileId) {

        UploadedFile uploadedFile = fileRepository.findById(fileId);
        Template template = templateRepository.findById(templateId);

        List<Pair<String, Rectangle>> fieldLocationPairs = template
                .getTextLocations()
                .stream()
                .map(textLocation -> Pair.of(textLocation.getName(), toRectangle(textLocation))).collect(Collectors.toList());


        List<Pair<String, String>> fieldValuePairs = fieldLocationPairs.stream()
                .map(fieldLocation -> getFieldValuePair(uploadedFile, fieldLocation)).collect(Collectors.toList());

        List<OcrFieldDto> pairResults = fieldValuePairs.stream().map(this::mapToDto).collect(Collectors.toList());

        Map<String, String> resultMap = pairResults.stream().collect(Collectors.toMap(OcrFieldDto::getFieldName, OcrFieldDto::getFieldValue));

        OcrTemplateResponseDto ocrTemplateResponseDto = new OcrTemplateResponseDto();
        ocrTemplateResponseDto.setResult(pairResults);
        ocrTemplateResponseDto.setOcrResult(resultMap);
        return ocrTemplateResponseDto;
    }

    private Pair<String, String> getFieldValuePair(UploadedFile uploadedFile,
                                                   Pair<String, Rectangle> fieldLocation) {
        String fieldValue = null;
        try {
            fieldValue = fixNewLines(tesseract.doOCR(uploadedFile.getFile(), fieldLocation.getSecond()));
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }
        return Pair.of(fieldLocation.getFirst(), fieldValue);
    }

    private String fixNewLines(String ocrString) {
        return ocrString.replace("\n", "");
    }

    private OcrFieldDto mapToDto(Pair<String, String> fieldValuePair) {
        return new OcrFieldDto(fieldValuePair.getFirst(), fieldValuePair.getSecond());
    }

    private Rectangle toRectangle(TextLocation textLocation) {
        return new Rectangle(textLocation.getX(), textLocation.getY(),
                             textLocation.getWidth(), textLocation.getHeight());
    }
}
