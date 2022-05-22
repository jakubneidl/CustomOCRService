package com.example.customocrservice.component.utils;

import com.example.customocrservice.component.utils.PDFToImageConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.ghost4j.document.DocumentException;
import org.ghost4j.renderer.RendererException;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
@Slf4j
public class PDFToImageConverterImpl implements PDFToImageConverter {

    private static final String IMG_OUTPUT_PATH = "src/main/resources/files/";
    private static final String IMG_FORMAT = "jpeg";

    @Override
    public Set<Path> convertPDFToImage(File file, UUID fileID) throws IOException, RendererException, DocumentException {
        log.info("Begin converting pdf {}", file.getName());

        HashSet<Path> paths = new HashSet<>();
        PDDocument document = PDDocument.load(file);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        String fileName = file.getName();

        for (int page = 0; page < document.getNumberOfPages(); page++) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(
                    page, 300, ImageType.RGB);
            String path = String.format("%s%s-%d.%s", IMG_OUTPUT_PATH, fileName, page + 1, IMG_FORMAT);
            paths.add(Path.of(path));
            ImageIOUtil.writeImage(
                    bim, path, 300);
        }
        document.close();

        return paths;
    }
}
