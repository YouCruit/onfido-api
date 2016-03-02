package com.youcruit.onfido.api.report;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OptionField {
    @Expose
    @SerializedName("name")
    private Option option;
    @Expose
    @SerializedName("options")
    private List<NestedOptionField> options;

    public Option getOption() {
	return option;
    }

    public void setOption(Option option) {
	this.option = option;
    }

    public List<NestedOptionField> getOptions() {
	return options;
    }

    public void setOptions(List<NestedOptionField> options) {
	this.options = options;
    }
}
