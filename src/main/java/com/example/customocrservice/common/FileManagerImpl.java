package com.example.customocrservice.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Component
@Slf4j
public class FileManagerImpl implements FileManager {

    private static final String IMG_OUTPUT_PATH = "src/main/resources/files";

    private static final String DEFAULT_DATA_DIR = "data";

    public File multipartFileToFile(MultipartFile multipartFile, String fileName) {
        Path dataDirPath = null;
        try {
            dataDirPath = Files.createTempDirectory(DEFAULT_DATA_DIR);
            log.info("Creating temp directory: {}", dataDirPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("Temporary directory: {}", dataDirPath);


        File convFile = new File(dataDirPath + File.separator + fileName);

        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(multipartFile.getBytes());
        } catch (Exception e) {
            log.error("Cannot save file {}", e.getLocalizedMessage());
            throw new RuntimeException(e);
        }

        log.info("File converted to: {}", convFile.getAbsolutePath());
        return convFile;
    }
}
