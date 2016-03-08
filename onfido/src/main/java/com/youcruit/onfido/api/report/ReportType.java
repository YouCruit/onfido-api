package com.youcruit.onfido.api.report;

import static com.youcruit.onfido.api.report.Region.EU;
import static com.youcruit.onfido.api.report.Region.UK;
import static com.youcruit.onfido.api.report.Region.US;
import static java.util.Arrays.asList;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public enum ReportType {
    @SerializedName("identity")
    IDENTITY(UK, US, EU),
    @SerializedName("document")
    DOCUMENT(UK, US, EU),
    @SerializedName("employment")
    EMPLOYMENT(UK, US),
    @SerializedName("education")
    EDUCATION(UK, US),
    @SerializedName("negative_media")
    NEGATIVE_MEDIA(UK),
    @SerializedName("directorship")
    DIRECTORSHIP(UK),
    @SerializedName("crimilar_history")
    CRIMINAL_RECORD(UK, EU),
    @SerializedName("anti_money_laundering")
    ANTI_MONEY_LAUNDERING(UK),
    @SerializedName("street_level")
    STREET_LEVEL(UK),
    @SerializedName("credit")
    CREDIT(UK),
    @SerializedName("eviction")
    EVICTION(US),
    @SerializedName("sex_offender")
    SEX_OFFENDER(US),
    @SerializedName("national_criminal")
    NATIONAL_CRIMINAL(US),
    @SerializedName("county_criminal")
    COUNTY_CRIMINAL(US),
    @SerializedName("state_criminal")
    STATE_CRIMINAL(US),
    @SerializedName("federal_criminal")
    FEDERAL_CRIMINAL(US),
    @SerializedName("drug_test")
    DRUG_TEST(US),
    @SerializedName("watchlist")
    WATCHLIST(US, UK),
    @SerializedName("driving_record")
    DRIVING_RECORD(US);

    public final List<Region> availableInRegions;

    ReportType(Region... availableInRegions) {
	this.availableInRegions = asList(availableInRegions);
    }
}
