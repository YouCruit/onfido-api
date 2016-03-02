package com.youcruit.onfido.api.report;

import com.google.gson.annotations.SerializedName;

public enum Option {
    @SerializedName("volunteer")
    VOLUNTEER,
    @SerializedName("speedy")
    SPEEDY,
    @SerializedName("adults_barred_list")
    ADULTS_BARRED_LIST,
    @SerializedName("children_barred_list")
    CHILDREN_BARRED_LIST
}
