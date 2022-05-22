package com.example.customocrservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContoller {

    @GetMapping
    public String[] testString() {

        String str = "POTVRZENÍ\no zdanitelných příjmech ze závislé činnosti,\\nsražených zálohách na daň z těchto příjmů a daňovém zvýhodnění ?\\npodle zákona č. 586/1992 Sb., o ";

        String s = str.replaceAll("\\n", "\n");
        String[] split = s.split("\r?\n|\r");
        return split;
    }
}
