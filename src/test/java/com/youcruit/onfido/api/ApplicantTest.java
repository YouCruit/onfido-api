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
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v1/applicants"), "{\"id\":\"1c9c0377-6e11-4b5c-943e-f6b2a3ab1886\",\"created_at\":\"2016-03-03T14:31:06Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"da6269f0-ba6f-475f-8f88-41ecdb07b51d@example.com\",\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/1c9c0377-6e11-4b5c-943e-f6b2a3ab1886\",\"id_numbers\":[],\"addresses\":[]}");
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v1/applicants?per_page=20&page=1"), "{\"applicants\":[{\"id\":\"1c9c0377-6e11-4b5c-943e-f6b2a3ab1886\",\"created_at\":\"2016-03-03T14:31:06Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"da6269f0-ba6f-475f-8f88-41ecdb07b51d@example.com\",\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/1c9c0377-6e11-4b5c-943e-f6b2a3ab1886\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"61003dfb-9469-4f60-8471-6c7b3d110c3e\",\"created_at\":\"2016-03-03T14:29:47Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"42c485d9-26c3-4fb8-8c13-75d4bbc91444@example.com\",\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/61003dfb-9469-4f60-8471-6c7b3d110c3e\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"6a8caede-8eba-4455-937b-6ad499c5b222\",\"created_at\":\"2016-03-03T14:12:14Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"6d5665f4-fecb-4d49-9887-973ec220809e@example.com\",\"gender\":null,\"dob\":\"1980-03-03\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/6a8caede-8eba-4455-937b-6ad499c5b222\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]},{\"id\":\"9feeac54-8dc4-416c-bf4f-75abc97f2728\",\"created_at\":\"2016-03-03T14:11:17Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"e5b15782-26d6-4f93-ad4d-79c222f2d0f9@example.com\",\"gender\":null,\"dob\":\"1980-03-03\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/9feeac54-8dc4-416c-bf4f-75abc97f2728\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]},{\"id\":\"d23d728b-47b3-415d-bba5-69fca8d3bd95\",\"created_at\":\"2016-03-03T14:10:53Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"242f63f1-1bc3-445e-930c-496e44163589@example.com\",\"gender\":null,\"dob\":\"1980-03-03\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/d23d728b-47b3-415d-bba5-69fca8d3bd95\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]},{\"id\":\"89b870b4-9319-4c54-ba9e-fd7fd73a686a\",\"created_at\":\"2016-03-03T12:05:49Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"4014e0f0-4d19-49ef-a07d-6c0a4e024bce@example.com\",\"gender\":null,\"dob\":\"1980-03-03\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/89b870b4-9319-4c54-ba9e-fd7fd73a686a\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]},{\"id\":\"fd67c138-1408-4e37-ab50-2afc49fb1301\",\"created_at\":\"2016-03-03T11:44:53Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"678df3b0-39e9-45b0-a05d-1d692e2b4718@example.com\",\"gender\":null,\"dob\":\"1980-03-03\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/fd67c138-1408-4e37-ab50-2afc49fb1301\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]},{\"id\":\"7960c163-6453-4156-857e-45574f49bb7f\",\"created_at\":\"2016-03-03T11:38:13Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"0bd3fb37-ee2f-4dc8-a49f-dda9fb9868ac@example.com\",\"gender\":null,\"dob\":\"1980-03-03\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/7960c163-6453-4156-857e-45574f49bb7f\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]},{\"id\":\"ec96b4b1-399d-4bee-a1a5-332935c829ec\",\"created_at\":\"2016-03-03T11:23:59Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"0f2ca8b3-595a-47c9-8816-7678405e216b@example.com\",\"gender\":null,\"dob\":\"1980-03-03\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/ec96b4b1-399d-4bee-a1a5-332935c829ec\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]},{\"id\":\"d08c285e-fe5b-4e7b-85c2-cc65b13a5355\",\"created_at\":\"2016-03-03T11:23:08Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"5caec2f3-19b5-4033-9f8c-1fb9bd9149d7@example.com\",\"gender\":null,\"dob\":\"1980-03-03\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/d08c285e-fe5b-4e7b-85c2-cc65b13a5355\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]},{\"id\":\"e6b1d0d9-d143-41ae-92da-aaab496e8c00\",\"created_at\":\"2016-03-03T11:03:38Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"a1b92415-98e8-40a4-8dad-a0f4b8e7c535@example.com\",\"gender\":null,\"dob\":\"1980-03-03\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/e6b1d0d9-d143-41ae-92da-aaab496e8c00\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]},{\"id\":\"1d27ef36-6fe7-47e6-a5fe-bfe71da77c7b\",\"created_at\":\"2016-03-02T22:23:54Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"94957f1b-bd5f-4da2-a05b-d4dd6ae72886@example.com\",\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/1d27ef36-6fe7-47e6-a5fe-bfe71da77c7b\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"4dabf603-f541-4671-90f2-d42ad62130de\",\"created_at\":\"2016-03-02T22:23:08Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"c2db8373-6e7d-4531-9c12-6bb3975890ed@example.com\",\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/4dabf603-f541-4671-90f2-d42ad62130de\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"967882ed-a66d-4985-a59b-c7c1a90570f2\",\"created_at\":\"2016-03-02T22:22:33Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"3d4431b5-b3ac-4407-8e92-aeb09de79819@example.com\",\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/967882ed-a66d-4985-a59b-c7c1a90570f2\",\"id_numbers\":[],\"addresses\":[]},{\"id\":\"adc7e830-0e78-4d96-926a-91c4505d3bc3\",\"created_at\":\"2016-03-02T22:16:26Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"5efd0990-0525-435f-8dbe-5dd4af7118ce@example.com\",\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/adc7e830-0e78-4d96-926a-91c4505d3bc3\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]},{\"id\":\"b877e701-9d95-4a2d-8506-5672f9569469\",\"created_at\":\"2016-03-02T22:14:49Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"0fcfd971-d25d-4c6e-b317-51c4ea1a5ac3@example.com\",\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/b877e701-9d95-4a2d-8506-5672f9569469\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]},{\"id\":\"3d466cd2-5e56-44f3-84ba-9897c31d24f4\",\"created_at\":\"2016-03-02T21:58:34Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"baebd243-ace9-43b2-be00-f6d5a69f4739@example.com\",\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/3d466cd2-5e56-44f3-84ba-9897c31d24f4\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]},{\"id\":\"f37a5b4b-9720-4c40-85f5-cea7637e6ebc\",\"created_at\":\"2016-03-02T21:56:05Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"802567a8-7a53-42d7-9767-ad7d98de4eac@example.com\",\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/f37a5b4b-9720-4c40-85f5-cea7637e6ebc\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]},{\"id\":\"84f85370-fba8-432d-a0b2-235ca12adb2d\",\"created_at\":\"2016-03-02T21:52:32Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"364e3760-3f2f-4eba-871d-fc35fe74490f@example.com\",\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/84f85370-fba8-432d-a0b2-235ca12adb2d\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]},{\"id\":\"b64221ed-50a7-499f-95bf-e4013a5ff558\",\"created_at\":\"2016-03-02T21:51:53Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"f250696e-8f18-4e7e-a0e2-287f3b67f59c@example.com\",\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/b64221ed-50a7-499f-95bf-e4013a5ff558\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]}]}");
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v1/applicants/1c9c0377-6e11-4b5c-943e-f6b2a3ab1886"), "{\"id\":\"1c9c0377-6e11-4b5c-943e-f6b2a3ab1886\",\"created_at\":\"2016-03-03T14:31:06Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"da6269f0-ba6f-475f-8f88-41ecdb07b51d@example.com\",\"gender\":null,\"dob\":\"1980-03-02\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/1c9c0377-6e11-4b5c-943e-f6b2a3ab1886\",\"id_numbers\":[],\"addresses\":[]}");
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
	aestNow.set(Calendar.DAY_OF_YEAR, 62);
	applicantCreationRequest.setDateOfBirth(aestNow);
	applicantCreationRequest.setCountry(CountryCode.US);
	String email;
	if (client instanceof FakeHttpClient) {
	    email = "da6269f0-ba6f-475f-8f88-41ecdb07b51d@example.com";
	} else {
	    email = UUID.randomUUID().toString() + "@example.com";
	}
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
