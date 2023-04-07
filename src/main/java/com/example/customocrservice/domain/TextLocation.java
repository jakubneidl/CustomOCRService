package com.example.customocrservice.domain;

import lombok.Data;

@Data
public class TextLocation {

    private String name;

    private int x;
    private int y;
    private int width;
    private int height;
}
