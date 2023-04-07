package com.example.customocrservice.model.response.ocr;

import lombok.Data;

import java.util.List;

@Data
public class OcrTemplateResponseDto {
    List<OcrFieldDto> result;
}
