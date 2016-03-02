package com.youcruit.onfido.api.webhook;

public class SignatureMismatchException extends Exception {
    private static final long serialVersionUID = -8492327887129999798L;

    private final String calculated;
    private final String attachedInRequest;

    public SignatureMismatchException(String message, String calculated, String attachedInRequest) {
	super(message);
	this.calculated = calculated;
	this.attachedInRequest = attachedInRequest;
    }

    public String getAttachedInRequest() {
	return attachedInRequest;
    }

    public String getCalculated() {
	return calculated;
    }
}
