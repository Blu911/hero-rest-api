package com.blu911.herorestapi;

import com.google.gson.Gson;

import java.util.List;

public class TestUtils {
    private static Gson gson = new Gson();

    @SuppressWarnings("rawtypes")
    public static List jsonToList(String json, Class<List> classOf) {
        return gson.fromJson(json, classOf);
    }

    public static String objectToJason(Object object) {
        return gson.toJson(object);
    }

    public static <T> T jsonToObject(String json, Class<T> classOf) {
        return gson.fromJson(json, classOf);
    }

}
