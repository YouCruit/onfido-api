package com.youcruit.onfido.api.report;

import static com.youcruit.onfido.api.report.ReportType.ANTI_MONEY_LAUNDERING;
import static com.youcruit.onfido.api.report.ReportType.COUNTY_CRIMINAL;
import static com.youcruit.onfido.api.report.ReportType.CREDIT;
import static com.youcruit.onfido.api.report.ReportType.DOCUMENT;
import static com.youcruit.onfido.api.report.ReportType.DRIVING_RECORD;
import static com.youcruit.onfido.api.report.ReportType.DRUG_TEST;
import static com.youcruit.onfido.api.report.ReportType.EVICTION;
import static com.youcruit.onfido.api.report.ReportType.FEDERAL_CRIMINAL;
import static com.youcruit.onfido.api.report.ReportType.IDENTITY;
import static com.youcruit.onfido.api.report.ReportType.NATIONAL_CRIMINAL;
import static com.youcruit.onfido.api.report.ReportType.SEX_OFFENDER;
import static com.youcruit.onfido.api.report.ReportType.STATE_CRIMINAL;
import static com.youcruit.onfido.api.report.ReportType.STREET_LEVEL;
import static com.youcruit.onfido.api.report.ReportType.WATCHLIST;

import java.util.Arrays;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public enum BreakdownFieldType {

    @SerializedName("address")
    ADDRESS(IDENTITY, ANTI_MONEY_LAUNDERING, STREET_LEVEL),
    @SerializedName("insolvency")
    CREDIT_REPORT(CREDIT),
    @SerializedName("date_of_birth")
    DATE_OF_BIRTH(IDENTITY, ANTI_MONEY_LAUNDERING, SEX_OFFENDER),
    @SerializedName("mortality")
    MORTALITY_BREAKDOWN(IDENTITY),
    @SerializedName("ssn")
    SSN(IDENTITY),
    @SerializedName("data_integrity")
    DATA_INTEGRITY(DOCUMENT),
    @SerializedName("police_record")
    POLICE_RECORD(DOCUMENT),
    @SerializedName("mortality")
    MORTALITY(ANTI_MONEY_LAUNDERING),
    @SerializedName("sanctions_and_politically_exposed_persons")
    SANCTIONS_AND_POLITICALLY_EXPOSED_PERSONS(ANTI_MONEY_LAUNDERING, WATCHLIST),
    @SerializedName("passport")
    PASSPORT(ANTI_MONEY_LAUNDERING),
    @SerializedName("driving_license")
    DRIVING_LICENSE(ANTI_MONEY_LAUNDERING, DRIVING_RECORD),
    @SerializedName("national_criminal")
    NATIONAL_CRIMINAL_BREAKDOWN(NATIONAL_CRIMINAL),
    @SerializedName("county_criminal")
    COUNTY_CRIMINAL_BREAKDOWN(COUNTY_CRIMINAL),
    @SerializedName("full_name")
    FULL_NAME(SEX_OFFENDER),
    @SerializedName("known_alias")
    KNOWN_ALIAS(SEX_OFFENDER),
    @SerializedName("year_of_birth")
    YEAR_OF_BIRTH(SEX_OFFENDER),
    @SerializedName("age")
    AGE(SEX_OFFENDER),
    @SerializedName("eviction")
    EVICTION_BREAKDOWN(EVICTION),
    @SerializedName("driving_restrictions")
    DRIVING_RESTRICTIONS(DRIVING_RECORD),
    @SerializedName("driving_violations")
    DRIVING_VIOLATIONS(DRIVING_RECORD),
    @SerializedName("driving_accidents")
    DRIVING_ACCIDENTS(DRIVING_RECORD),
    @SerializedName("state_criminal")
    STATE_CRIMINAL_BREAKDOWN(STATE_CRIMINAL),
    @SerializedName("federal_criminal")
    FEDERAL_CRIMINAL_BREAKDOWN(FEDERAL_CRIMINAL),
    @SerializedName("drug_test")
    DRUG_TEST_BREAKDOWN(DRUG_TEST),
    @SerializedName("watchlist")
    WATCH_LIST_BREAKDOWN(WATCHLIST)
    ;

    public final List<ReportType> reportTypes;

    BreakdownFieldType(ReportType... reportTypes) {
	this.reportTypes = Arrays.asList(reportTypes);
    }
}
