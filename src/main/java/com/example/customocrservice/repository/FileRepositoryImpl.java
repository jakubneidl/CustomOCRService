package com.example.customocrservice.repository;

import com.example.customocrservice.model.ocr.UploadedFile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class FileRepositoryImpl implements FileRepository {

    private final HashMap<UUID, UploadedFile> fileHashMap = new HashMap<>();

    @Override
    public UploadedFile save(UploadedFile uploadedFile) {
        fileHashMap.put(uploadedFile.getId(), uploadedFile);
        return fileHashMap.get(uploadedFile.getId());
    }

    @Override
    public UploadedFile findById(String id) {
        return fileHashMap.get(UUID.fromString(id));
    }

    @Override
    public List<UploadedFile> findAll() {
        return new ArrayList<>(fileHashMap.values());
    }
}
