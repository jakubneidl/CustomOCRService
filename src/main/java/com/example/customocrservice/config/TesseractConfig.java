package com.example.customocrservice.config;

import com.example.customocrservice.model.ocr.LanguageEnum;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;

@Configuration
public class TesseractConfig {

    @Bean
    public Tesseract tesseract() throws IOException {
        Tesseract tesseract = new Tesseract();

        // Get the path to the tessdata directory
        ClassPathResource tessDataResource = new ClassPathResource("tessdata");
        String tessDataPath = tessDataResource.getURI().toString();

        // Adjust the path format if running on Windows
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            tessDataPath = tessDataPath.substring(6);
        } else {
            tessDataPath = Paths.get(tessDataPath).toString();
        }

        // Set the datapath and language options for Tesseract
        tesseract.setDatapath(tessDataPath);
        tesseract.setLanguage("ces");
        tesseract.setOcrEngineMode(1);

        return tesseract;

    }
}
