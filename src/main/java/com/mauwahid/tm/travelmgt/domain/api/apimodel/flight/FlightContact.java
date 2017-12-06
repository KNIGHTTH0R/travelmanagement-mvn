package com.mauwahid.tm.travelmgt.domain.api.apimodel.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightContact {

    private String phone;

  //  @JsonProperty("full_name")
  //  private String fullName;

    private String email;

    //for Opsigo

    private String title;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("home_phone")
    private String homePhone;

    @JsonProperty("mobile_phone")
    private String mobilePhone;



}
