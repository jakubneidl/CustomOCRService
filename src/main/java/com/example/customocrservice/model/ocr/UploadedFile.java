package com.example.customocrservice.model.ocr;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class UploadedFile {
    private UUID id;
    @JsonIgnore
    private MultipartFile multipartFile;
    private LanguageEnum lang;
    private Integer numberOfPages;
    private Set<Page> pages;
}
