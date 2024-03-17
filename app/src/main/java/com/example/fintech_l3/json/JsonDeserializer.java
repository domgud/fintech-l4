package com.example.fintech_l3.json;

import android.util.Log;

public class JsonDeserializer<T> {
    private static final String TAG = "JsonDeserializer";
    private final Class<T> classType;
    public JsonDeserializer(Class<T> classType) {

        this.classType = classType;
    }

    /**
     * @param data json data
     * @return deserialized object, depending on the class type
     */
    public T deserialize(String data) {

        try {
            return JsonObjectMapper.convertToObject(data, classType);
        } catch (Exception e) {
            Log.e(TAG, "Failed to deserialize " + data);
            return null;
        }
    }
}
