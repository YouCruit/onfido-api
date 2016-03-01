package com.youcruit.onfido.api.documents;

import com.google.gson.annotations.SerializedName;

public enum Side {
    @SerializedName(Constants.FRONT)
    FRONT(Constants.FRONT),
    @SerializedName(Constants.BACK)
    BACK(Constants.BACK);

    final String sideName;

    Side(final String sideName) {
	this.sideName = sideName;
    }

    private static class Constants {
	public static final String FRONT = "front";
	public static final String BACK = "back";
    }
}
