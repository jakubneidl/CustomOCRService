package com.example.customocrservice.model.invoice;

import com.example.customocrservice.model.ocr.UploadedFile;
import lombok.Data;

import java.util.UUID;

@Data
public class Invoice {
    private UUID id;
    private Contractor contractor;
    private OrderingParty orderingParty;
    private UploadedFile uploadedFile;
}
