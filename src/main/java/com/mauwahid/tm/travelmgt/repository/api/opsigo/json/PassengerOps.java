package com.mauwahid.tm.travelmgt.repository.api.opsigo.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PassengerOps {

    @JsonProperty("Index")
    private int index;

    @JsonProperty("Type")
    private int type;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("BirthDate")
    private String birthDate;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("HomePhone")
    private String homePhone;

    @JsonProperty("MobilePhone")
    private String mobilePhone;

    @JsonProperty("OtherPhone")
    private String otherPhone;

    @JsonProperty("IdNumber")
    private String idNumber;

    @JsonProperty("Nationality")
    private String nationality;

    @JsonProperty("AdultAssoc")
    private String adultAssoc;

    @JsonProperty("PassportNumber")
    private String passportNumber;

    @JsonProperty("PassportExpire")
    private String passportExpired;

    @JsonProperty("PassportOrigin")
    private String passportOrigin;

    @JsonProperty("ticket_number")
    private String ticketNumber;

}
