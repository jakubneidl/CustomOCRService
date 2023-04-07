package com.example.customocrservice.component.ocr;

import com.example.customocrservice.common.FileManager;
import com.example.customocrservice.model.ocr.Page;
import com.example.customocrservice.model.ocr.SupportedFilesEnum;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OcrIMG implements OcrStrategy {

    private static final Set<SupportedFilesEnum> SUPPORTED_FILES = Set.of(SupportedFilesEnum.PNG, SupportedFilesEnum.JPG, SupportedFilesEnum.BMP);

    private final Tesseract tesseract;
    private final FileManager fileManager;

    @Override
    public Set<Page> doOCr(MultipartFile file, UUID fileID) throws TesseractException {
        Path path = fileManager.saveFile(file, fileID);
        Page page = new Page();
        page.setContent(tesseract.doOCR(new File(path.toString())));
        return Set.of(page);
    }

    @Override
    public Set<String> getSupportedFilesExtensions() {
        return SUPPORTED_FILES
                .stream()
                .map(SupportedFilesEnum::getFileExtension)
                .collect(Collectors.toSet());
    }
}
