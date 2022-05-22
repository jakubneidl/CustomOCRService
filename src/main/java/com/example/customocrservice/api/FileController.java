package com.example.customocrservice.api;

import com.example.customocrservice.model.ocr.LanguageEnum;
import com.example.customocrservice.model.ocr.UploadedFile;
import com.example.customocrservice.service.OcrService;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final OcrService ocrService;

    @PostMapping("/upload")
    public UploadedFile doUcrOnFile(@RequestParam("file") MultipartFile file, @RequestParam("lang") LanguageEnum lang) {
        try {
            return ocrService.doOcr(file, lang);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return null;
    }


}
