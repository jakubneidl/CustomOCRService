package com.example.customocrservice.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.UUID;

public interface FileManager {

    File multipartFileToFile(MultipartFile file, String fileName);
}
