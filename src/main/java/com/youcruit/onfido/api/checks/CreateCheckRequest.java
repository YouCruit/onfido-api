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

    public CheckType getType() {
	return type;
    }

    public void setType(final CheckType type) {
	this.type = type;
    }

    public List<ReportRequest> getReportRequests() {
	return reportRequests;
    }

    public void setReportRequests(final List<ReportRequest> reportRequests) {
	this.reportRequests = reportRequests;
    }

    @Override
    public String toString() {
	return "CreateCheckRequest{" +
		"type=" + type +
		", reportRequests=" + reportRequests +
		'}';
    }
}
