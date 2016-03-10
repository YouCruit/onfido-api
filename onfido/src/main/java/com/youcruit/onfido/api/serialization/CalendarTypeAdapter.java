package com.youcruit.onfido.api.serialization;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class CalendarTypeAdapter extends TypeAdapter<Calendar> {

    private static final Pattern DATE_FORMAT = Pattern.compile("(?<year>[0-9]{4})-(?<month>01|02|03|04|05|06|07|08|09|10|11|12)-(?<day>0[1-9]|[12][0-9]|3[0-1])");
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    private int getInt(Matcher matcher, String year) {return Integer.parseInt(matcher.group(year));}

    @Override
    public void write(JsonWriter out, Calendar value) throws IOException {
	if (value == null) {
	    out.nullValue();
	    return;
	}
	out.value(String.format(Locale.US, "%02d-%02d-%02d", value.get(Calendar.YEAR), value.get(Calendar.MONTH) + 1, value.get(Calendar.DAY_OF_MONTH)));
    }

    @Override
    public Calendar read(JsonReader in) throws IOException {
	if (in.peek() == JsonToken.NULL) {
	    in.nextNull();
	    return null;
	}
	Matcher matcher = DATE_FORMAT.matcher(in.nextString());
	if (! matcher.matches()) {
	    throw new JsonParseException("Couldn't get date from " + matcher);
	}
	Calendar.Builder builder = new Calendar.Builder().setTimeZone(UTC);
	builder.setDate(getInt(matcher, "year"), getInt(matcher, "month") - 1, getInt(matcher, "day"));
	return builder.build();
    }
}
