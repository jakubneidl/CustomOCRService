package com.example.customocrservice.model.ocr;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SupportedFilesEnum {
    PDF("pdf"),
    JPG("jpg"),
    BMP("bmp"),
    PNG("png");

    private final String fileExtension;
}
