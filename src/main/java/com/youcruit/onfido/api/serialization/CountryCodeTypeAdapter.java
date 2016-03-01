package com.youcruit.onfido.api.serialization;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.neovisionaries.i18n.CountryCode;

public class CountryCodeTypeAdapter implements JsonSerializer<CountryCode>, JsonDeserializer<CountryCode> {

    @Override
    public CountryCode deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
	return CountryCode.valueOf(json.getAsString());
    }

    @Override
    public JsonElement serialize(CountryCode src, Type typeOfSrc, JsonSerializationContext context) {
	return context.serialize(src.getAlpha3());
    }
}
