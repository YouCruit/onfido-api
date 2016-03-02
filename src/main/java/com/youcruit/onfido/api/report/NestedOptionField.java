package com.youcruit.onfido.api.report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NestedOptionField {
    @Expose
    @SerializedName("name")
    private Option option;
}
