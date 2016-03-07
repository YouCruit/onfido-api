package com.youcruit.onfido.api.webhook;

import java.net.URL;
import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventObject {
    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("status")
    private Status status;
    @Expose
    @SerializedName("completed_at")
    private Date completedAt;
    @Expose
    @SerializedName("href")
    private URL resourceUrl;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public Status getStatus() {
	return status;
    }

    public void setStatus(Status status) {
	this.status = status;
    }

    public Date getCompletedAt() {
	return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
	this.completedAt = completedAt;
    }

    public URL getResourceUrl() {
	return resourceUrl;
    }

    public void setResourceUrl(URL resourceUrl) {
	this.resourceUrl = resourceUrl;
    }

    @Override
    public String toString() {
	return "EventObject{" +
		"id='" + id + '\'' +
		", status=" + status +
		", completedAt=" + completedAt +
		", resourceUrl=" + resourceUrl +
		'}';
    }
}
