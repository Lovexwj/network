package com.example.httplibrary.gsonuiles;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DoubleTypeAdapter implements JsonDeserializer<Double> {

	@Override
	public Double deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		try {
			return element.getAsDouble();
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}
}