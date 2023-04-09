package com.example.customocrservice.config;

import com.example.customocrservice.model.ocr.LanguageEnum;
import lombok.SneakyThrows;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
public class TesseractConfig {

    private static String TESSERACT_TRAINED_DATA_PATH = "tessdata";

    @SneakyThrows
    @Bean
    public Tesseract tesseract() {
        Tesseract tesseract = new Tesseract();

        Resource tessDataResource = new ClassPathResource("tessdata");
        Path tempTessDataFolder = Files.createTempDirectory("tessdata");
        FileCopyUtils.copy(tessDataResource.getFile(), tempTessDataFolder.toFile());

        tesseract.setDatapath(tempTessDataFolder.toString());
        tesseract.setLanguage("eng");
        return tesseract;
    }
}
