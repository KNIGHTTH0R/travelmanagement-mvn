package com.mauwahid.tm.travelmgt.domain.apimodel.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightContact {

    private String phone;

    @JsonProperty("full_name")
    private String fullName;

    private String email;


}
