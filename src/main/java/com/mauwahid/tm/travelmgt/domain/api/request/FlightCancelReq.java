package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightCancelReq {

    @JsonProperty("session_key")
    private String sessionKey;

    private String airline;

    @JsonProperty("booking_code")
    private String bookingCode;

    @JsonProperty("api_source")
    private String apiSource;

    @JsonProperty("callback_uri")
    private String callbackUri;
}
