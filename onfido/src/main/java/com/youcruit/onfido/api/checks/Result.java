package com.youcruit.onfido.api.checks;

import com.google.gson.annotations.SerializedName;

public enum Result {
    @SerializedName("clear")
    CLEAR,
    @SerializedName("consider")
    CONSIDER,
    @SerializedName("unidentified")
    UNIDENTIFIED

}
