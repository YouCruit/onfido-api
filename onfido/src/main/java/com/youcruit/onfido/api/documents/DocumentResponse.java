package com.youcruit.onfido.api.documents;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.youcruit.onfido.api.applicants.ApplicantId;

public class DocumentResponse {
    private ApplicantId applicantId;
    @Expose
    @SerializedName("id")
    private DocumentId id;
    @Expose
    @SerializedName("created_at")
    private Date createdAt;
    @Expose
    @SerializedName("href")
    private String pathToThis;
    @Expose
    @SerializedName("file_name")
    private String fileName;
    @Expose
    @SerializedName("file_type")
    private FileType fileType;
    @Expose
    @SerializedName("file_size")
    private int fileSize;
    @Expose
    @SerializedName("type")
    private Type type;
    @Expose
    @SerializedName("side")
    private Side side;

    public ApplicantId getApplicantId() {
	return applicantId;
    }

    public void setApplicantId(final ApplicantId applicantId) {
	this.applicantId = applicantId;
    }

    public DocumentId getId() {
	return id;
    }

    public void setId(final DocumentId id) {
	this.id = id;
    }

    public Date getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
	this.createdAt = createdAt;
    }

    public String getPathToThis() {
	return pathToThis;
    }

    public void setPathToThis(final String pathToThis) {
	this.pathToThis = pathToThis;
    }

    public String getFileName() {
	return fileName;
    }

    public void setFileName(final String fileName) {
	this.fileName = fileName;
    }

    public FileType getFileType() {
	return fileType;
    }

    public void setFileType(final FileType fileType) {
	this.fileType = fileType;
    }

    public int getFileSize() {
	return fileSize;
    }

    public void setFileSize(final int fileSize) {
	this.fileSize = fileSize;
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

    @Override
    public boolean equals(final Object o) {
	if (this == o) {
	    return true;
	}
	if (!(o instanceof DocumentResponse)) {
	    return false;
	}

	DocumentResponse that = (DocumentResponse) o;

	if (fileSize != that.fileSize) {
	    return false;
	}
	if (applicantId != null ? !applicantId.equals(that.applicantId) : that.applicantId != null) {
	    return false;
	}
	if (id != null ? !id.equals(that.id) : that.id != null) {
	    return false;
	}
	if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) {
	    return false;
	}
	if (pathToThis != null ? !pathToThis.equals(that.pathToThis) : that.pathToThis != null) {
	    return false;
	}
	if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) {
	    return false;
	}
	if (fileType != that.fileType) {
	    return false;
	}
	if (type != that.type) {
	    return false;
	}
	return side == that.side;

    }

    @Override
    public int hashCode() {
	int result = applicantId != null ? applicantId.hashCode() : 0;
	result = 31 * result + (id != null ? id.hashCode() : 0);
	result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
	result = 31 * result + (pathToThis != null ? pathToThis.hashCode() : 0);
	result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
	result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
	result = 31 * result + fileSize;
	result = 31 * result + (type != null ? type.hashCode() : 0);
	result = 31 * result + (side != null ? side.hashCode() : 0);
	return result;
    }

    @Override
    public String toString() {
	return "DocumentResponse{" +
		"applicantId=" + applicantId +
		", id=" + id +
		", createdAt=" + createdAt +
		", pathToThis='" + pathToThis + '\'' +
		", fileName='" + fileName + '\'' +
		", fileType=" + fileType +
		", fileSize=" + fileSize +
		", type=" + type +
		", side=" + side +
		'}';
    }
}
