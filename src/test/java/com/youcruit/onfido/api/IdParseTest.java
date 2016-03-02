package com.youcruit.onfido.api;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;

import org.junit.Test;

import com.youcruit.onfido.api.applicants.ApplicantId;
import com.youcruit.onfido.api.applicants.ApplicantResponse;
import com.youcruit.onfido.api.http.AbstractOnfidoHttpClient;
import com.youcruit.onfido.api.http.OnfidoCallback;

public class IdParseTest {
    String applicantJson = "{\"id\":\"8b71ae80-6661-45c9-bcf8-89489ac5f001\",\"created_at\":\"2016-03-02T16:38:23Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/8b71ae80-6661-45c9-bcf8-89489ac5f001\",\"id_numbers\":[],\"addresses\":[]}";
    AbstractOnfidoHttpClient gsonParser = new AbstractOnfidoHttpClient(null, null) {

	@Override
	public <V> void async(URI uri, Object requestBody, Method method, OnfidoCallback<V> callback, Class<V> responseClass) {
	}

	@Override
	public <V> V sync(URI uri, Object requestBody, Method method, Class<V> responseClass) throws IOException {
	    return null;
	}
    };

    @Test
    public void testGson() throws IOException {
	ApplicantResponse applicantResponse = gsonParser.getAdapter(ApplicantResponse.class).fromJson(applicantJson);
	assertEquals(new ApplicantId("8b71ae80-6661-45c9-bcf8-89489ac5f001"), applicantResponse.getId());
    }



}
