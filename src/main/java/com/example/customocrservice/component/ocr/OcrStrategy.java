package com.example.customocrservice.component.ocr;

import com.example.customocrservice.model.ocr.Page;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

public interface OcrStrategy {

    Set<Page> doOCr(MultipartFile file, UUID fileID) throws TesseractException;

    Set<String> getSupportedFilesExtensions();
}
