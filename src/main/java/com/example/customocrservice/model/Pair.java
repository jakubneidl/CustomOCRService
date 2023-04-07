package com.example.customocrservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pair<T, U> {
    private T first;
    private U second;

    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }
}
