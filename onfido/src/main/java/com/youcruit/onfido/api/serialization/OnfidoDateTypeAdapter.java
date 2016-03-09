package com.youcruit.onfido.api.serialization;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class OnfidoDateTypeAdapter extends TypeAdapter<Date> {
    private final static Pattern ONFIDO_DATE_STYLE = Pattern.compile("([0-9-]+)[ T]([0-9:]+) ?UTC");

    private final DateFormat enUsFormat
	    = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.US);
    private final DateFormat localFormat
	    = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT);

    @Override
    public Date read(JsonReader in) throws IOException {
	if (in.peek() == JsonToken.NULL) {
	    in.nextNull();
	    return null;
	}
	return deserializeToDate(in.nextString());
    }

    private synchronized Date deserializeToDate(String json) {
	try {
	    return localFormat.parse(json);
	} catch (ParseException ignored) {
	}
	try {
	    return enUsFormat.parse(json);
	} catch (ParseException ignored) {
	}
	try {
	    Matcher matcher = ONFIDO_DATE_STYLE.matcher(json);
	    if (matcher.matches()) {
		String fixedDate = matcher.group(1) + "T" + matcher.group(2) + "Z";
		return ISO8601Utils.parse(fixedDate, new ParsePosition(0));
	    }
	    return ISO8601Utils.parse(json, new ParsePosition(0));
	} catch (ParseException e) {
	    throw new JsonSyntaxException(json, e);
	}
    }

    @Override
    public synchronized void write(JsonWriter out, Date value) throws IOException {
	if (value == null) {
	    out.nullValue();
	    return;
	}
	String dateFormatAsString = enUsFormat.format(value);
	out.value(dateFormatAsString);
    }
}
