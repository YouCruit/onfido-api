package com.youcruit.onfido.api;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.youcruit.onfido.api.http.FakeHttpClient;
import com.youcruit.onfido.api.http.OnfidoHttpClient;
import com.youcruit.onfido.api.webhook.Event;
import com.youcruit.onfido.api.webhook.SignatureMismatchException;
import com.youcruit.onfido.api.webhook.WebhookClient;

public class WebhookTest extends HttpIT {

    public static final Logger LOGGER = LogManager.getLogger(WebhookTest.class);
    private OnfidoHttpClient client;
    private WebhookClient webhookClient;
    private String onfidoWebhookHost;

    public WebhookTest(final Class<OnfidoHttpClient> httpClientClass) {
	super(httpClientClass);
    }

    @Before
    public void setWebhookHost() {
	onfidoWebhookHost = getPropEnv("ONFIDO_WEBHOOK_URI");
	if (onfidoWebhookHost == null) {
	    client = new FakeHttpClient();
	    onfidoWebhookHost = "https://example.com/foo/";
	} else {
	    LOGGER.warn("ONFIDO_WEBHOOK_HOST is defined, so not using FakeClient");
	    client = createClient();
	}
	webhookClient = new WebhookClient(client);
    }

    @Test
    public void createWebhook() throws IOException {
	if (client instanceof FakeHttpClient) {
	    FakeHttpClient fakeClient = (FakeHttpClient) this.client;
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v2/webhooks"), "{\"id\":\"b6c6c111-ffbb-491d-bc03-0a7bae829a53\",\"url\":\"https://example.com/foo/\",\"enabled\":true,\"token\":\"o4TfmcJ-lwEHLmJJDN9LvyIObJlkiM5l\",\"href\":\"/v2/webhooks/b6c6c111-ffbb-491d-bc03-0a7bae829a53\",\"events\":[\"report completion\",\"report withdrawal\",\"check in progress\",\"check completion\"]}");
	}
	webhookClient.register(URI.create(onfidoWebhookHost), true, asList(Event.values()));
    }

    @Test
    public void validateWebhook() throws UnsupportedEncodingException, SignatureMismatchException {
	String token = "4zvPpGGpvcqKcgGOEC7y3Ju8v2WC0btO";

	String signature1 = "130ff94a8d70e734c57b181968efe6ed32c387c6";
	String payload1 = "{\"payload\":{\"resource_type\":\"check\",\"action\":\"in progress\",\"object\":{\"id\":\"c86f46e1-a8ae-47a2-9d48-fd54da7fe3c5\",\"status\":\"in_progress\",\"completed_at\":\"2016-03-09 13:10:44 UTC\",\"href\":\"https://api.onfido.com/v1/applicants/95af7d0d-1c23-4c07-a8cd-8b66108decc6/checks/c86f46e1-a8ae-47a2-9d48-fd54da7fe3c5\"}}}";
	webhookClient.deserializeAndVerify(payload1.getBytes("UTF-8"), token, signature1);

	String signature2 = "fe9920536efcf6a5af4581498a39cedd0993be32";
	String payload2 = "{\"payload\":{\"resource_type\":\"report\",\"action\":\"completed\",\"object\":{\"id\":\"412c7d24-3186-4cc0-b922-d608ee5c8ec3\",\"status\":\"complete\",\"completed_at\":\"2016-03-09 13:10:53 UTC\",\"href\":\"https://api.onfido.com/v1/checks/c86f46e1-a8ae-47a2-9d48-fd54da7fe3c5/reports/412c7d24-3186-4cc0-b922-d608ee5c8ec3\"}}}";
	webhookClient.deserializeAndVerify(payload2.getBytes("UTF-8"), token, signature2);

	String signature3 = "476a3cab6fe7e1ad2ee8e147c44e6a560b1b9e24";
	String payload3 = "{\"payload\":{\"resource_type\":\"report\",\"action\":\"completed\",\"object\":{\"id\":\"412c7d24-3186-4cc0-b922-d608ee5c8ec3\",\"status\":\"complete\",\"occured_at\":\"2016-03-09T13:10:54+00:00\",\"href\":\"/v1/checks/c86f46e1-a8ae-47a2-9d48-fd54da7fe3c5/reports/412c7d24-3186-4cc0-b922-d608ee5c8ec3\"}}}";
	webhookClient.deserializeAndVerify(payload3.getBytes("UTF-8"), token, signature3);
    }
}
