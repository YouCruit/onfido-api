package com.youcruit.onfido.api.applicants;

import java.util.Calendar;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.neovisionaries.i18n.CountryCode;

public class Applicant {
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("first_name")
    private String firstName;
    @Expose
    @SerializedName("middle_name")
    private String middleName;
    @Expose
    @SerializedName("last_name")
    private String lastName;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("gender")
    private Gender gender;
    @Expose
    @SerializedName("dob")
    private Calendar dateOfBirth;
    @Expose
    @SerializedName("telephone")
    private String telephone;
    @Expose
    @SerializedName("mobile")
    private String mobile;
    @Expose
    @SerializedName("country")
    private CountryCode country;
    @Expose
    @SerializedName("addresses")
    private List<Address> addresses;
    @Expose
    @SerializedName("id_numbers")
    private List<String> idNumbers;

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getMiddleName() {
	return middleName;
    }

    public void setMiddleName(String middleName) {
	this.middleName = middleName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Gender getGender() {
	return gender;
    }

    public void setGender(Gender gender) {
	this.gender = gender;
    }

    public Calendar getDateOfBirth() {
	return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    public String getTelephone() {
	return telephone;
    }

    public void setTelephone(String telephone) {
	this.telephone = telephone;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public CountryCode getCountry() {
	return country;
    }

    public void setCountry(CountryCode country) {
	this.country = country;
    }

    public List<Address> getAddresses() {
	return addresses;
    }

    public void setAddresses(List<Address> addresses) {
	this.addresses = addresses;
    }

    public List<String> getIdNumbers() {
	return idNumbers;
    }

    public void setIdNumbers(List<String> idNumbers) {
	this.idNumbers = idNumbers;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Applicant)) return false;

	Applicant applicant = (Applicant) o;

	if (title != null ? !title.equals(applicant.title) : applicant.title != null) return false;
	if (firstName != null ? !firstName.equals(applicant.firstName) : applicant.firstName != null) return false;
	if (middleName != null ? !middleName.equals(applicant.middleName) : applicant.middleName != null) return false;
	if (lastName != null ? !lastName.equals(applicant.lastName) : applicant.lastName != null) return false;
	if (email != null ? !email.equals(applicant.email) : applicant.email != null) return false;
	if (gender != applicant.gender) return false;
	if (dateOfBirth != null ? !dateOfBirth.equals(applicant.dateOfBirth) : applicant.dateOfBirth != null) return false;
	if (telephone != null ? !telephone.equals(applicant.telephone) : applicant.telephone != null) return false;
	if (mobile != null ? !mobile.equals(applicant.mobile) : applicant.mobile != null) return false;
	if (country != applicant.country) return false;
	if (addresses != null ? !addresses.equals(applicant.addresses) : applicant.addresses != null) return false;
	return idNumbers != null ? idNumbers.equals(applicant.idNumbers) : applicant.idNumbers == null;

    }

    @Override
    public int hashCode() {
	int result = title != null ? title.hashCode() : 0;
	result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
	result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
	result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
	result = 31 * result + (email != null ? email.hashCode() : 0);
	result = 31 * result + (gender != null ? gender.hashCode() : 0);
	result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
	result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
	result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
	result = 31 * result + (country != null ? country.hashCode() : 0);
	result = 31 * result + (addresses != null ? addresses.hashCode() : 0);
	result = 31 * result + (idNumbers != null ? idNumbers.hashCode() : 0);
	return result;
    }

    @Override
    public String toString() {
	return "Applicant{" +
		"title='" + title + '\'' +
		", firstName='" + firstName + '\'' +
		", middleName='" + middleName + '\'' +
		", lastName='" + lastName + '\'' +
		", email='" + email + '\'' +
		", gender=" + gender +
		", dateOfBirth=" + dateOfBirth +
		", telephone='" + telephone + '\'' +
		", mobile='" + mobile + '\'' +
		", country=" + country +
		", addresses=" + addresses +
		", idNumbers=" + idNumbers +
		'}';
    }
}
