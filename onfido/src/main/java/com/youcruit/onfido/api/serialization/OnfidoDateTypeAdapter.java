package com.youcruit.onfido.api.serialization;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class OnfidoDateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
	if (json.isJsonNull()) {
	    return null;
	}
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.US);
	try {
	    return simpleDateFormat.parse(json.getAsString());
	} catch (ParseException e) {
	    throw new JsonParseException(e);
	}
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
	if (src == null) {
	    return JsonNull.INSTANCE;
	}
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.US);
	simpleDateFormat.setTimeZone(UTC);
	return context.serialize(simpleDateFormat.format(simpleDateFormat));
    }
}
