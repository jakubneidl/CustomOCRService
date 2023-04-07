package com.example.customocrservice.repository;

import com.example.customocrservice.model.ocr.UploadedFile;

import java.util.List;

public interface FileRepository {
    UploadedFile save(UploadedFile uploadedFile);
    UploadedFile findById(String id);

    List<UploadedFile> findAll();
}
