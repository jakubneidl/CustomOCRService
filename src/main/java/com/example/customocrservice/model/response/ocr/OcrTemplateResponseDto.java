package com.example.customocrservice.model.response.ocr;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OcrTemplateResponseDto {
    private List<OcrFieldDto> result;
    private Map<String, String> ocrResult;
}
