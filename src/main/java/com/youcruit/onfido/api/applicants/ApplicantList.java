package com.youcruit.onfido.api.applicants;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplicantList {
    @Expose
    @SerializedName("applicants")
    private List<ApplicantResponse> applicants;

    public List<ApplicantResponse> getApplicants() {
	return applicants;
    }

    public void setApplicants(List<ApplicantResponse> applicants) {
	this.applicants = applicants;
    }
}
