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
    @SerializedName("properties")
    private Map<String, Object> properties;

    public Result getResult() {
	return result;
    }

    public void setResult(final Result result) {
	this.result = result;
    }

    public Map<String, Object> getProperties() {
	return properties;
    }

    public void setProperties(final Map<String, Object> properties) {
	this.properties = properties;
    }

    @Override
    public String toString() {
	return "BreakdownField{" +
		"result=" + result +
		", properties=" + properties +
		'}';
    }
}
