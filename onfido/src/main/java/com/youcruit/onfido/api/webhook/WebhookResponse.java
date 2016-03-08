package com.youcruit.onfido.api.webhook;

import java.net.URI;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebhookResponse {
    @Expose
    @SerializedName("id")
    private WebhookId id;
    @Expose
    @SerializedName("url")
    private URI url;
    @Expose
    @SerializedName("enabled")
    private boolean enabled;
    @Expose
    @SerializedName("href")
    private String pathToThis;
    @Expose
    @SerializedName("events")
    private List<Event> events;

    @Override
    public String toString() {
	return "WebhookResponse{" +
		"id=" + id +
		", url=" + url +
		", enabled=" + enabled +
		", pathToThis='" + pathToThis + '\'' +
		", events=" + events +
		'}';
    }
}
