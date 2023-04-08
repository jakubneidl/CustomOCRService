package com.example.customocrservice.controller;

import com.example.customocrservice.model.ocr.LanguageEnum;
import com.example.customocrservice.model.response.file.UploadedFileResponseDto;
import com.example.customocrservice.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/files")
public class FileController {
    private final FileService fileService;

    @PostMapping
    public ResponseEntity<UploadedFileResponseDto> uploadedFile(@RequestParam("file") MultipartFile file,
                                                                @RequestParam("lang") LanguageEnum lang) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(fileService.save(file, lang));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UploadedFileResponseDto> findFileById(@PathVariable("id") String id) {
        return ResponseEntity.ok(fileService.findFileById(id));
    }

    @GetMapping
    public ResponseEntity<List<UploadedFileResponseDto>> findAllFiles() {
        return ResponseEntity.ok(fileService.findAll());
    }

}
