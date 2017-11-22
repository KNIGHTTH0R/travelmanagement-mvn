package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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
}

