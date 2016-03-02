package com.youcruit.onfido.api.applicants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.neovisionaries.i18n.CountryCode;

public class IdNumber {
    @Expose
    @SerializedName("type")
    private IdNumberType type;
    @Expose
    @SerializedName("value")
    private String value;
    @Expose
    @SerializedName("state")
    private String stateCode;

    public IdNumberType getType() {
	return type;
    }

    public void setType(final IdNumberType type) {
	this.type = type;
    }

    public String getValue() {
	return value;
    }

    public void setValue(final String value) {
	this.value = value;
    }

    public String getStateCode() {
	return stateCode;
    }

    public void setStateCode(final String stateCode) {
	this.stateCode = stateCode;
    }

    @Override
    public String toString() {
	return "IdNumber{" +
		"type=" + type +
		", value='" + value + '\'' +
		", stateCode='" + stateCode + '\'' +
		'}';
    }
}
