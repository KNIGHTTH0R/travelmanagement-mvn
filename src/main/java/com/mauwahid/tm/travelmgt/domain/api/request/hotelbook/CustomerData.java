package com.mauwahid.tm.travelmgt.domain.api.request.hotelbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerData {

    @JsonProperty("Title")
    private String title;

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("ContactNumber")
    private String contactNumber;

    @JsonProperty("Address")
    private String address;


}
