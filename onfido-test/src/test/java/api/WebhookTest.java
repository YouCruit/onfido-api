package com.youcruit.onfido.api;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.net.URI;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.youcruit.onfido.api.http.FakeHttpClient;
import com.youcruit.onfido.api.http.OnfidoHttpClient;
import com.youcruit.onfido.api.webhook.Event;
import com.youcruit.onfido.api.webhook.WebhookClient;

public class WebhookTest extends HttpIT {

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
	    Logger.getLogger(getClass()).warn("ONFIDO_WEBHOOK_HOST is defined, so not using FakeClient");
	    client = createClient();
	}
	webhookClient = new WebhookClient(client);
    }

    @Test
    public void createWebhook() throws IOException {
	if (client instanceof FakeHttpClient) {
	    FakeHttpClient fakeClient = (FakeHttpClient) this.client;
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v1/webhooks"), "{\"id\":\"b6c6c111-ffbb-491d-bc03-0a7bae829a53\",\"url\":\"https://example.com/foo/\",\"enabled\":true,\"token\":\"o4TfmcJ-lwEHLmJJDN9LvyIObJlkiM5l\",\"href\":\"/v1/webhooks/b6c6c111-ffbb-491d-bc03-0a7bae829a53\",\"events\":[\"report completion\",\"report withdrawal\",\"check in progress\",\"check completion\"]}");
	}
	webhookClient.register(URI.create(onfidoWebhookHost), true, asList(Event.values()));
    }
}
