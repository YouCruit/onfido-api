package com.youcruit.onfido.api.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class FakeHttpClient extends AbstractOnfidoHttpClient<URI> {

    public final List<Map.Entry<URI, String>> responses = new ArrayList<>();
    public int currentPos = 0;

    public FakeHttpClient() {
	super(null, null);
    }

    private String getData(final URI uri) {
	assertTrue("No more responses for request: " + uri, currentPos < responses.size());
	Map.Entry<URI, String> next = responses.get(currentPos);
	assertEquals(next.getKey(), uri);
	currentPos++;
	return next.getValue();
    }

    public void addResponse(Req req, String response) {
	URI uri = pathToUri(req.encodedPathSegment, req.queryParameters, req.pathSegments.toArray(new String[0]));
	addResponse(uri, response);
    }

    public void addResponse(final URI uri, final String response) {responses.add(new AbstractMap.SimpleEntry<>(uri, response));}

    public void addResponseFromResource(URI uri, String resource) throws IOException {
	String response = IOUtils.toString(getClass().getResourceAsStream(resource));
	addResponse(uri, response);
    }

    public void addResponseFromResource(Req req, String resource) throws IOException {
	InputStream resourceAsStream = getClass().getResourceAsStream(resource);
	if (resourceAsStream == null) {
	    throw new IOException("failed to find " + resource);
	}
	String response = IOUtils.toString(resourceAsStream);
	addResponse(req, response);
    }


    public void assertAtEnd() {
	assertEquals(responses.size(), currentPos);
    }

    @Override
    public <V> void async(final URI uri, final Object requestBody, final Method method, final OnfidoCallback<V> callback, final Class<V> responseClass) {
	throw new UnsupportedOperationException();
    }

    @Override
    public <V> V sync(final URI uri, final Object requestBody, final Method method, final Class<V> responseClass) throws IOException {
	return toResponse(uri, responseClass, System.currentTimeMillis(), uri, true, 200);
    }

    @Override
    protected String getString(URI response) throws IOException {
	return getData(response);
    }

    @Override
    protected byte[] getBytes(URI response) throws IOException {
	return getData(response).getBytes(UTF8);
    }
}
