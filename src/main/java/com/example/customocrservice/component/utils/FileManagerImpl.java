package com.example.customocrservice.component.utils;

import com.example.customocrservice.component.utils.FileManager;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.UUID;

@Component
public class FileManagerImpl implements FileManager {

    private static final String IMG_OUTPUT_PATH = "src/main/resources/files/";

    @Override
    public Path saveFile(MultipartFile file, UUID fileId) {
        Path path = Path.of(IMG_OUTPUT_PATH + fileId + "-" + file.getOriginalFilename());
        File newFile = new File(path.toString());
        try (OutputStream os = new FileOutputStream(newFile)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

}
