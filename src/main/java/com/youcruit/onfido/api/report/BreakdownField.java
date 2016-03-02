package com.youcruit.onfido.api.report;

import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.youcruit.onfido.api.checks.Result;

public class BreakdownField {
    @Expose
    @SerializedName("result")
    private Result result;

    @Expose
    @SerializedName("breakdown")
    private Map<BreakdownFieldType, BreakdownField> breakdown;
    @Expose
    @SerializedName("properties")
    private Map<String, Object> properties;
}
