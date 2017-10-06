package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightBookReq  {

    @JsonProperty("api_source")
    private String apiName;

    @JsonProperty("flight_key")
    private String flightKey;

    @JsonProperty("airline")
    private String airlineId;

    @JsonProperty("adult_title1")
    private String adultTitle1;

    @JsonProperty("adult_name1")
    private String adultName1;

    @JsonProperty("adult_spceial_req1")
    private String adultSpecialRequest1;

    @JsonProperty("adult_title2")
    private String adultTitle2;

    @JsonProperty("adult_name2")
    private String adultName2;

    @JsonProperty("adult_special_req2")
    private String adultSpecialRequest2;

    @JsonProperty("contact_title")
    private String contactTitle;

    @JsonProperty("contact_name")
    private String contactName;

    @JsonProperty("contact_phone")
    private String contactPhone;

    @JsonProperty("child_title1")
    private String childTitle1;

    @JsonProperty("child_name1")
    private String childName1;

    @JsonProperty("child_birth_date1")
    private String childBirthDate1;

    @JsonProperty("child_id1")
    private String childId1;

    @JsonProperty("child_special_req1")
    private String childSpecialRequest1;

    @JsonProperty("infant_title1")
    private String infantTitle1;

    @JsonProperty("infant_name1")
    private String infantName1;

    @JsonProperty("infant_birth_date1")
    private String infantBirthDate1;

    @JsonProperty("infant_id1")
    private String infantId1;

    @JsonProperty("infant_special_req1")
    private String infantSpecialRequest1;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getFlightKey() {
        return flightKey;
    }

    public void setFlightKey(String flightKey) {
        this.flightKey = flightKey;
    }

    public String getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(String airlineId) {
        this.airlineId = airlineId;
    }

    public String getAdultTitle1() {
        return adultTitle1;
    }

    public void setAdultTitle1(String adultTitle1) {
        this.adultTitle1 = adultTitle1;
    }

    public String getAdultName1() {
        return adultName1;
    }

    public void setAdultName1(String adultName1) {
        this.adultName1 = adultName1;
    }

    public String getAdultSpecialRequest1() {
        return adultSpecialRequest1;
    }

    public void setAdultSpecialRequest1(String adultSpecialRequest1) {
        this.adultSpecialRequest1 = adultSpecialRequest1;
    }

    public String getAdultTitle2() {
        return adultTitle2;
    }

    public void setAdultTitle2(String adultTitle2) {
        this.adultTitle2 = adultTitle2;
    }

    public String getAdultName2() {
        return adultName2;
    }

    public void setAdultName2(String adultName2) {
        this.adultName2 = adultName2;
    }

    public String getAdultSpecialRequest2() {
        return adultSpecialRequest2;
    }

    public void setAdultSpecialRequest2(String adultSpecialRequest2) {
        this.adultSpecialRequest2 = adultSpecialRequest2;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getChildTitle1() {
        return childTitle1;
    }

    public void setChildTitle1(String childTitle1) {
        this.childTitle1 = childTitle1;
    }

    public String getChildName1() {
        return childName1;
    }

    public void setChildName1(String childName1) {
        this.childName1 = childName1;
    }

    public String getChildBirthDate1() {
        return childBirthDate1;
    }

    public void setChildBirthDate1(String childBirthDate1) {
        this.childBirthDate1 = childBirthDate1;
    }

    public String getChildId1() {
        return childId1;
    }

    public void setChildId1(String childId1) {
        this.childId1 = childId1;
    }

    public String getChildSpecialRequest1() {
        return childSpecialRequest1;
    }

    public void setChildSpecialRequest1(String childSpecialRequest1) {
        this.childSpecialRequest1 = childSpecialRequest1;
    }

    public String getInfantTitle1() {
        return infantTitle1;
    }

    public void setInfantTitle1(String infantTitle1) {
        this.infantTitle1 = infantTitle1;
    }

    public String getInfantName1() {
        return infantName1;
    }

    public void setInfantName1(String infantName1) {
        this.infantName1 = infantName1;
    }

    public String getInfantBirthDate1() {
        return infantBirthDate1;
    }

    public void setInfantBirthDate1(String infantBirthDate1) {
        this.infantBirthDate1 = infantBirthDate1;
    }

    public String getInfantId1() {
        return infantId1;
    }

    public void setInfantId1(String infantId1) {
        this.infantId1 = infantId1;
    }

    public String getInfantSpecialRequest1() {
        return infantSpecialRequest1;
    }

    public void setInfantSpecialRequest1(String infantSpecialRequest1) {
        this.infantSpecialRequest1 = infantSpecialRequest1;
    }
}

