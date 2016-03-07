package com.youcruit.onfido.api.report;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.youcruit.onfido.api.checks.Result;

public class Report {
    @Expose
    @SerializedName("id")
    private ReportId reportId;
    @Expose
    @SerializedName("name")
    private ReportType reportType;
    @Expose
    @SerializedName("created_at")
    private Date createdAt;
    @Expose
    @SerializedName("status")
    private ReportStatus status;
    @Expose
    @SerializedName("result")
    private Result result;
    @Expose
    @SerializedName("variant")
    private Variant variant;
    @Expose
    @SerializedName("options")
    private List<OptionField> options;
    @Expose
    @SerializedName("href")
    private String pathToThis;
    @Expose
    @SerializedName("breakdown")
    private Map<BreakdownFieldType, BreakdownField> breakdown;
    @Expose
    @SerializedName("properties")
    private Map<PropertyFieldType, Object> properties;

    public ReportId getReportId() {
	return reportId;
    }

    public void setReportId(ReportId reportId) {
	this.reportId = reportId;
    }

    public ReportType getReportType() {
	return reportType;
    }

    public void setReportType(ReportType reportType) {
	this.reportType = reportType;
    }

    public Date getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
    }

    public ReportStatus getStatus() {
	return status;
    }

    public void setStatus(ReportStatus status) {
	this.status = status;
    }

    public Result getResult() {
	return result;
    }

    public void setResult(Result result) {
	this.result = result;
    }

    public Variant getVariant() {
	return variant;
    }

    public void setVariant(Variant variant) {
	this.variant = variant;
    }

    public List<OptionField> getOptions() {
	return options;
    }

    public void setOptions(List<OptionField> options) {
	this.options = options;
    }

    public String getPathToThis() {
	return pathToThis;
    }

    public void setPathToThis(String pathToThis) {
	this.pathToThis = pathToThis;
    }

    public Map<BreakdownFieldType, BreakdownField> getBreakdown() {
	return breakdown;
    }

    public void setBreakdown(Map<BreakdownFieldType, BreakdownField> breakdown) {
	this.breakdown = breakdown;
    }

    public Map<PropertyFieldType, Object> getProperties() {
	return properties;
    }

    public void setProperties(Map<PropertyFieldType, Object> properties) {
	this.properties = properties;
    }

    @Override
    public String toString() {
	return "Report{" +
		"reportId=" + reportId +
		", reportType=" + reportType +
		", createdAt=" + createdAt +
		", status=" + status +
		", result=" + result +
		", variant=" + variant +
		", options=" + options +
		", pathToThis='" + pathToThis + '\'' +
		", breakdown=" + breakdown +
		", properties=" + properties +
		'}';
    }
}
