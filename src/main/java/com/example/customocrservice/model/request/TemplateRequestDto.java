package com.example.customocrservice.model.request;

import lombok.Data;

import java.util.List;

@Data
public class TemplateRequestDto {
    private String name;
    private List<TextLocationsRequestDto> textLocations;
}
