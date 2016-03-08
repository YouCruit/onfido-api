package com.youcruit.onfido.api.applicants;

import com.google.gson.annotations.SerializedName;

public enum IdNumberType {
    @SerializedName("nino")
    NINO,
    @SerializedName("ssn")
    SSN,
    @SerializedName("driving_license")
    DRIVING_LICENSE;
}
