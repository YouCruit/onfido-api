package com.youcruit.onfido.api.http;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import javax.annotation.concurrent.ThreadSafe;

import com.google.gson.TypeAdapter;

@ThreadSafe
public interface OnfidoHttpClient {

    String API_BASE_URL = "https://api.onfido.com/";
    String API_BASE_WITH_VERSION = API_BASE_URL + "v1/";

    enum Method {
	PUT, POST, GET, DELETE
    }

    <V> void async(URI uri, Object requestBody, Method method, OnfidoCallback<V> callback, Class<V> responseClass);

    <V> V sync(URI uri, Object requestBody, Method method, Class<V> responseClass) throws IOException;

    URI pathToUri(String... pathSegments);

    URI pathToUri(Map<String, String> queryParameters, String... pathSegments);

    URI pathToUri(String encodedPathSegment, Map<String, String> queryParameters, String... pathSegments);

    <T> TypeAdapter<T> getAdapter(Class<T> clazz);
}
