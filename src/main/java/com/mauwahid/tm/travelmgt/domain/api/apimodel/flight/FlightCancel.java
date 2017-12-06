package com.mauwahid.tm.travelmgt.domain.api.apimodel.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightCancel {

    @JsonProperty("status_code")
    private int statusCode;

    @JsonProperty("status_desc")
    private String statusDesc;

}
