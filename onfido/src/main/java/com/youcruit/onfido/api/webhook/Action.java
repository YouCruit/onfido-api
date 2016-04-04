package com.youcruit.onfido.api.webhook;

import com.google.gson.annotations.SerializedName;

public enum Action {
    @SerializedName("completed")
    COMPLETED,
    @SerializedName("withdrawal")
    WITHDRAWAL
}
