package com.youcruit.onfido.api.documents;

import java.util.Arrays;

import com.youcruit.onfido.api.applicants.ApplicantId;

public class CreateDocumentRequest {
    private byte[] documentData;
    private FileType fileType;
    private Type type;
    private Side side;
    private ApplicantId applicantId;

    public byte[] getDocumentData() {
	return documentData;
    }

    public void setDocumentData(final byte[] documentData) {
	this.documentData = documentData;
    }

    public Type getType() {
	return type;
    }

    public void setType(final Type type) {
	this.type = type;
    }

    public Side getSide() {
	return side;
    }

    public void setSide(final Side side) {
	this.side = side;
    }

    public ApplicantId getApplicantId() {
	return applicantId;
    }

    public void setApplicantId(final ApplicantId applicantId) {
	this.applicantId = applicantId;
    }

    public FileType getFileType() {
	return fileType;
    }

    public void setFileType(final FileType fileType) {
	this.fileType = fileType;
    }

    @Override
    public String toString() {
	return "CreateDocumentRequest{" +
		"documentData=" + Arrays.toString(documentData) +
		", fileType=" + fileType +
		", type=" + type +
		", side=" + side +
		", applicantId=" + applicantId +
		'}';
    }
}
