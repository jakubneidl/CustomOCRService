package com.example.customocrservice.model.response.template;

import lombok.Data;

@Data
public class TextLocationsResponseDto {
    private String name;
    private int x;
    private int y;
    private int width;
    private int height;
}
