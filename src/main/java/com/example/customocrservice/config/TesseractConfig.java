package com.example.customocrservice.config;

import com.example.customocrservice.model.ocr.LanguageEnum;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

@Configuration
public class TesseractConfig {

    private static String TESSERACT_TRAINED_DATA_PATH = "tessdata";

    @Bean
    public Tesseract tesseract() {
        Tesseract tesseract = new Tesseract();
        try {
            File tessDataFolder = new File(getClass().getClassLoader().getResource(TESSERACT_TRAINED_DATA_PATH).toURI());
            if (tessDataFolder.exists()) {
                tesseract.setDatapath(tessDataFolder.getAbsolutePath());
            } else {
                throw new RuntimeException("Failed to find Tesseract trained data on classpath");
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to convert URL to URI for Tesseract trained data", e);
        }
        tesseract.setLanguage(LanguageEnum.CES.getLangCode());
        return tesseract;
    }
}
