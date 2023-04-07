package com.example.customocrservice.api;

import com.example.customocrservice.model.request.TemplateRequestDto;
import com.example.customocrservice.model.response.template.TemplateResponseDto;
import com.example.customocrservice.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/templates")
@RequiredArgsConstructor
public class TemplateController {
    private final TemplateService templateService;

    @PostMapping
    public ResponseEntity<TemplateResponseDto> saveTemplate(@RequestBody TemplateRequestDto templateRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(templateService.save(templateRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemplateResponseDto> findTemplate(@PathVariable("id") String id) {
        return ResponseEntity.ok(templateService.findTemplate(id));
    }
}
