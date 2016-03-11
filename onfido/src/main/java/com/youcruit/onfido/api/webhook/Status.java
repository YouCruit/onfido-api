package com.youcruit.onfido.api.webhook;

import com.google.gson.annotations.SerializedName;

public enum Status {
    @SerializedName("complete")
    COMPLETED,
    @SerializedName("withdrawn")
    WITHDRAWN,
    @SerializedName("in_progress")
    IN_PROGRESS;
}

