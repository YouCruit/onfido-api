package com.youcruit.onfido.api.serialization;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CalendarTypeAdapter implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {

    private static final Pattern DATE_FORMAT = Pattern.compile("(?<year>[0-9]{4})-(?<month>01|02|03|04|05|06|07|08|09|10|11|12)-(?<day>0[1-9]|[12][0-9]|3[0-1])");
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    @Override
    public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
	if (json.isJsonNull()) {
	    return null;
	}
	Matcher matcher = DATE_FORMAT.matcher(json.getAsString());
	if (! matcher.matches()) {
	    throw new JsonParseException("Couldn't get date from " + matcher);
	}
	Calendar.Builder builder = new Calendar.Builder().setTimeZone(UTC);
	builder.setDate(getInt(matcher, "year"), getInt(matcher, "month") - 1, getInt(matcher, "day"));
	return builder.build();
    }

    private int getInt(Matcher matcher, String year) {return Integer.parseInt(matcher.group(year));}

    @Override
    public JsonElement serialize(Calendar src, Type typeOfSrc, JsonSerializationContext context) {
	if (src == null) {
	    return JsonNull.INSTANCE;
	}
	return context.serialize(String.format(Locale.US, "%d-%d-%d", src.get(Calendar.YEAR), src.get(Calendar.MONTH) + 1, src.get(Calendar.DAY_OF_MONTH)));
    }
}
