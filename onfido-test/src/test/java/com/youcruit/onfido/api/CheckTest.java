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
import com.youcruit.onfido.api.report.Report;
import com.youcruit.onfido.api.report.ReportClient;
import com.youcruit.onfido.api.report.ReportId;
import com.youcruit.onfido.api.report.ReportStatus;
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
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v2/applicants"), "{\"id\":\"723d3bc3-1a12-4058-8429-b1f536e8af1e\",\"created_at\":\"2016-03-14T11:15:21Z\",\"sandbox\":true,\"title\":null,\"first_name\":\"Foo\",\"middle_name\":\"FB\",\"last_name\":\"Bar\",\"email\":\"a344feff-aedc-49cf-b166-dfba2046e5ee@example.com\",\"gender\":null,\"dob\":\"1980-03-14\",\"telephone\":null,\"mobile\":null,\"country\":\"usa\",\"href\":\"/v2/applicants/723d3bc3-1a12-4058-8429-b1f536e8af1e\",\"id_numbers\":[{\"type\":\"ssn\",\"value\":\"404-35-0728\"}],\"addresses\":[]}");
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v2/applicants/723d3bc3-1a12-4058-8429-b1f536e8af1e/checks"), "{\"id\":\"63b8a616-17fa-48c2-ab8c-52d3b12ed388\",\"created_at\":\"2016-03-14T11:16:16Z\",\"status\":\"awaiting_applicant\",\"redirect_uri\":null,\"type\":\"standard\",\"result\":null,\"sandbox\":true,\"report_type_groups\":[\"2685\"],\"results_uri\":\"https://onfido.com/dashboard/information_requests/324424\",\"download_uri\":\"https://onfido.com/dashboard/pdf/information_requests/324424\",\"form_uri\":null,\"href\":null,\"reports\":[{\"created_at\":\"2016-03-14T11:16:16Z\",\"href\":\"/v2/checks/63b8a616-17fa-48c2-ab8c-52d3b12ed388/reports/60828a0a-d39b-4872-a00c-684fa16b6ed0\",\"id\":\"60828a0a-d39b-4872-a00c-684fa16b6ed0\",\"name\":\"national_criminal\",\"properties\":{},\"result\":null,\"status\":\"awaiting_data\",\"variant\":\"standard\",\"breakdown\":{}},{\"created_at\":\"2016-03-14T11:16:16Z\",\"href\":\"/v2/checks/63b8a616-17fa-48c2-ab8c-52d3b12ed388/reports/562a1893-26ef-4ced-914d-efeba43db74e\",\"id\":\"562a1893-26ef-4ced-914d-efeba43db74e\",\"name\":\"county_criminal\",\"properties\":{},\"result\":null,\"status\":\"awaiting_data\",\"variant\":\"standard\",\"breakdown\":{}},{\"created_at\":\"2016-03-14T11:16:16Z\",\"href\":\"/v2/checks/63b8a616-17fa-48c2-ab8c-52d3b12ed388/reports/ecf53a53-1bda-4454-9be3-eac2bf6f9ca9\",\"id\":\"ecf53a53-1bda-4454-9be3-eac2bf6f9ca9\",\"name\":\"identity\",\"properties\":{},\"result\":null,\"status\":\"awaiting_data\",\"variant\":\"standard\",\"breakdown\":{}}]}");
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v2/applicants/723d3bc3-1a12-4058-8429-b1f536e8af1e/checks/63b8a616-17fa-48c2-ab8c-52d3b12ed388"), "{\"id\":\"63b8a616-17fa-48c2-ab8c-52d3b12ed388\",\"created_at\":\"2016-03-14T11:16:16Z\",\"status\":\"awaiting_applicant\",\"redirect_uri\":null,\"type\":\"standard\",\"result\":null,\"sandbox\":true,\"report_type_groups\":[\"2685\"],\"results_uri\":\"https://onfido.com/dashboard/information_requests/324424\",\"download_uri\":\"https://onfido.com/dashboard/pdf/information_requests/324424\",\"form_uri\":null,\"href\":null,\"reports\":[\"60828a0a-d39b-4872-a00c-684fa16b6ed0\",\"562a1893-26ef-4ced-914d-efeba43db74e\",\"ecf53a53-1bda-4454-9be3-eac2bf6f9ca9\"]}");
	    fakeClient.addResponse(URI.create("https://onfido.com/dashboard/pdf/information_requests/324424.pdf"), "%PDF---------------------------------");
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v2/checks/63b8a616-17fa-48c2-ab8c-52d3b12ed388/reports/60828a0a-d39b-4872-a00c-684fa16b6ed0"), "{\"created_at\":\"2016-03-14T11:16:16Z\",\"href\":\"/v2/checks/63b8a616-17fa-48c2-ab8c-52d3b12ed388/reports/60828a0a-d39b-4872-a00c-684fa16b6ed0\",\"id\":\"60828a0a-d39b-4872-a00c-684fa16b6ed0\",\"name\":\"national_criminal\",\"properties\":{},\"result\":null,\"status\":\"awaiting_data\",\"variant\":\"standard\",\"breakdown\":{}}");
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v2/checks/63b8a616-17fa-48c2-ab8c-52d3b12ed388/reports/562a1893-26ef-4ced-914d-efeba43db74e"), "{\"created_at\":\"2016-03-14T11:16:16Z\",\"href\":\"/v2/checks/63b8a616-17fa-48c2-ab8c-52d3b12ed388/reports/562a1893-26ef-4ced-914d-efeba43db74e\",\"id\":\"562a1893-26ef-4ced-914d-efeba43db74e\",\"name\":\"county_criminal\",\"properties\":{},\"result\":null,\"status\":\"awaiting_data\",\"variant\":\"standard\",\"breakdown\":{}}");
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v2/checks/63b8a616-17fa-48c2-ab8c-52d3b12ed388/reports/ecf53a53-1bda-4454-9be3-eac2bf6f9ca9"), "{\"created_at\":\"2016-03-14T11:16:16Z\",\"href\":\"/v2/checks/63b8a616-17fa-48c2-ab8c-52d3b12ed388/reports/ecf53a53-1bda-4454-9be3-eac2bf6f9ca9\",\"id\":\"ecf53a53-1bda-4454-9be3-eac2bf6f9ca9\",\"name\":\"ssn_trace\",\"properties\":{},\"result\":null,\"status\":\"awaiting_data\",\"variant\":\"standard\",\"breakdown\":{}}");
	}

	// Far from optimal, but since all the tests require creation of a new applicant and doesn't modify it, do them sequentially
	ApplicantId applicantId = createApplicant();
	CheckCreationResponse check = createCheck(applicantId);
	assertEquals(ResultStatus.AWAITING_APPLICANT, check.getStatus());

	Check gotCheck = checkClient.getCheck(applicantId, check.getId());
	assertEquals(ResultStatus.AWAITING_APPLICANT, gotCheck.getStatus());
	assertEquals(null, gotCheck.getResult());

	byte[] pdfReport = reportClient.getPdfReport(check.getDownloadUri());
	String s = new String(pdfReport, 0, 20, "UTF-8");
	assertTrue("Not pdf?", s.startsWith("%PDF"));

	for (String reportId : gotCheck.getReports()) {
	    Report report = reportClient.getReport(gotCheck.getId(), new ReportId(reportId));
	    assertEquals(ReportStatus.AWAITING_DATA, report.getStatus());
	}
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
