package com.example.customocrservice.model.response.template;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TemplateResponseDto {
    private UUID id;
    private String name;
    private List<TextLocationsResponseDto> textLocations;
}
