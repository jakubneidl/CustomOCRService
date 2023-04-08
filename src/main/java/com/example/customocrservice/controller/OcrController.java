package com.example.customocrservice.controller;

import com.example.customocrservice.model.response.ocr.OcrTemplateResponseDto;
import com.example.customocrservice.service.OcrTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ocrs")
@RequiredArgsConstructor
public class OcrController {

    private final OcrTemplateService ocrTemplateService;

    @PostMapping
    public ResponseEntity<OcrTemplateResponseDto> ocrTemplate(@RequestParam("templateId") String templateId,
                                                              @RequestParam("fileId") String fileId) {
        return ResponseEntity.ok(ocrTemplateService.ocrTemplate(templateId, fileId));
    }
}
