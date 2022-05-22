package com.example.customocrservice.model.ocr;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LanguageEnum {

    CES("cestina", "ces"),
    ENG("english", "eng");

    private final String language;
    private final String langCode;
}
