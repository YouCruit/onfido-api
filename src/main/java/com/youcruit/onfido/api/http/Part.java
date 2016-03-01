package com.youcruit.onfido.api.http;

import java.nio.charset.Charset;

public class Part {
    private final String contentType;
    private final byte[] data;
    private final String name;

    public Part(final String name, final String contentType, final byte[] data) {
	this.contentType = contentType;
	this.data = data;
	this.name = name;
    }

    public Part(final String name, String data) {
	this.contentType = null;
	this.data = data.getBytes(Charset.forName("UTF-8"));
	this.name = name;
    }

    public String getContentType() {
	return contentType;
    }

    public byte[] getData() {
	return data;
    }

    public String getName() {
	return name;
    }
}
