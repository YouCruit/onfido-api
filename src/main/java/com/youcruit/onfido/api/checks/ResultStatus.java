package com.youcruit.onfido.api.checks;

import com.google.gson.annotations.SerializedName;

public enum ResultStatus {
    @SerializedName("AWAITING_APPLICANT")
    AWAITING_DATA,
    @SerializedName("COMPLETE")
    COMPLETE;
}
