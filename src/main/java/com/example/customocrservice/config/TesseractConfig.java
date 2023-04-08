package com.example.customocrservice.config;

import com.example.customocrservice.model.ocr.LanguageEnum;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.net.URL;

@Configuration
public class TesseractConfig {

    private static String TESSERACT_TRAINED_DATA_PATH = "src/main/resources/tessdata";

    @Bean
    public Tesseract tesseract() {
        Tesseract tesseract = new Tesseract();
        URL resource = getClass().getResource(TESSERACT_TRAINED_DATA_PATH);
        if (resource != null) {
            tesseract.setDatapath(resource.getPath());
        } else {
            throw new RuntimeException("Failed to find Tesseract trained data on classpath");
        }
        tesseract.setLanguage(LanguageEnum.CES.getLangCode());
        return tesseract;
    }
}
