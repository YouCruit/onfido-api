package com.youcruit.onfido.api;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
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
import com.youcruit.onfido.api.checks.CheckCreationResponse;
import com.youcruit.onfido.api.checks.CheckType;
import com.youcruit.onfido.api.checks.CreateCheckRequest;
import com.youcruit.onfido.api.checks.ReportRequest;
import com.youcruit.onfido.api.checks.ResultStatus;
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
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v1/applicants"), "{\"id\":\"7b60944c-0e26-4c86-bf49-6fdd9df26e9f\",\"created_at\":\"2016-03-14T10:42:13Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"2dffb03a-f520-495b-9c6c-15c9823b577a@example.com\",\"gender\":null,\"dob\":\"1980-03-14\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v1/applicants/7b60944c-0e26-4c86-bf49-6fdd9df26e9f\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]}");
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v1/applicants/7b60944c-0e26-4c86-bf49-6fdd9df26e9f/checks"), "{\"id\":\"be77adc0-1197-4cc1-a2b0-bc0e277da747\",\"created_at\":\"2016-03-14T10:42:28Z\",\"status\":\"awaiting_applicant\",\"redirect_uri\":null,\"type\":\"standard\",\"result\":null,\"sandbox\":true,\"report_type_groups\":[\"2685\"],\"results_uri\":\"https://onfido.com/dashboard/information_requests/324364\",\"download_uri\":\"https://onfido.com/dashboard/pdf/information_requests/324364\",\"form_uri\":null,\"href\":null,\"reports\":[{\"created_at\":\"2016-03-14T10:42:28Z\",\"href\":\"/v1/checks/be77adc0-1197-4cc1-a2b0-bc0e277da747/reports/ae4048a2-fcae-49d9-bd6d-835932b3dc50\",\"id\":\"ae4048a2-fcae-49d9-bd6d-835932b3dc50\",\"name\":\"national_criminal\",\"properties\":{},\"result\":null,\"status\":\"awaiting_data\",\"variant\":\"standard\",\"breakdown\":{}},{\"created_at\":\"2016-03-14T10:42:28Z\",\"href\":\"/v1/checks/be77adc0-1197-4cc1-a2b0-bc0e277da747/reports/f0992591-e46b-4d16-af06-c9219c9a283e\",\"id\":\"f0992591-e46b-4d16-af06-c9219c9a283e\",\"name\":\"county_criminal\",\"properties\":{},\"result\":null,\"status\":\"awaiting_data\",\"variant\":\"standard\",\"breakdown\":{}},{\"created_at\":\"2016-03-14T10:42:28Z\",\"href\":\"/v1/checks/be77adc0-1197-4cc1-a2b0-bc0e277da747/reports/21b1d02a-8f57-4713-b44a-6a6a7045dd36\",\"id\":\"21b1d02a-8f57-4713-b44a-6a6a7045dd36\",\"name\":\"identity\",\"properties\":{},\"result\":null,\"status\":\"awaiting_data\",\"variant\":\"standard\",\"breakdown\":{}}]}");
	    fakeClient.addResponse(URI.create("https://onfido.com/dashboard/pdf/information_requests/324364.pdf"), "%PDF---------------------------------");
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v1/applicants/7b60944c-0e26-4c86-bf49-6fdd9df26e9f/checks/be77adc0-1197-4cc1-a2b0-bc0e277da747"), "{\"id\":\"be77adc0-1197-4cc1-a2b0-bc0e277da747\",\"created_at\":\"2016-03-14T10:42:28Z\",\"status\":\"awaiting_applicant\",\"redirect_uri\":null,\"type\":\"standard\",\"result\":null,\"sandbox\":true,\"report_type_groups\":[\"2685\"],\"results_uri\":\"https://onfido.com/dashboard/information_requests/324364\",\"download_uri\":\"https://onfido.com/dashboard/pdf/information_requests/324364\",\"form_uri\":null,\"href\":null,\"reports\":[\"ae4048a2-fcae-49d9-bd6d-835932b3dc50\",\"f0992591-e46b-4d16-af06-c9219c9a283e\",\"21b1d02a-8f57-4713-b44a-6a6a7045dd36\"]}");
	}

	// Far from optimal, but since all the tests require creation of a new applicant and doesn't modify it, do them sequentially
	ApplicantId applicantId = createApplicant();
	CheckCreationResponse check = createCheck(applicantId);
	assertEquals(ResultStatus.AWAITING_DATA, check.getStatus());

	byte[] pdfReport = reportClient.getPdfReport(check.getDownloadUri());
	String s = new String(pdfReport, 0, 20, "UTF-8");
	assertTrue("Not pdf?", s.startsWith("%PDF"));
	Check gotCheck = checkClient.getCheck(applicantId, check.getId());
	assertEquals(ResultStatus.AWAITING_DATA, gotCheck.getStatus());
	assertEquals(null, gotCheck.getResult());
    }

    private CheckCreationResponse createCheck(ApplicantId applicantId) throws IOException {
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
