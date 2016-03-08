package com.youcruit.onfido.api.webhook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebhookPayload {
    @Expose
    @SerializedName("resource_type")
    private ResourceType resourceType;
    @Expose
    @SerializedName("action")
    private Action action;
    @Expose
    @SerializedName("object")
    private EventObject eventObject;

    public ResourceType getResourceType() {
	return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
	this.resourceType = resourceType;
    }

    public Action getAction() {
	return action;
    }

    public void setAction(Action action) {
	this.action = action;
    }

    public EventObject getEventObject() {
	return eventObject;
    }

    public void setEventObject(EventObject eventObject) {
	this.eventObject = eventObject;
    }

    @Override
    public String toString() {
	return "WebhookPayload{" +
		"resourceType=" + resourceType +
		", action=" + action +
		", eventObject=" + eventObject +
		'}';
    }
}
