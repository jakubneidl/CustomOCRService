package com.example.customocrservice.component.ocr;

import com.example.customocrservice.component.utils.FileManager;
import com.example.customocrservice.component.utils.PDFToImageConverter;
import com.example.customocrservice.model.ocr.SupportedFilesEnum;
import com.example.customocrservice.model.ocr.Page;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.ghost4j.document.DocumentException;
import org.ghost4j.renderer.RendererException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OcrPDF implements OcrStrategy {

    private static final Set<SupportedFilesEnum> SUPPORTED_FILES = Set.of(SupportedFilesEnum.PDF);

    private final Tesseract tesseract;
    private final FileManager fileManager;
    private final PDFToImageConverter pdfToImageConverter;

    @Override
    public Set<Page> doOCr(MultipartFile file, UUID fileID) throws TesseractException {
        Path pathToOriginalFile = fileManager.saveFile(file, fileID);
        Set<Path> pathToImages = null;
        try {
            pathToImages = pdfToImageConverter.convertPDFToImage(new File(pathToOriginalFile.toString()), fileID);
        } catch (IOException | RendererException | DocumentException e) {
            e.printStackTrace();
        }

        assert pathToImages != null;

        Set<Page> pages = new HashSet<>();
        int pageNumber = 1;
        for (Path path : pathToImages) {
            Page page = new Page();
            page.setPage(pageNumber++);
            page.setContent(tesseract.doOCR(new File(path.toString())));
            pages.add(page);
        }

        return pages;
    }

    @Override
    public Set<String> getSupportedFilesExtensions() {
        return SUPPORTED_FILES
                .stream()
                .map(SupportedFilesEnum::getFileExtension)
                .collect(Collectors.toSet());
    }

}
