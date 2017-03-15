package com.youcruit.onfido.api.report;

import com.google.gson.annotations.SerializedName;

public enum Variant {
    @SerializedName("basic")
    BASIC,
    @SerializedName("standard")
    STANDARD,
    @SerializedName("enhanced")
    ENHANCED,
    @SerializedName("kyc")
    KYC;
}
