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
}
