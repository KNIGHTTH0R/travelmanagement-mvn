package com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelCustomerData {

    @JsonProperty("title")
    private String title;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("contact_number")
    private String contactNumber;

    @JsonProperty("address")
    private String address;
}
