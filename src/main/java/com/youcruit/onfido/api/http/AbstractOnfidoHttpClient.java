package com.youcruit.onfido.api.http;

import static java.util.Collections.EMPTY_MAP;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.neovisionaries.i18n.CountryCode;
import com.youcruit.onfido.api.common.OnfidoId;
import com.youcruit.onfido.api.serialization.CalendarTypeAdapter;
import com.youcruit.onfido.api.serialization.CountryCodeTypeAdapter;
import com.youcruit.onfido.api.serialization.OnfidoIdTypeAdapter;

@ThreadSafe
public abstract class AbstractOnfidoHttpClient implements OnfidoHttpClient {
    protected final Logger log = Logger.getLogger(getClass());

    public static final Charset UTF8 = Charset.forName("UTF-8");

    private final Gson gson;
    protected final String baseUrl;

    public AbstractOnfidoHttpClient(Gson gson, String baseUrl) {
	this.baseUrl = baseUrl == null ? API_BASE_WITH_VERSION : baseUrl;
	this.gson = gson == null ? createGson() : gson;
    }

    protected static Gson createGson() {
	return new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
		.registerTypeAdapter(OnfidoId.class, new OnfidoIdTypeAdapter())
		.registerTypeAdapter(CountryCode.class, new CountryCodeTypeAdapter())
		.registerTypeAdapter(Calendar.class, new CalendarTypeAdapter())
		.registerTypeAdapter(GregorianCalendar.class, new CalendarTypeAdapter())
		.create();
    }

    @SuppressWarnings("unchecked")
    public URI pathToUri(String... pathSegments) {
	return pathToUri(EMPTY_MAP, pathSegments);
    }

    @Override
    public URI pathToUri(final Map<String, String> queryParameters, String... pathSegments) {
	return pathToUri("", queryParameters, pathSegments);
    }

    public URI pathToUri(String encodedPathSegment, Map<String, String> queryParameters, String... pathSegments) {
	StringBuilder sb = new StringBuilder(baseUrl);
	if (baseUrl.endsWith("/")) {
	    sb.setLength(sb.length() - 1);
	}
	if (!encodedPathSegment.isEmpty()) {
	    sb.append('/').append(encodedPathSegment);
	}
	if (sb.charAt(sb.length() - 1) == '/') {
	    sb.setLength(sb.length() - 1);
	}
	try {
	    for (String pathSegment : pathSegments) {
		sb.append('/').append(URLEncoder.encode(pathSegment, UTF8.name()));
	    }

	    if (queryParameters.size() > 0) {
		boolean first = true;
		for (Map.Entry<String, String> queryParameter : queryParameters.entrySet()) {
		    sb.append(first ? '?' : '&');
		    first = false;
		    sb.append(queryParameter.getKey()).append('=').append(URLEncoder.encode(queryParameter.getValue(), UTF8.name()));
		}
	    }
	    return URI.create(sb.toString());
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	    throw new RuntimeException(e);
	}
    }

    @Override
    public <T> TypeAdapter<T> getAdapter(Class<T> clazz) {
	return gson.getAdapter(clazz);
    }

    public String toJson(final Object src) {
	return gson.toJson(src);
    }

    protected void logRequest(final URI uri, final Method method, final String requestString) {
	if (log.isDebugEnabled()) {
	    StringBuilder sb = new StringBuilder(method.name()).append(" Request for ").append(uri);
	    log.debug(sb);
	    if (log.isTraceEnabled()) {
		sb.append(" : ").append(requestString);
		log.trace(sb);
	    }
	}
    }

    protected void logResponse(final URI uri, final long startTime, final String responseJson) {
	if (log.isDebugEnabled()) {
	    if (log.isTraceEnabled()) {
		log.trace("Response after " + (System.currentTimeMillis() - startTime) + " ms json from " + uri + " : " + responseJson);
	    } else {
		log.debug("Response after " + (System.currentTimeMillis() - startTime) + " ms json from " + uri + " with " + responseJson.length() + " bytes returned");
	    }
	}
    }
}
