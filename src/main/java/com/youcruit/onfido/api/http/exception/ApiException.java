package com.youcruit.onfido.api.http.exception;

import java.io.IOException;

public class ApiException extends IOException {
    private static final int MAX_MESSAGE = 1000;
    private static final long serialVersionUID = 5702334662139938998L;
    private final String response;
    private final int httpStatusCode;
    private final ApiError parsedResponse;

    public ApiException(String response, int httpStatusCode, ApiError parsedResponse) {
	super(getString(response, httpStatusCode));
	this.response = response;
	this.httpStatusCode = httpStatusCode;
	this.parsedResponse = parsedResponse;
    }

    private static String getString(String response, final int httpStatusCode) {
	StringBuilder sb = new StringBuilder("Request failed with " + httpStatusCode + " and response: ");
	if (response != null && response.length() > MAX_MESSAGE) {
	    sb.append(response, 0, MAX_MESSAGE);
	} else {
	    sb.append(response);
	}
	return sb.toString();
    }

    public String getResponse() {
	return response;
    }

    public ApiError getParsedResponse() {
	return parsedResponse;
    }

    public int getHttpStatusCode() {
	return httpStatusCode;
    }
}
