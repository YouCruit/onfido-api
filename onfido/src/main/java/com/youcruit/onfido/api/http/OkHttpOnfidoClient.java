package com.youcruit.onfido.api.http;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.concurrent.ThreadSafe;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.CipherSuite;
import com.squareup.okhttp.ConnectionSpec;
import com.squareup.okhttp.ConnectionSpec.Builder;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.TlsVersion;

import okio.ByteString;

@ThreadSafe
public class OkHttpOnfidoClient extends AbstractOnfidoHttpClient<Response> {
    private static final Pattern HOST_MATCHER = Pattern.compile("(.*\\.|)onfido.com");

    private final OkHttpClient client;
    private static final MediaType JSON = MediaType.parse("application/json");
    private final Map<String, String> headersToReplace;

    private OkHttpClient createOkClient() {
	OkHttpClient okHttpClient = new OkHttpClient();
	ConnectionSpec sslConnectionSpec = new Builder(ConnectionSpec.MODERN_TLS)
		.tlsVersions(TlsVersion.TLS_1_2)
		.cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
			CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
			CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA)

		.build();
	okHttpClient.setConnectionSpecs(Collections.singletonList(sslConnectionSpec));
	okHttpClient.interceptors().add(new HeaderAdder());
	return okHttpClient;
    }

    public OkHttpOnfidoClient(String authToken) {
	this(null, null, null, authToken);
    }

    public OkHttpOnfidoClient(String apiUrl, String authToken) {
	this(null, null, apiUrl, authToken);
    }

    public OkHttpOnfidoClient(OkHttpClient okHttpClient, Gson gson, String apiUrl, String authToken) {
	super(gson, apiUrl);
	if (okHttpClient == null) {
	    this.client = createOkClient();
	} else {
	    this.client = okHttpClient;
	}
	headersToReplace = Collections.singletonMap("Authorization", "Token token=" + authToken);
    }

    public <V> void async(URI uri, Object requestBody, OnfidoHttpClient.Method method, OnfidoCallback<V> callback, Class<V> responseClass) {
	final Request request = createRequest(uri, requestBody, method);
	client.newCall(request).enqueue(new CallbackWrapper<>(callback, responseClass));
    }

    public <V> V sync(URI uri, Object requestBody, OnfidoHttpClient.Method method, Class<V> responseClass) throws IOException {
	final Request request = createRequest(uri, requestBody, method);
	final long startTime = System.currentTimeMillis();
	final Response response = client.newCall(request).execute();
	boolean successful = response.isSuccessful();
	return toResponse(uri, responseClass, startTime, response, successful, response.code());
    }

    public byte[] getBytes(Response response) throws IOException {
	return response.body().bytes();
    }

    @Override
    public String getString(Response response) throws IOException {
	return response.body().string();
    }

    @SuppressWarnings("ConstantConditions")
    private Request createRequest(URI uri, Object requestBody, OnfidoHttpClient.Method method) {
	Request.Builder requestBuilder = new Request.Builder().url(uri.toString());
	RequestBody payload = null;
	if (requestBody == null && method == Method.POST) {
	    requestBody = Collections.emptyMap();
	}
	if (requestBody != null) {
	    if (requestBody.getClass() == Part[].class) {
		MultipartBuilder multipartBuilder = new MultipartBuilder();
		for (Part part : ((Part[]) requestBody)) {
		    if (part.getContentType() != null) {
			RequestBody body = RequestBody.create(MediaType.parse(part.getContentType()), part.getData());
			multipartBuilder.addFormDataPart(part.getName(), part.getName(), body);
		    } else {
			multipartBuilder.addFormDataPart(part.getName(), new String(part.getData(), UTF8));
		    }
		}
		payload = multipartBuilder.build();
	    } else {
		String requestString = toJson(requestBody);
		logRequest(uri, method, requestString);
		payload = RequestBody.create(JSON, ByteString.encodeUtf8(requestString));
	    }
	}
	requestBuilder.method(method.name(), payload);
	return requestBuilder.build();
    }

    private class CallbackWrapper<V> implements Callback {
	private final OnfidoCallback<V> callback;
	private final Class<V> clazz;
	private final long startTime = System.currentTimeMillis();

	private CallbackWrapper(OnfidoCallback<V> callback, Class<V> clazz) {
	    this.callback = callback;
	    this.clazz = clazz;
	}

	@Override
	public void onFailure(Request request, IOException e) {
	    callback.onError(e);
	}

	@Override
	public void onResponse(Response response) throws IOException {
	    try {
		boolean successful = response.isSuccessful();
		V responseObject = toResponse(response.request().uri(), clazz, startTime, response, successful, response.code());
		callback.onSuccess(responseObject);
	    } catch (IOException e) {
		callback.onError(e);
	    } catch (Exception e) {
		callback.onError(new IOException(e));
	    }
	}
    }

    private class HeaderAdder implements Interceptor {

	@Override
	public Response intercept(Chain chain) throws IOException {
	    Request originalRequest = chain.request();
	    Request.Builder builder = originalRequest.newBuilder();
	    // Only add authentication on requests to this host
	    HttpUrl httpUrl = originalRequest.httpUrl();
	    if ("https".equals(httpUrl.scheme()) && HOST_MATCHER.matcher(httpUrl.host()).matches()) {
		for (Map.Entry<String, String> header : headersToReplace.entrySet()) {
		    builder.removeHeader(header.getKey()).addHeader(header.getKey(), header.getValue());
		}
	    }
	    Request requestWithUserAgent = builder.build();
	    return chain.proceed(requestWithUserAgent);
	}
    }

    @Override
    public URI pathToUri(String encodedPathSegment, final Map<String, String> queryParameters, String... pathSegments) {
	HttpUrl.Builder builder = HttpUrl.parse(baseUrl + encodedPathSegment).newBuilder();
	for (String pathSegment : pathSegments) {
	    builder.addPathSegment(pathSegment);
	}
	for (Map.Entry<String, String> e : queryParameters.entrySet()) {
	    builder.addQueryParameter(e.getKey(), e.getValue());
	}
	return builder.build().uri();
    }
}