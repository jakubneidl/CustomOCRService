package com.example.customocrservice.service;

import com.example.customocrservice.common.FileManager;
import com.example.customocrservice.mapper.UploadedFileMapper;
import com.example.customocrservice.model.ocr.LanguageEnum;
import com.example.customocrservice.model.ocr.UploadedFile;
import com.example.customocrservice.model.response.file.UploadedFileResponseDto;
import com.example.customocrservice.repository.FileRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepositoryImpl fileRepository;

    private final UploadedFileMapper uploadedFileMapper;
    private final FileManager fileManager;

    public UploadedFileResponseDto save(MultipartFile file, LanguageEnum lang) {
        UUID fileId = UUID.randomUUID();
        Path path = fileManager.saveFile(file, fileId);

        UploadedFile uploadedFile = new UploadedFile(fileId, path.toFile(), lang);
        fileRepository.save(uploadedFile);

        return uploadedFileMapper.mapToDto(uploadedFile);
    }

    public UploadedFileResponseDto findFileById(String fileId) {
        return uploadedFileMapper.mapToDto(fileRepository.findById(fileId));
    }

    public List<UploadedFileResponseDto> findAll() {
        return fileRepository
                .findAll()
                .stream()
                .map(uploadedFileMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
