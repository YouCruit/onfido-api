package com.youcruit.onfido.api.webhook;

import static com.youcruit.onfido.api.http.OnfidoHttpClient.Method.GET;
import static com.youcruit.onfido.api.http.OnfidoHttpClient.Method.POST;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.youcruit.onfido.api.http.OnfidoHttpClient;

public class WebhookClient {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private static final Charset UTF8 = Charset.forName("UTF8");

    private final OnfidoHttpClient onfidoHttpClient;

    public WebhookClient(OnfidoHttpClient onfidoHttpClient) {
	this.onfidoHttpClient = onfidoHttpClient;
    }

    public WebhookRegisterResponse register(URI webhookUri, boolean enabled, List<Event> events) throws IOException {
	if (!webhookUri.getScheme().equals("https")) {
	    throw new IOException("Only https is allowed");
	}
	Map<String, Object> obj = new HashMap<>();
	obj.put("url", webhookUri);
	obj.put("enabled", enabled);
	obj.put("events", events);
	URI uri = onfidoHttpClient.pathToUri("webhooks");
	return onfidoHttpClient.sync(uri, obj, POST, WebhookRegisterResponse.class);
    }

    public WebhookResponse getWebhook(WebhookId webhookId) throws IOException {
	URI uri = onfidoHttpClient.pathToUri("webhooks", webhookId.getId());
	return onfidoHttpClient.sync(uri, null, GET, WebhookResponse.class);
    }

    public List<WebhookResponse> listWebhooks() throws IOException {
	URI uri = onfidoHttpClient.pathToUri("webhooks");
	return onfidoHttpClient.sync(uri, null, GET, WebhookResponseList.class);
    }

    public IncomingWebhookRequest deserializeAndVerify(byte[] json, String token, String attachedSignature) throws SignatureMismatchException {
	try {
	    // get an hmac_sha1 key from the raw key bytes
	    SecretKeySpec signingKey = new SecretKeySpec(token.getBytes(UTF8), HMAC_SHA1_ALGORITHM);

	    // get an hmac_sha1 Mac instance and initialize with the signing key
	    Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
	    mac.init(signingKey);

	    // compute the calculatedSignature on input data bytes
	    byte[] rawHmac = mac.doFinal(json);

	    // base64-encode the calculatedSignature
	    String calculatedSignature = new BigInteger(1, rawHmac).toString(16);
	    if (! calculatedSignature.equals(attachedSignature)) {
		throw new SignatureMismatchException("HMAC mismatch", calculatedSignature, attachedSignature);
	    }
	    return onfidoHttpClient.getAdapter(IncomingWebhookRequest.class).fromJson(new String(json, UTF8));
	} catch (NoSuchAlgorithmException |InvalidKeyException e) {
	    throw new IllegalArgumentException(e.getMessage(), e.getCause());
	} catch (IOException e) {
	    throw new IllegalArgumentException("Failed to parse json", e);
	}
    }
}
