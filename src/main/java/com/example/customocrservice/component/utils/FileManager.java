package com.example.customocrservice.component.utils;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.UUID;

public interface FileManager {

    Path saveFile(MultipartFile file, UUID fileID);
}
