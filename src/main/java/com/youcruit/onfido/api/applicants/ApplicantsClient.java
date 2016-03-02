package com.youcruit.onfido.api.applicants;

import static com.youcruit.onfido.api.http.OnfidoHttpClient.Method.GET;
import static com.youcruit.onfido.api.http.OnfidoHttpClient.Method.POST;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.youcruit.onfido.api.http.OnfidoHttpClient;

@ThreadSafe
public class ApplicantsClient {

    private final OnfidoHttpClient httpClient;

    public ApplicantsClient(@Nonnull OnfidoHttpClient httpClient) {
	this.httpClient = httpClient;
    }

    public ApplicantResponse createApplicant(@Nonnull ApplicantCreationRequest request) throws IOException {
	return httpClient.sync(httpClient.pathToUri("applicants"), request, POST, ApplicantResponse.class);
    }

    public ApplicantResponse getApplicant(@Nonnull ApplicantId applicantId) throws IOException {
	return httpClient.sync(httpClient.pathToUri("applicants", applicantId.getId()), null, GET, ApplicantResponse.class);
    }

    public ApplicantList listApplicants(@Nullable @Nonnegative Integer page, @Nullable @Nonnegative Integer pageSize) throws IOException {
	Map<String, String> queryParameters = new HashMap<>();
	if (page != null) {
	    queryParameters.put("page", String.valueOf(page));
	}
	if (pageSize != null) {
	    queryParameters.put("per_page", String.valueOf(pageSize));
	}
	return httpClient.sync(httpClient.pathToUri(queryParameters, "applicants"), null, GET, ApplicantList.class);
    }
}
