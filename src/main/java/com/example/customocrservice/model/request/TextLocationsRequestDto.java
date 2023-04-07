package com.example.customocrservice.model.request;

import lombok.Data;

@Data
public class TextLocationsRequestDto {
    private String name;
    private int x;
    private int y;
    private int width;
    private int height;
}
