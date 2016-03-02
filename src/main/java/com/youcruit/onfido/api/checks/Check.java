package com.youcruit.onfido.api.checks;

import java.net.URI;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    private ResultStatus status;
    @Expose
    @SerializedName("result")
    private Result result;
    @Expose
    @SerializedName("redirect_uri")
    private URI redirectUri;
    @Expose
    @SerializedName("results_uri")
    private URI resultsUri;
    @Expose
    @SerializedName("reports")
    private List<String> reportIds;
}
