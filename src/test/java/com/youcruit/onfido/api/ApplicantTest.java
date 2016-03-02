package com.youcruit.onfido.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

import org.junit.Test;

import com.neovisionaries.i18n.CountryCode;
import com.youcruit.onfido.api.applicants.ApplicantCreationRequest;
import com.youcruit.onfido.api.applicants.ApplicantId;
import com.youcruit.onfido.api.applicants.ApplicantList;
import com.youcruit.onfido.api.applicants.ApplicantResponse;
import com.youcruit.onfido.api.applicants.ApplicantsClient;
import com.youcruit.onfido.api.applicants.IdNumber;
import com.youcruit.onfido.api.applicants.IdNumberType;
import com.youcruit.onfido.api.http.FakeHttpClient;
import com.youcruit.onfido.api.http.OnfidoHttpClient;

public class ApplicantTest extends HttpIT {

    private final OnfidoHttpClient client;
    private final ApplicantsClient applicantsClient;

    public ApplicantTest(Class<OnfidoHttpClient> httpClientClass) {
	super(httpClientClass);
	client = createClient();
	applicantsClient = new ApplicantsClient(client);
    }

    @Test
    public void runAllSequentialTests() throws IOException {
	if (client instanceof FakeHttpClient) {
	    FakeHttpClient fakeClient = (FakeHttpClient) client;
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v1/applicants"), "{\"id\":\"8d16119d-866e-4a05-8aa4-472c41608ef8\",\"created_at\":\"2016-03-02T19:13:33Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/8d16119d-866e-4a05-8aa4-472c41608ef8\",\"id_numbers\":[],\"addresses\":[]}");
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v1/applicants?per_page=20&page=1"), "{\"applicants\":[{\"id\":\"8d16119d-866e-4a05-8aa4-472c41608ef8\",\"created_at\":\"2016-03-02T19:13:33Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/8d16119d-866e-4a05-8aa4-472c41608ef8\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"a9b56d17-935f-4fb0-a688-3022029625f1\",\"created_at\":\"2016-03-02T19:12:44Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/a9b56d17-935f-4fb0-a688-3022029625f1\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"c1464dec-4046-45a0-9c27-25ba522f94c9\",\"created_at\":\"2016-03-02T19:12:11Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/c1464dec-4046-45a0-9c27-25ba522f94c9\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"142f8eb6-152f-4e57-8acf-9a3d2f1d2f77\",\"created_at\":\"2016-03-02T16:56:26Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/142f8eb6-152f-4e57-8acf-9a3d2f1d2f77\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"b5a028b4-a716-4c28-a181-8f9b1951b1b0\",\"created_at\":\"2016-03-02T16:54:09Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/b5a028b4-a716-4c28-a181-8f9b1951b1b0\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"8b71ae80-6661-45c9-bcf8-89489ac5f001\",\"created_at\":\"2016-03-02T16:38:23Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/8b71ae80-6661-45c9-bcf8-89489ac5f001\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"5b14b454-8e47-41bf-99c3-f82d4a8c3bdf\",\"created_at\":\"2016-03-02T16:36:25Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/5b14b454-8e47-41bf-99c3-f82d4a8c3bdf\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"e1628b63-7335-4e1c-a22f-1fb16d3ebd85\",\"created_at\":\"2016-03-02T16:07:16Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/e1628b63-7335-4e1c-a22f-1fb16d3ebd85\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"c1e5ed2d-ae6c-46c7-a3ea-c2f4fbf88dca\",\"created_at\":\"2016-03-02T15:39:35Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"2016-02-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/c1e5ed2d-ae6c-46c7-a3ea-c2f4fbf88dca\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"9c5a62bb-7eea-4e35-a221-3badc551b335\",\"created_at\":\"2016-03-02T15:39:17Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"2016-02-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/9c5a62bb-7eea-4e35-a221-3badc551b335\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"4010f89a-0965-4ba2-9a53-73e363be6b39\",\"created_at\":\"2016-03-02T15:34:48Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"2016-02-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/4010f89a-0965-4ba2-9a53-73e363be6b39\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"5285686f-d2eb-4608-8a41-a34dfc9df993\",\"created_at\":\"2016-03-02T15:34:05Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"2016-02-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/5285686f-d2eb-4608-8a41-a34dfc9df993\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"3ddd142b-7ff2-4dfb-acbb-5ac04407083e\",\"created_at\":\"2016-03-02T15:33:49Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"2016-02-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/3ddd142b-7ff2-4dfb-acbb-5ac04407083e\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"c3c1f7dc-42a0-447a-ade0-b2a5b015373f\",\"created_at\":\"2016-03-02T15:27:51Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"2016-02-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/c3c1f7dc-42a0-447a-ade0-b2a5b015373f\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"8633a302-724e-42e5-9863-581235051d79\",\"created_at\":\"2016-03-02T15:27:02Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"2016-02-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/8633a302-724e-42e5-9863-581235051d79\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"13b666ab-bee6-4dbd-98df-78222bb93269\",\"created_at\":\"2016-03-02T15:26:54Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"2016-02-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/13b666ab-bee6-4dbd-98df-78222bb93269\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"25d8a366-c3eb-4b32-94b0-226e84f9abdd\",\"created_at\":\"2016-03-02T15:22:02Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"2016-02-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/25d8a366-c3eb-4b32-94b0-226e84f9abdd\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"338b789c-440b-4a22-ba62-c93bbd48ebd7\",\"created_at\":\"2016-03-02T15:21:20Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"2016-02-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/338b789c-440b-4a22-ba62-c93bbd48ebd7\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"e9e92bb8-33aa-4d19-b48c-8febc6fdfdfa\",\"created_at\":\"2016-03-02T15:19:27Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"2016-02-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/e9e92bb8-33aa-4d19-b48c-8febc6fdfdfa\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"1e8ef058-8a51-417e-ae92-85b656465a86\",\"created_at\":\"2016-03-02T15:17:49Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"2016-02-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/1e8ef058-8a51-417e-ae92-85b656465a86\",\"id_numbers\":[],\"addresses\":[]}]}");
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v1/applicants/8d16119d-866e-4a05-8aa4-472c41608ef8"), "{\"id\":\"8d16119d-866e-4a05-8aa4-472c41608ef8\",\"created_at\":\"2016-03-02T19:13:33Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":null,\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"gbr\",\"href\":\"/v1/applicants/8d16119d-866e-4a05-8aa4-472c41608ef8\",\"id_numbers\":[],\"addresses\":[]}");
	}

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
	applicantCreationRequest.setCountry(CountryCode.US);
	String email = UUID.randomUUID().toString() + "@example.com";
	applicantCreationRequest.setEmail(email);

	IdNumber ssn = new IdNumber();
	ssn.setType(IdNumberType.SSN);
	ssn.setValue("404-35-0728");

	ApplicantResponse applicant = applicantsClient.createApplicant(applicantCreationRequest);
	assertEquals("Foo", applicant.getFirstName());
	assertEquals("Bar", applicant.getLastName());
	assertEquals("FB", applicant.getMiddleName());
	assertSameDate(aestNow, applicant.getDateOfBirth());
	assertEquals(CountryCode.US, applicant.getCountry());

	assertEquals(email, applicant.getEmail());
	return applicant.getId();
    }
}
