package com.youcruit.onfido.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Test;

import com.youcruit.onfido.api.applicants.ApplicantCreationRequest;
import com.youcruit.onfido.api.applicants.ApplicantId;
import com.youcruit.onfido.api.applicants.ApplicantList;
import com.youcruit.onfido.api.applicants.ApplicantResponse;
import com.youcruit.onfido.api.applicants.ApplicantsClient;
import com.youcruit.onfido.api.http.OnfidoHttpClient;

public class ApplicantIT extends HttpIT {

    private final OnfidoHttpClient client;
    private final ApplicantsClient applicantsClient;

    public ApplicantIT(Class<OnfidoHttpClient> httpClientClass) {
	super(httpClientClass);
	client = createClient();
	applicantsClient = new ApplicantsClient(client);
    }

    @Test
    public void runAllSequentialTests() throws IOException {
	// Far from optimal, but since all the tests require creation of a new applicant and doesn't modify it, do them sequentially
	ApplicantId id = createApplicant();
	listApplicants(id);
	getApplicant(id);
    }

    private void getApplicant(ApplicantId id) throws IOException {
	ApplicantResponse applicant = applicantsClient.getApplicant(id);
	assertEquals(id, applicant.getId());
	assertEquals("Foo", applicant.getFirstName());
    }

    private void listApplicants(ApplicantId id) throws IOException {
	int page = 1;
	ApplicantList list;
	do {
	    list = applicantsClient.listApplicants(page, 20);
	    for (ApplicantResponse applicantResponse : list.getApplicants()) {
		if (applicantResponse.getId().equals(id)) {
		    return;
		}
	    }
	} while (! list.getApplicants().isEmpty());
	fail("Didn't find the applicant we're supposed to find");
    }

    public ApplicantId createApplicant() throws IOException {
	ApplicantCreationRequest applicantCreationRequest = new ApplicantCreationRequest();
	applicantCreationRequest.setFirstName("Foo");
	applicantCreationRequest.setLastName("Bar");
	applicantCreationRequest.setMiddleName("FB");
	Calendar aestNow = Calendar.getInstance(TimeZone.getTimeZone("AEST"));
	aestNow.set(Calendar.YEAR, 1980);
	applicantCreationRequest.setDateOfBirth(aestNow);

	ApplicantResponse applicant = applicantsClient.createApplicant(applicantCreationRequest);
	assertEquals("Foo", applicant.getFirstName());
	assertEquals("Bar", applicant.getLastName());
	assertEquals("FB", applicant.getMiddleName());
	assertSameDate(aestNow, applicant.getDateOfBirth());
	return applicant.getId();
    }

}
