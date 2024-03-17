package com.example.fintech_l3.json;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser<T>{
    private final JsonDeserializer<T> jsonDeserializer;

    public JsonParser(Class<T> classType) {
        this.jsonDeserializer = new JsonDeserializer<>(classType);
    }

    public List<T> parseStringAsList(String jsonString) throws IOException{
        List<T> resultList = new ArrayList<>();
            JsonNode rootNode = JsonObjectMapper.OBJECT_MAPPER.readTree(jsonString);
            for (JsonNode currencyNode : rootNode) {
                T currency = jsonDeserializer.deserialize(currencyNode.toString());
                if (currency != null){
                    resultList.add(currency);
                }
            }
        return resultList;
    }
}
