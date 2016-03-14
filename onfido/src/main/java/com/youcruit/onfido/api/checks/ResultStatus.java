package com.youcruit.onfido.api.checks;

import com.google.gson.annotations.SerializedName;

public enum ResultStatus {
    @SerializedName("awaiting_applicant")
    AWAITING_APPLICANT,
    @SerializedName("in_progress")
    IN_PROGRESS,
    @SerializedName("complete")
    COMPLETE;
}
