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
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
public class TesseractConfig {

    private static String TESSERACT_TRAINED_DATA_PATH = "tessdata";

    @Bean
    public Tesseract tesseract() throws IOException, URISyntaxException {
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("ces");

        // Load tessdata folder from classpath
        URL tessDataURL = getClass().getClassLoader().getResource(TESSERACT_TRAINED_DATA_PATH);
        if (tessDataURL != null) {
            File tessDataFolder = new File(tessDataURL.toURI());
            tesseract.setDatapath(tessDataFolder.getParent());
        } else {
            throw new IOException("Unable to find tessdata folder in classpath");
        }

        return tesseract;
    }
}
