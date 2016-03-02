package com.youcruit.onfido.api.webhook;

import com.google.gson.annotations.SerializedName;

public enum Event {
    @SerializedName("report completion")
    REPORT_COMPLETION,
    @SerializedName("report withdrawal")
    REPORT_WITHDRAWAL,
    @SerializedName("check in progress")
    CHECK_COMPLETION,
    @SerializedName("check completion")
    CHECK_IN_PROGRESS
}
