package com.youcruit.onfido.api.report;

import com.google.gson.annotations.SerializedName;

public enum ReportStatus {
    @SerializedName("awaiting_data")
    AWAITING_DATA,
    @SerializedName("complete")
    COMPLETE;
}
