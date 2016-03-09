package com.youcruit.onfido.api;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.Collections;
import java.util.TimeZone;
import java.util.UUID;

import org.junit.Test;

import com.neovisionaries.i18n.CountryCode;
import com.youcruit.onfido.api.applicants.Applicant;
import com.youcruit.onfido.api.applicants.ApplicantId;
import com.youcruit.onfido.api.applicants.ApplicantResponse;
import com.youcruit.onfido.api.applicants.ApplicantsClient;
import com.youcruit.onfido.api.applicants.IdNumber;
import com.youcruit.onfido.api.applicants.IdNumberType;
import com.youcruit.onfido.api.checks.Check;
import com.youcruit.onfido.api.checks.CheckClient;
import com.youcruit.onfido.api.checks.CheckType;
import com.youcruit.onfido.api.checks.CreateCheckRequest;
import com.youcruit.onfido.api.checks.ReportRequest;
import com.youcruit.onfido.api.http.FakeHttpClient;
import com.youcruit.onfido.api.http.OnfidoHttpClient;
import com.youcruit.onfido.api.report.ReportClient;
import com.youcruit.onfido.api.report.ReportType;

public class CheckTest extends HttpIT {

    private final OnfidoHttpClient client;
    private final ApplicantsClient applicantsClient;
    private final CheckClient checkClient;
    private final ReportClient reportClient;

    public CheckTest(Class<OnfidoHttpClient> httpClientClass) {
	super(httpClientClass);
	client = createClient();
	applicantsClient = new ApplicantsClient(client);
	checkClient = new CheckClient(client);
	reportClient = new ReportClient(client);
    }

    @Test
    public void runAllSequentialTests() throws IOException {
	if (client instanceof FakeHttpClient) {
	    FakeHttpClient fakeClient = (FakeHttpClient) client;
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v1/applicants"), "{\"id\":\"6a8caede-8eba-4455-937b-6ad499c5b222\",\"created_at\":\"2016-03-03T14:12:14Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"6d5665f4-fecb-4d49-9887-973ec220809e@example.com\",\"gender\":null,\"dob\":\"1980-03-03\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/6a8caede-8eba-4455-937b-6ad499c5b222\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]}");
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v1/applicants/6a8caede-8eba-4455-937b-6ad499c5b222/checks"), "{\"id\":\"030857da-fee9-4ac6-a6b5-1c692b402964\",\"created_at\":\"2016-03-03T14:12:38Z\",\"status\":\"in_progress\",\"redirect_uri\":null,\"type\":\"standard\",\"result\":null,\"sandbox\":true,\"report_type_groups\":[\"2685\"],\"results_uri\":\"https://onfido.com/dashboard/information_requests/292393\",\"download_uri\":\"https://onfido.com/dashboard/pdf/information_requests/292393\",\"form_uri\":null,\"href\":null,\"reports\":[{\"created_at\":\"2016-03-03T14:12:38Z\",\"href\":\"/v1/checks/030857da-fee9-4ac6-a6b5-1c692b402964/reports/be22e654-85a7-4edc-ad81-9a8a981316d8\",\"id\":\"be22e654-85a7-4edc-ad81-9a8a981316d8\",\"name\":\"identity\",\"properties\":{\"addresses\":[],\"aliases\":[\"DOE JOE\"]},\"result\":null,\"status\":\"awaiting_approval\",\"variant\":\"standard\",\"breakdown\":{\"ssn\":{\"result\":\"consider\",\"breakdown\":{\"ssn_is_valid\":{\"result\":\"clear\",\"properties\":{}},\"ssn_for_the_given_first_name,_last_name_and_dob\":{\"result\":\"consider\",\"properties\":{}}}},\"mortality\":{\"result\":\"clear\"},\"date_of_birth\":{\"result\":\"unidentified\"}}},{\"created_at\":\"2016-03-03T14:12:38Z\",\"href\":\"/v1/checks/030857da-fee9-4ac6-a6b5-1c692b402964/reports/d138e7cd-16f2-4310-917c-a1e45d8b863d\",\"id\":\"d138e7cd-16f2-4310-917c-a1e45d8b863d\",\"name\":\"county_criminal\",\"properties\":{},\"result\":null,\"status\":\"awaiting_data\",\"variant\":\"standard\",\"breakdown\":{}},{\"created_at\":\"2016-03-03T14:12:38Z\",\"href\":\"/v1/checks/030857da-fee9-4ac6-a6b5-1c692b402964/reports/c1c09ac6-231e-405d-9599-b142fe0492a7\",\"id\":\"c1c09ac6-231e-405d-9599-b142fe0492a7\",\"name\":\"national_criminal\",\"properties\":{\"records\":[]},\"result\":\"clear\",\"status\":\"complete\",\"variant\":\"standard\",\"breakdown\":{\"national_criminal\":{\"result\":\"clear\"}}}]}");
	    fakeClient.addResponse(URI.create("https://onfido.com/dashboard/pdf/information_requests/292393.pdf"), "%PDF---------------------------------");
	}

	// Far from optimal, but since all the tests require creation of a new applicant and doesn't modify it, do them sequentially
	ApplicantId applicantId = createApplicant();
	Check check = createCheck(applicantId);

	byte[] pdfReport = reportClient.getPdfReport(check.getDownloadUri());
	String s = new String(pdfReport, 0, 20, "UTF-8");
	assertTrue("Not pdf?", s.startsWith("%PDF"));
    }

    private Check createCheck(ApplicantId applicantId) throws IOException {
	CreateCheckRequest createCheckRequest = new CreateCheckRequest();
	createCheckRequest.setType(CheckType.STANDARD);

	ReportRequest countyCriminal = new ReportRequest();
	countyCriminal.setReportType(ReportType.COUNTY_CRIMINAL);
	ReportRequest nationalCriminal = new ReportRequest();
	nationalCriminal.setReportType(ReportType.NATIONAL_CRIMINAL);
	ReportRequest identity = new ReportRequest();
	identity.setReportType(ReportType.IDENTITY);
	createCheckRequest.setReportRequests(asList(nationalCriminal, countyCriminal, identity));

	return checkClient.createCheck(createCheckRequest, applicantId);
    }

    public ApplicantId createApplicant() throws IOException {
	Applicant applicantCreationRequest = new Applicant();
	applicantCreationRequest.setFirstName("Foo");
	applicantCreationRequest.setLastName("Bar");
	applicantCreationRequest.setMiddleName("FB");
	String email = UUID.randomUUID().toString() + "@example.com";

	IdNumber ssn = new IdNumber();
	ssn.setType(IdNumberType.SSN);
	ssn.setValue("404-35-0728");


	applicantCreationRequest.setIdNumbers(Collections.singletonList(ssn));
	applicantCreationRequest.setCountry(CountryCode.US);
	applicantCreationRequest.setEmail(email);
	Calendar aestNow = Calendar.getInstance(TimeZone.getTimeZone("AEST"));
	aestNow.set(Calendar.YEAR, 1980);
	applicantCreationRequest.setDateOfBirth(aestNow);

	ApplicantResponse applicant = applicantsClient.createApplicant(applicantCreationRequest);
	return applicant.getId();
    }
}
