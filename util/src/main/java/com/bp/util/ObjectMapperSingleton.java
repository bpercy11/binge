package com.bp.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

public class ObjectMapperSingleton {
    private static ObjectMapper objectMapper;

    private ObjectMapperSingleton(){
        objectMapper = new ObjectMapper();
    }

    public static ObjectMapper getInstance(){
        return Objects.requireNonNullElseGet(objectMapper, ObjectMapper::new);
    }
}
