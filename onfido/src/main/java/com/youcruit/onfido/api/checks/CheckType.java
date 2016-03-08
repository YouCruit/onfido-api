package com.youcruit.onfido.api.checks;

import com.google.gson.annotations.SerializedName;

public enum CheckType {
    @SerializedName(Constants.STANDARD)
    STANDARD(Constants.STANDARD),
    @SerializedName(Constants.EXPRESS)
    EXPRESS(Constants.EXPRESS);

    final String checkType;

    CheckType(final String checkType) {
	this.checkType = checkType;
    }

    private static class Constants {
	public static final String STANDARD = "standard";
	public static final String EXPRESS = "express";
    }
}
