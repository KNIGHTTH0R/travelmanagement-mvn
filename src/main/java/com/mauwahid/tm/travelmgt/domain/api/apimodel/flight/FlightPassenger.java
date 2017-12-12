package com.mauwahid.tm.travelmgt.domain.api.apimodel.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightPassenger {


    private String title;

  //  @JsonProperty("birth_date")
   // private String birthDate;

    @JsonProperty("passenger_type")
    private String passengerType;


    //for opsigo

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("home_phone")
    private String homePhone;

    @JsonProperty("mobile_phone")
    private String mobilePhone;

    @JsonProperty("other_phone")
    private String otherPhone;

    @JsonProperty("id_number")
    private String idNumber;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("adult_assoc")
    private String adultAssoc;

    @JsonProperty("passport_expired")
    private String passportExpired;

    @JsonProperty("passport_number")
    private String passportNumber;

    @JsonProperty("passport_origin")
    private String passportOrigin;

    @JsonProperty("birth_date")
    private String birthDate;

    private String type; // 1 -> adult, 2->child 3->infant
}
