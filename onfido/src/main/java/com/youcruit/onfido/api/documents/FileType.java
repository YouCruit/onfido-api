package com.youcruit.onfido.api.documents;

import com.google.gson.annotations.SerializedName;

public enum FileType {
    @SerializedName("png")
    PNG("image/png"),
    @SerializedName("jpg")
    JPG("image/jpeg"),
    @SerializedName("pdf")
    PDF("application/pdf");

    final String contentType;

    FileType(final String contentType) {
	this.contentType = contentType;
    }
}
