package com.youcruit.onfido.api.report;

import static com.youcruit.onfido.api.report.ReportType.COUNTY_CRIMINAL;
import static com.youcruit.onfido.api.report.ReportType.CREDIT;
import static com.youcruit.onfido.api.report.ReportType.DRUG_TEST;
import static com.youcruit.onfido.api.report.ReportType.EVICTION;
import static com.youcruit.onfido.api.report.ReportType.FEDERAL_CRIMINAL;
import static com.youcruit.onfido.api.report.ReportType.IDENTITY;
import static com.youcruit.onfido.api.report.ReportType.NATIONAL_CRIMINAL;
import static com.youcruit.onfido.api.report.ReportType.SEX_OFFENDER;
import static com.youcruit.onfido.api.report.ReportType.STATE_CRIMINAL;
import static com.youcruit.onfido.api.report.ReportType.WATCHLIST;

import java.util.Arrays;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public enum PropertyFieldType {
    @SerializedName("addresses")
    ADDRESSES(IDENTITY),
    @SerializedName("phone_numbers")
    PHONE_NUMBERS(IDENTITY),
    @SerializedName("records")
    RECORDS(NATIONAL_CRIMINAL, COUNTY_CRIMINAL, SEX_OFFENDER, EVICTION, STATE_CRIMINAL, FEDERAL_CRIMINAL, WATCHLIST),
    @SerializedName("results")
    RESULTS(DRUG_TEST),
    @SerializedName("number_of_ccjs")
    NUMBER_OF_CCSJS(CREDIT),
    @SerializedName("sum_of_ccj_value")
    SUM_OF_CCJ_VALUE(CREDIT),
    @SerializedName("number_of_bankruptcies")
    NUMBER_OF_BANKRUPCIES(CREDIT),
    @SerializedName("number_of_ivas")
    NUMBER_OF_IVAS(CREDIT)
    ;

    public final List<ReportType> reportTypes;

    PropertyFieldType(ReportType... reportTypes) {
	this.reportTypes = Arrays.asList(reportTypes);
    }
}
