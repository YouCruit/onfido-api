package com.youcruit.onfido.api.serialization;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.youcruit.onfido.api.common.OnfidoId;

public class OnfidoIdTypeAdapter implements JsonSerializer<OnfidoId>, JsonDeserializer<OnfidoId> {

    @SuppressWarnings("unchecked")
    @Override
    public OnfidoId deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
	if (json.isJsonNull()) {
	    return null;
	}
	try {
	    return ((Class<OnfidoId>) typeOfT).getConstructor(String.class).newInstance(json.getAsString());
	} catch (Exception e) {
	    throw new JsonParseException(e);
	}
    }

    @Override
    public JsonElement serialize(OnfidoId src, Type typeOfSrc, JsonSerializationContext context) {
	if (src == null) {
	    return JsonNull.INSTANCE;
	}
	return context.serialize(src.getId());
    }
}
