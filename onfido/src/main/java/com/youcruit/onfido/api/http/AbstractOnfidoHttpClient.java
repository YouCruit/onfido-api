package com.youcruit.onfido.api.http;

import static java.util.Collections.EMPTY_MAP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.neovisionaries.i18n.CountryCode;
import com.youcruit.onfido.api.applicants.ApplicantId;
import com.youcruit.onfido.api.checks.CheckId;
import com.youcruit.onfido.api.common.OnfidoId;
import com.youcruit.onfido.api.documents.DocumentId;
import com.youcruit.onfido.api.http.exception.ApiError;
import com.youcruit.onfido.api.http.exception.ApiErrorResponse;
import com.youcruit.onfido.api.http.exception.ApiException;
import com.youcruit.onfido.api.report.ReportId;
import com.youcruit.onfido.api.serialization.CalendarTypeAdapter;
import com.youcruit.onfido.api.serialization.CountryCodeTypeAdapter;
import com.youcruit.onfido.api.serialization.OnfidoDateTypeAdapter;
import com.youcruit.onfido.api.serialization.OnfidoIdTypeAdapter;
import com.youcruit.onfido.api.webhook.WebhookId;

@ThreadSafe
public abstract class AbstractOnfidoHttpClient<R> implements OnfidoHttpClient {
    protected final Logger log = Logger.getLogger(getClass());

    public static final Charset UTF8 = Charset.forName("UTF-8");

    private final Gson gson;
    protected final String baseUrl;

    public AbstractOnfidoHttpClient(Gson gson, String baseUrl) {
	this.baseUrl = baseUrl == null ? API_BASE_WITH_VERSION : baseUrl;
	this.gson = gson == null ? createGson() : gson;
    }

    protected static Gson createGson() {
	CountryCodeTypeAdapter countryCodeTypeAdapter = new CountryCodeTypeAdapter();
	GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
	for (CountryCode countryCode : CountryCode.values()) {
	    gsonBuilder.registerTypeAdapter(countryCode.getClass(), countryCodeTypeAdapter);
	}
	return gsonBuilder
		.registerTypeAdapter(OnfidoId.class, new OnfidoIdTypeAdapter())
		.registerTypeAdapter(ReportId.class, new OnfidoIdTypeAdapter())
		.registerTypeAdapter(CheckId.class, new OnfidoIdTypeAdapter())
		.registerTypeAdapter(DocumentId.class, new OnfidoIdTypeAdapter())
		.registerTypeAdapter(WebhookId.class, new OnfidoIdTypeAdapter())
		.registerTypeAdapter(ApplicantId.class, new OnfidoIdTypeAdapter())
		.registerTypeAdapter(Calendar.class, new CalendarTypeAdapter())
		.registerTypeAdapter(GregorianCalendar.class, new CalendarTypeAdapter())
		.registerTypeAdapter(Date.class, new OnfidoDateTypeAdapter())
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

    protected <V> V toResponse(URI uri, Class<V> responseClass, long startTime, R response, boolean successful, int code) throws IOException {
	if (successful) {
	    if (Void.class.getName().equals(responseClass.getName())) {
		getResponseStringAndLog(uri, startTime, response);
		return null;
	    } else if (byte[].class.getName().equals(responseClass.getName())) {
		byte[] bytes = getBytes(response);
		logResponse(uri, startTime, new String(bytes, 0, 20, UTF8) + "...");
		return (V) bytes;
	    }
	    String responseJson = getResponseStringAndLog(uri, startTime, response);
	    return getAdapter(responseClass).fromJson(responseJson);
	} else {
	    String responseJson = getResponseStringAndLog(uri, startTime, response);
	    return getException(responseJson, code);
	}
    }

    protected <V> V getException(String responseJson, int code) throws ApiException {
	ApiError apiError = null;
	try {
	    apiError = getAdapter(ApiErrorResponse.class).fromJson(responseJson).getError();
	} catch (Exception ignored) {
	}
	throw new ApiException(responseJson, code, apiError);
    }

    protected String getResponseStringAndLog(URI uri, long startTime, R response) throws IOException {
	String responseJson = getString(response);
	logResponse(uri, startTime, responseJson);
	return responseJson;
    }

    protected abstract String getString(R response) throws IOException;
    protected abstract byte[] getBytes(R response) throws IOException;
}
