package com.youcruit.onfido.api.documents;

import java.io.IOException;
import java.net.URI;

import com.youcruit.onfido.api.http.OnfidoHttpClient;
import com.youcruit.onfido.api.http.OnfidoHttpClient.Method;
import com.youcruit.onfido.api.http.Part;

public class DocumentClient {
    private final OnfidoHttpClient httpClient;

    public DocumentClient(OnfidoHttpClient httpClient) {
	this.httpClient = httpClient;
    }

    public DocumentResponse create(CreateDocumentRequest createDocumentRequest) throws IOException {
	final Part[] parts;
	if (createDocumentRequest.getSide() == null) {
	    parts = new Part[2];
	} else {
	    parts = new Part[3];
	    parts[2] = new Part("side", createDocumentRequest.getSide().sideName);
	}
	parts[0] = new Part("file", createDocumentRequest.getFileType().contentType, createDocumentRequest.getDocumentData());
	parts[1] = new Part("type", createDocumentRequest.getType().typeName);
	URI uri = httpClient.pathToUri("documents", createDocumentRequest.getApplicantId().getId(), "documents");
	return httpClient.sync(uri, parts, Method.POST, DocumentResponse.class);
    }
}
