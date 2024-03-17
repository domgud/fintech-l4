package com.example.fintech_l3.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.UncheckedIOException;

/**
 * Exception used to rethrow a JsonProcessingException without handling it
 */
public class UncheckedJsonException extends UncheckedIOException {

    public UncheckedJsonException(JsonProcessingException cause) {

        super(cause);
    }

}