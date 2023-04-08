package com.example.customocrservice.model.response.file;

import lombok.Data;

import java.util.UUID;

@Data
public class UploadedFileResponseDto {
    private String name;
    private UUID id;
}
