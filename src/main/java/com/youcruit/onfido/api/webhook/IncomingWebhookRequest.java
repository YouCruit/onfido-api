package com.youcruit.onfido.api.webhook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IncomingWebhookRequest {
    @Expose
    @SerializedName("payload")
    private WebhookPayload payload;

    public WebhookPayload getPayload() {
	return payload;
    }

    public void setPayload(WebhookPayload payload) {
	this.payload = payload;
    }
}
