package com.youcruit.onfido.api.checks;

import java.io.IOException;
import java.net.URI;

import com.youcruit.onfido.api.applicants.ApplicantId;
import com.youcruit.onfido.api.http.OnfidoHttpClient;

public class CheckClient {
    public final OnfidoHttpClient httpClient;

    public CheckClient(final OnfidoHttpClient httpClient) {
	this.httpClient = httpClient;
    }

    public Check createCheck(CreateCheckRequest checkRequest, ApplicantId applicantId) throws IOException {
	URI uri = httpClient.pathToUri("applicants", applicantId.getId(), "checks");
	return httpClient.sync(uri, checkRequest, OnfidoHttpClient.Method.POST, Check.class);
    }
}
