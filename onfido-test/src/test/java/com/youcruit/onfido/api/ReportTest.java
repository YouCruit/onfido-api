package com.youcruit.onfido.api;

import java.io.IOException;
import java.net.URI;

import org.junit.Test;

import com.youcruit.onfido.api.applicants.ApplicantsClient;
import com.youcruit.onfido.api.checks.CheckClient;
import com.youcruit.onfido.api.checks.CheckId;
import com.youcruit.onfido.api.http.FakeHttpClient;
import com.youcruit.onfido.api.http.OnfidoHttpClient;
import com.youcruit.onfido.api.report.Report;
import com.youcruit.onfido.api.report.ReportClient;
import com.youcruit.onfido.api.report.ReportId;

public class ReportTest extends HttpIT {

    private final OnfidoHttpClient client;
    private final ApplicantsClient applicantsClient;
    private final CheckClient checkClient;
    private final ReportClient reportClient;

    public ReportTest(Class<OnfidoHttpClient> httpClientClass) {
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
	    fakeClient.addResponse(URI.create("https://api.onfido.com/v2/checks/4cb40a5d-e74a-40b1-9252-15cc39df212f/reports/f2a664ce-ed8c-49ac-94a4-1b2ad3fa69a6"), "{\"created_at\":\"2016-03-11T11:56:18Z\",\"href\":\"/v2/checks/4cb40a5d-e74a-40b1-9252-15cc39df212f/reports/f2a664ce-ed8c-49ac-94a4-1b2ad3fa69a6\",\"id\":\"f2a664ce-ed8c-49ac-94a4-1b2ad3fa69a6\",\"name\":\"national_criminal\",\"properties\":{\"records\":[]},\"result\":\"clear\",\"status\":\"complete\",\"variant\":\"standard\",\"breakdown\":{\"national_criminal\":{\"result\":\"clear\"}}}");
	}

	Report report = reportClient.getReport(new CheckId("4cb40a5d-e74a-40b1-9252-15cc39df212f"), new ReportId("f2a664ce-ed8c-49ac-94a4-1b2ad3fa69a6"));
    }
}
