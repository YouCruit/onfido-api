package com.youcruit.onfido.api.http.exception;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiErrorResponse {
    @Expose
    @SerializedName("error")
    private ApiError error;

    public ApiError getError() {
	return error;
    }

    public void setError(ApiError error) {
	this.error = error;
    }
}
