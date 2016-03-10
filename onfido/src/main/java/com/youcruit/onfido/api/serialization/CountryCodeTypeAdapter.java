package com.youcruit.onfido.api.serialization;

import java.io.IOException;
import java.util.Locale;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.neovisionaries.i18n.CountryCode;

public class CountryCodeTypeAdapter extends TypeAdapter<CountryCode> {

    @Override
    public void write(final JsonWriter out, final CountryCode value) throws IOException {
	if (value == null) {
	    out.nullValue();
	    return;
	}
	out.value(value.getAlpha3());
    }

    @Override
    public CountryCode read(final JsonReader in) throws IOException {
	if (in.peek() == JsonToken.NULL) {
	    in.nextNull();
	    return null;
	}
	return CountryCode.getByCode(in.nextString().toUpperCase(Locale.US));
    }
}
