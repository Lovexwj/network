package com.example.httplibrary.gsonuiles;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class IntegerTypeAdapter implements JsonDeserializer<Integer> {

	@Override
	public Integer deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		try {
			return element.getAsInt();
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}