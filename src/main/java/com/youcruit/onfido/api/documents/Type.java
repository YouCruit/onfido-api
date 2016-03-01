package com.youcruit.onfido.api.documents;

import com.google.gson.annotations.SerializedName;

public enum Type {
    @SerializedName(Constants.PASSPORT)
    PASSPORT(Constants.PASSPORT),
    @SerializedName(Constants.NATIONAL_IDENTITY_CARD)
    NATIONAL_IDENTITY_CARD(Constants.NATIONAL_IDENTITY_CARD),
    @SerializedName(Constants.WORK_PERMIT)
    WORK_PERMIT(Constants.WORK_PERMIT),
    @SerializedName(Constants.DRIVING_LICENSE)
    DRIVING_LICENSE(Constants.DRIVING_LICENSE),
    @SerializedName(Constants.NATIONAL_INSURANCE)
    NATIONAL_INSURANCE(Constants.NATIONAL_INSURANCE),
    @SerializedName(Constants.BIRTH_CERTIFICATE)
    BIRTH_CERTIFICATE(Constants.BIRTH_CERTIFICATE),
    @SerializedName(Constants.BANK_STATEMENT)
    BANK_STATEMENT(Constants.BANK_STATEMENT),
    @SerializedName(Constants.UNKNOWN)
    UNKNOWN(Constants.UNKNOWN);

    final String typeName;

    Type(final String typeName) {
	this.typeName = typeName;
    }

    private static class Constants {
	public static final String PASSPORT = "passport";
	public static final String NATIONAL_IDENTITY_CARD = "national_identity_card";
	public static final String WORK_PERMIT = "work_permit";
	public static final String DRIVING_LICENSE = "driving_license";
	public static final String NATIONAL_INSURANCE = "national_insurance";
	public static final String BIRTH_CERTIFICATE = "birth_certificate";
	public static final String BANK_STATEMENT = "bank_statement";
	public static final String UNKNOWN = "unknown";
    }
}
