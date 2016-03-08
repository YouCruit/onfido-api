package com.youcruit.onfido.api.webhook;

import com.google.gson.annotations.SerializedName;

public enum Status {
    @SerializedName("completion")
    COMPLETED,
    @SerializedName("withdrawal")
    WITHDRAWN,
    @SerializedName("in_progress")
    IN_PROGRESS;
}

