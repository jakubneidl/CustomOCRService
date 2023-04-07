package com.example.customocrservice.domain;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Template {
    private UUID id;
    private String name;
    private List<TextLocation> textLocations;
}
