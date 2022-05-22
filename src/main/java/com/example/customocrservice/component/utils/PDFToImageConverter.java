package com.example.customocrservice.component.utils;

import org.ghost4j.document.DocumentException;
import org.ghost4j.renderer.RendererException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.UUID;

public interface PDFToImageConverter {
    Set<Path> convertPDFToImage(File file, UUID fileId) throws IOException, RendererException, DocumentException;
}

