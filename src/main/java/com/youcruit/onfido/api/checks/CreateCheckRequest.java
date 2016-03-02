package com.youcruit.onfido.api.checks;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateCheckRequest {
    @Expose
    @SerializedName("type")
    private CheckType type;
    @Expose
    @SerializedName("reports")
    private List<ReportRequest> reportRequests;
}
