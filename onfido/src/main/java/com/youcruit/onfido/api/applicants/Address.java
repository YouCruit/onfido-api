package com.youcruit.onfido.api.applicants;

import java.util.Calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.neovisionaries.i18n.CountryCode;

public class Address {
    @Expose
    @SerializedName("building_number")
    private String buildingNumber;
    @Expose
    @SerializedName("street")
    private String street;
    @Expose
    @SerializedName("town")
    private String town;
    @Expose
    @SerializedName("postcode")
    private String postcode;
    @Expose
    @SerializedName("country")
    private CountryCode country;
    @Expose
    @SerializedName("start_date")
    private Calendar startDate;

    public String getBuildingNumber() {
	return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
	this.buildingNumber = buildingNumber;
    }

    public String getStreet() {
	return street;
    }

    public void setStreet(String street) {
	this.street = street;
    }

    public String getTown() {
	return town;
    }

    public void setTown(String town) {
	this.town = town;
    }

    public String getPostcode() {
	return postcode;
    }

    public void setPostcode(String postcode) {
	this.postcode = postcode;
    }

    public CountryCode getCountry() {
	return country;
    }

    public void setCountry(CountryCode country) {
	this.country = country;
    }

    public Calendar getStartDate() {
	return startDate;
    }

    public void setStartDate(Calendar startDate) {
	this.startDate = startDate;
    }

    @Override
    public String toString() {
	return "Address{" +
		"buildingNumber='" + buildingNumber + '\'' +
		", street='" + street + '\'' +
		", town='" + town + '\'' +
		", postcode='" + postcode + '\'' +
		", country=" + country +
		", startDate=" + startDate +
		'}';
    }
}
