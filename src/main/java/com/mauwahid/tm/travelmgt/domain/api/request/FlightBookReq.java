package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightBookReq  {

    @JsonProperty("api_source")
    private String apiName;

    @JsonProperty("flight_key")
    private String flightKey;

    @JsonProperty("travel_id")
    private String travelId;

    @JsonProperty("class_key")
    private String classKey;

    @JsonProperty("airline")
    private String airlineId;

    @JsonProperty("adult_title1")
    private String adultTitle1;

    @JsonProperty("adult_name1")
    private String adultName1;

    @JsonProperty("adult_id_number1")
    private String adultIDNumber1;

    @JsonProperty("adult_birthdate1")
    private String adultBirthDate1;

    @JsonProperty("adult_passport1")
    private String adultPassport1;

    @JsonProperty("adult_passport_expired1")
    private String adultPassportExpired1;

    @JsonProperty("adult_country1")
    private String adultCountry1;

    @JsonProperty("adult_spceial_req1")
    private String adultSpecialRequest1;

    @JsonProperty("adult_title2")
    private String adultTitle2;

    @JsonProperty("adult_name2")
    private String adultName2;

    @JsonProperty("adult_id_number2")
    private String adultIDNumber2;

    @JsonProperty("adult_birthdate2")
    private String adultBirthDate2;

    @JsonProperty("adult_passport2")
    private String adultPassport2;

    @JsonProperty("adult_passport_expired2")
    private String adultPassportExpired2;

    @JsonProperty("adult_country2")
    private String adultCountry2;

    @JsonProperty("adult_special_req2")
    private String adultSpecialRequest2;

    @JsonProperty("contact_title")
    private String contactTitle;

    @JsonProperty("contact_name")
    private String contactName;

    @JsonProperty("contact_phone")
    private String contactPhone;

    @JsonProperty("contact_email")
    private String contactEmail;

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

    @JsonProperty("airline_type")
    private String airlineType;

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

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    public String getClassKey() {
        return classKey;
    }

    public void setClassKey(String classKey) {
        this.classKey = classKey;
    }

    public String getAirlineType() {
        return airlineType;
    }

    public void setAirlineType(String airlineType) {
        this.airlineType = airlineType;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getAdultIDNumber1() {
        return adultIDNumber1;
    }

    public void setAdultIDNumber1(String adultIDNumber1) {
        this.adultIDNumber1 = adultIDNumber1;
    }

    public String getAdultIDNumber2() {
        return adultIDNumber2;
    }

    public void setAdultIDNumber2(String adultIDNumber2) {
        this.adultIDNumber2 = adultIDNumber2;
    }

    public String getAdultBirthDate1() {
        return adultBirthDate1;
    }

    public void setAdultBirthDate1(String adultBirthDate1) {
        this.adultBirthDate1 = adultBirthDate1;
    }

    public String getAdultBirthDate2() {
        return adultBirthDate2;
    }

    public void setAdultBirthDate2(String adultBirthDate2) {
        this.adultBirthDate2 = adultBirthDate2;
    }

    public String getAdultPassport1() {
        return adultPassport1;
    }

    public void setAdultPassport1(String adultPassport1) {
        this.adultPassport1 = adultPassport1;
    }

    public String getAdultPassport2() {
        return adultPassport2;
    }

    public void setAdultPassport2(String adultPassport2) {
        this.adultPassport2 = adultPassport2;
    }

    public String getAdultPassportExpired1() {
        return adultPassportExpired1;
    }

    public void setAdultPassportExpired1(String adultPassportExpired1) {
        this.adultPassportExpired1 = adultPassportExpired1;
    }

    public String getAdultCountry1() {
        return adultCountry1;
    }

    public void setAdultCountry1(String adultCountry1) {
        this.adultCountry1 = adultCountry1;
    }

    public String getAdultPassportExpired2() {
        return adultPassportExpired2;
    }

    public void setAdultPassportExpired2(String adultPassportExpired2) {
        this.adultPassportExpired2 = adultPassportExpired2;
    }

    public String getAdultCountry2() {
        return adultCountry2;
    }

    public void setAdultCountry2(String adultCountry2) {
        this.adultCountry2 = adultCountry2;
    }
}

