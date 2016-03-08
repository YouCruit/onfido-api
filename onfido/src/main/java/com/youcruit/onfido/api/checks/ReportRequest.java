package com.youcruit.onfido.api.checks;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.youcruit.onfido.api.report.OptionField;
import com.youcruit.onfido.api.report.ReportType;
import com.youcruit.onfido.api.report.Variant;

public class ReportRequest {
    @Expose
    @SerializedName("name")
    private ReportType reportType;
    @Expose
    @SerializedName("variant")
    private Variant variant;
    @Expose
    @SerializedName("options")
    private List<OptionField> options;

    public ReportType getReportType() {
	return reportType;
    }

    public void setReportType(final ReportType reportType) {
	this.reportType = reportType;
    }

    public Variant getVariant() {
	return variant;
    }

    public void setVariant(final Variant variant) {
	this.variant = variant;
    }

    public List<OptionField> getOptions() {
	return options;
    }

    public void setOptions(final List<OptionField> options) {
	this.options = options;
    }

    @Override
    public String toString() {
	return "ReportRequest{" +
		"reportType=" + reportType +
		", variant=" + variant +
		", options=" + options +
		'}';
    }
}
