package com.example.customocrservice.service;

import com.example.customocrservice.component.ocr.OcrStrategy;
import com.example.customocrservice.component.utils.FileManagerImpl;
import com.example.customocrservice.exception.UnsupportedFileException;
import com.example.customocrservice.model.ocr.LanguageEnum;
import com.example.customocrservice.model.ocr.Page;
import com.example.customocrservice.model.ocr.UploadedFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OcrService {

    private final Tesseract tesseract;
    private final FileManagerImpl fileManager;
    private final Set<OcrStrategy> ocrStrategies;

    public UploadedFile doOcr(MultipartFile file, LanguageEnum lang) throws TesseractException {
        UUID fileID = UUID.randomUUID();
        UploadedFile uploadedFile = getUploadedDocument(file, fileID, lang);



        String fileExtension = getFileExtension(file.getOriginalFilename());

        OcrStrategy ocrStrategy = getOcrStrategies(fileExtension);
        Set<Page> pages = ocrStrategy.doOCr(file, fileID);
        uploadedFile.setPages(pages);
        uploadedFile.setNumberOfPages(pages.size());
        return uploadedFile;
    }

    private UploadedFile getUploadedDocument(MultipartFile file, UUID fileID, LanguageEnum lang) {
        return UploadedFile.builder()
                .id(fileID)
                .lang(lang)
                .multipartFile(file)
                .build();
    }

    private OcrStrategy getOcrStrategies(String fileExtension) {
        return ocrStrategies
                .stream()
                .filter(ocrStrategy -> ocrStrategy.getSupportedFilesExtensions().contains(fileExtension.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new UnsupportedFileException("Unsupported file " + fileExtension));
    }

    private String getFileExtension(String fileName) {
        return FilenameUtils.getExtension(fileName);
    }

}
