package com.example.customocrservice.model.response.ocr;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OcrFieldDto {
    private String fieldName;
    private String fieldValue;
}
