package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightIssueReq {

    @JsonProperty("session_key")
    private String sessionKey;

    private String airline;

    @JsonProperty("booking_code")
    private String bookingCode;
}

