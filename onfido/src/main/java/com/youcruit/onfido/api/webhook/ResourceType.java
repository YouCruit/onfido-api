package com.youcruit.onfido.api.webhook;

import com.google.gson.annotations.SerializedName;

public enum ResourceType {
    @SerializedName("report")
    REPORT,
    @SerializedName("check")
    CHECK
}
