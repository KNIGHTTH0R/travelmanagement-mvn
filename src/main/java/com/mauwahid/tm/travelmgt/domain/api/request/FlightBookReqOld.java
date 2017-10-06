package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightBookReqOld {

    @JsonProperty("flight_key")
    private String flightKey;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("customer_phone")
    private String customerPhone;

    @JsonProperty("customer_email")
    private String customerEmail;

    @JsonProperty("pax_type")
    private String paxType;

    @JsonProperty("pax_title")
    private String paxTitle;

    @JsonProperty("pax_first_name")
    private String paxFirstName;

    @JsonProperty("pax_last_name")
    private String paxLastName;

    @JsonProperty("pax_id_number")
    private String paxIdNumber;

    @JsonProperty("pax_birth_date")
    private String paxBirthDate;

    @JsonProperty("pax_passport")
    private String paxPassport;

    @JsonProperty("pax_passport_expired")
    private String paxPassportExpired;

    @JsonProperty("pax_country")
    private String paxCountry;

    private String margin;


    public String getFlightKey() {
        return flightKey;
    }

    public void setFlightKey(String flightKey) {
        this.flightKey = flightKey;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getPaxType() {
        return paxType;
    }

    public void setPaxType(String paxType) {
        this.paxType = paxType;
    }

    public String getPaxTitle() {
        return paxTitle;
    }

    public void setPaxTitle(String paxTitle) {
        this.paxTitle = paxTitle;
    }

    public String getPaxFirstName() {
        return paxFirstName;
    }

    public void setPaxFirstName(String paxFirstName) {
        this.paxFirstName = paxFirstName;
    }

    public String getPaxLastName() {
        return paxLastName;
    }

    public void setPaxLastName(String paxLastName) {
        this.paxLastName = paxLastName;
    }

    public String getPaxIdNumber() {
        return paxIdNumber;
    }

    public void setPaxIdNumber(String paxIdNumber) {
        this.paxIdNumber = paxIdNumber;
    }

    public String getPaxBirthDate() {
        return paxBirthDate;
    }

    public void setPaxBirthDate(String paxBirthDate) {
        this.paxBirthDate = paxBirthDate;
    }

    public String getPaxPassport() {
        return paxPassport;
    }

    public void setPaxPassport(String paxPassport) {
        this.paxPassport = paxPassport;
    }

    public String getPaxPassportExpired() {
        return paxPassportExpired;
    }

    public void setPaxPassportExpired(String paxPassportExpired) {
        this.paxPassportExpired = paxPassportExpired;
    }

    public String getPaxCountry() {
        return paxCountry;
    }

    public void setPaxCountry(String paxCountry) {
        this.paxCountry = paxCountry;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }
}
