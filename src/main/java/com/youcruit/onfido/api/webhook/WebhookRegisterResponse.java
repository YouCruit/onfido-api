package com.youcruit.onfido.api.webhook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebhookRegisterResponse extends WebhookResponse {
    @Expose
    @SerializedName("token")
    private String token;

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    @Override
    public String toString() {
	return "WebhookRegisterResponse{" +
		"token='" + token + '\'' +
		"} " + super.toString();
    }
}
