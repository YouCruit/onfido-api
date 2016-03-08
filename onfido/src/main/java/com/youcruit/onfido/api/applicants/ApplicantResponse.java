package com.youcruit.onfido.api.applicants;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplicantResponse extends Applicant {

    @Expose
    @SerializedName("id")
    private ApplicantId id;
    @Expose
    @SerializedName("created_at")
    private Date createdAt;
    @Expose
    @SerializedName("href")
    private String pathToThis;

    public ApplicantId getId() {
	return id;
    }

    public void setId(ApplicantId id) {
	this.id = id;
    }

    public Date getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
    }

    public String getPathToThis() {
	return pathToThis;
    }

    public void setPathToThis(String pathToThis) {
	this.pathToThis = pathToThis;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof ApplicantResponse)) return false;
	if (!super.equals(o)) return false;

	ApplicantResponse that = (ApplicantResponse) o;

	if (id != null ? !id.equals(that.id) : that.id != null) return false;
	if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
	return pathToThis != null ? pathToThis.equals(that.pathToThis) : that.pathToThis == null;

    }

    @Override
    public int hashCode() {
	int result = super.hashCode();
	result = 31 * result + (id != null ? id.hashCode() : 0);
	result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
	result = 31 * result + (pathToThis != null ? pathToThis.hashCode() : 0);
	return result;
    }

    @Override
    public String toString() {
	return "ApplicantResponse{" +
		"id='" + id + '\'' +
		", createdAt=" + createdAt +
		", staticHref='" + pathToThis + '\'' +
		"} " + super.toString();
    }
}
