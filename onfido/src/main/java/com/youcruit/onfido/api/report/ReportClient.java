package com.youcruit.onfido.api.report;

import static com.youcruit.onfido.api.http.OnfidoHttpClient.Method.GET;

import java.io.IOException;
import java.net.URI;

import com.youcruit.onfido.api.checks.CheckId;
import com.youcruit.onfido.api.http.OnfidoHttpClient;

public class ReportClient {
    private final OnfidoHttpClient httpClient;

    public ReportClient(OnfidoHttpClient httpClient) {
	this.httpClient = httpClient;
    }

    public Report getReport(CheckId checkId, ReportId reportId) throws IOException {
	URI uri = httpClient.pathToUri("checks", checkId.getId(), "reports", reportId.getId());
	return httpClient.sync(uri, null, GET, Report.class);
    }

    public Report getReport(ReportId reportId) throws IOException {
	URI uri = httpClient.pathToUri("reports", reportId.getId());
	return httpClient.sync(uri, null, GET, Report.class);
    }

    public ReportList listReports(CheckId checkId) throws IOException {
	URI uri = httpClient.pathToUri("checks", checkId.getId());
	return httpClient.sync(uri, null, GET, ReportList.class);
    }

    public byte[] getPdfReport(URI downloadUri) throws IOException {
	URI pdfDownloadUri = URI.create(downloadUri.toString() + ".pdf");
	return httpClient.sync(pdfDownloadUri, null, GET, byte[].class);
    }
}