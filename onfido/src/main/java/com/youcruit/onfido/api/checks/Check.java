package com.youcruit.onfido.api.checks;

import java.net.URI;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.youcruit.onfido.api.report.Report;
import com.youcruit.onfido.api.webhook.Status;

public class Check {
    @Expose
    @SerializedName("id")
    private CheckId id;
    @Expose
    @SerializedName("created_at")
    private Date createdAt;
    @Expose
    @SerializedName("href")
    private String pathToThis;
    @Expose
    @SerializedName("type")
    private CheckType type;
    @Expose
    @SerializedName("status")
    private Status status;
    @Expose
    @SerializedName("result")
    private Result result;
    @Expose
    @SerializedName("redirect_uri")
    private URI redirectUri;
    @Expose
    @SerializedName("download_uri")
    private URI downloadUri;
    @Expose
    @SerializedName("results_uri")
    private URI resultsUri;
    @Expose
    @SerializedName("form_uri")
    private URI formUri;
    @Expose
    @SerializedName("reports")
    private List<Report> reports;

    public CheckId getId() {
	return id;
    }

    public void setId(final CheckId id) {
	this.id = id;
    }

    public Date getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
	this.createdAt = createdAt;
    }

    public String getPathToThis() {
	return pathToThis;
    }

    public void setPathToThis(final String pathToThis) {
	this.pathToThis = pathToThis;
    }

    public CheckType getType() {
	return type;
    }

    public void setType(final CheckType type) {
	this.type = type;
    }

    public Status getStatus() {
	return status;
    }

    public void setStatus(final Status status) {
	this.status = status;
    }

    public Result getResult() {
	return result;
    }

    public void setResult(final Result result) {
	this.result = result;
    }

    public URI getRedirectUri() {
	return redirectUri;
    }

    public void setRedirectUri(final URI redirectUri) {
	this.redirectUri = redirectUri;
    }

    public URI getDownloadUri() {
	return downloadUri;
    }

    public void setDownloadUri(final URI downloadUri) {
	this.downloadUri = downloadUri;
    }

    public URI getResultsUri() {
	return resultsUri;
    }

    public void setResultsUri(final URI resultsUri) {
	this.resultsUri = resultsUri;
    }

    public URI getFormUri() {
	return formUri;
    }

    public void setFormUri(final URI formUri) {
	this.formUri = formUri;
    }

    public List<Report> getReports() {
	return reports;
    }

    public void setReports(final List<Report> reports) {
	this.reports = reports;
    }

    @Override
    public String toString() {
	return "Check{" +
		"id=" + id +
		", createdAt=" + createdAt +
		", pathToThis='" + pathToThis + '\'' +
		", type=" + type +
		", status=" + status +
		", result=" + result +
		", redirectUri=" + redirectUri +
		", downloadUri=" + downloadUri +
		", resultsUri=" + resultsUri +
		", reports=" + reports +
		'}';
    }
}
