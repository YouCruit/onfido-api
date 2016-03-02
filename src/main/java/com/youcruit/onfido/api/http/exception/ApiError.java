package com.youcruit.onfido.api.http.exception;

import java.util.List;
import java.util.Map;

import javax.lang.model.type.ErrorType;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiError {
    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("type")
    private ErrorType type;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("fields")
    Map<String, List<String>> fields;

    public Map<String, List<String>> getFields() {
	return fields;
    }

    public void setFields(Map<String, List<String>> fields) {
	this.fields = fields;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public ErrorType getType() {
	return type;
    }

    public void setType(ErrorType type) {
	this.type = type;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }
}
