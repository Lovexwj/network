package com.example.httplibrary.gsonuiles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Date;

public class MyGson {
    private static GsonBuilder gsonBuilder = new GsonBuilder()
        .registerTypeAdapter(Date.class, new DateTypeAdapter())
        .registerTypeAdapter(ResultCode.class, new ResultCodeTypeAdapter())
        .registerTypeAdapter(Number.class, new NumberTypeAdapter())
        .registerTypeAdapter(Double.class, new DoubleTypeAdapter())
        .registerTypeAdapter(Integer.class, new IntegerTypeAdapter());//.setPrettyPrinting();

    public static Gson get() {
        return gsonBuilder.setLenient().create();
    }

    public static Gson get(String pattern) {
        return gsonBuilder.setDateFormat(pattern).create();
    }

    public static void registerTypeAdapter(Type type, Object typeAdapter) {
        gsonBuilder.registerTypeAdapter(type, typeAdapter);
    }
}
