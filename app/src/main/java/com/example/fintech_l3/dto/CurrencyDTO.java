package com.example.fintech_l3.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * DTO object, other fields provided from the API are not mapped, as they are not needed
 */
public class CurrencyDTO {

    private final String code;
    private final BigDecimal rate;

    @JsonCreator
    public CurrencyDTO(@JsonProperty("code") String code, @JsonProperty("rate") BigDecimal rate) {
        this.code = Objects.requireNonNull(code, "code can not be null!");
        this.rate = Objects.requireNonNull(rate, "rate can not be null!");

    }

    @Override
    public String toString(){//overriding the toString() method
        return code + ": " + rate;
    }
}

