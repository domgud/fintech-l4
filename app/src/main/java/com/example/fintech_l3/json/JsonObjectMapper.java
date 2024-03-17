package com.example.fintech_l3.json;

import com.example.fintech_l3.exception.UncheckedJsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class JsonObjectMapper {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new ParameterNamesModule())
            .registerModule(new JavaTimeModule()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);

    private JsonObjectMapper() {

    }

    /**
     * @param body      JSON body
     * @param classType Class type to which the deserialization happens
     * @return JSON object which is deserialized into desired class type
     */
    public static <T> T convertToObject(String body, Class<T> classType) {

        try {
            return OBJECT_MAPPER.readValue(body, classType);
        } catch (JsonProcessingException e) {

            throw new UncheckedJsonException(e);
        }
    }
}
