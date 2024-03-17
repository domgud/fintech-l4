package com.example.fintech_l3.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.UncheckedIOException;

public class UncheckedJsonException extends UncheckedIOException {

    public UncheckedJsonException(JsonProcessingException cause) {

        super(cause);
    }

}