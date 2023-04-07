package com.example.customocrservice.mapper;

import com.example.customocrservice.model.ocr.UploadedFile;
import com.example.customocrservice.model.response.file.UploadedFileResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UploadedFileMapper {

    UploadedFileResponseDto mapToDto(UploadedFile uploadedFile);
}
