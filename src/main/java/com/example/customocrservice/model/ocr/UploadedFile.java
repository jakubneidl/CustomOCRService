package com.example.customocrservice.model.ocr;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.util.UUID;


@Getter
@AllArgsConstructor
public class UploadedFile {
    private UUID id;
    private File file;
    private LanguageEnum lang;
}
