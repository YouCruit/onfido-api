package com.youcruit.onfido.api.serialization;

import java.lang.reflect.Type;
import java.util.Locale;

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
	if (json.isJsonNull()) {
	    return null;
	}
	return CountryCode.getByCode(json.getAsString().toUpperCase(Locale.US));
    }

    @Override
    public JsonElement serialize(CountryCode src, Type typeOfSrc, JsonSerializationContext context) {
	return context.serialize(src.getAlpha3());
    }
}
