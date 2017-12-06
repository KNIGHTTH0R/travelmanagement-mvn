package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightCancel;
import lombok.Data;

@Data
public class FlightCancelResponse extends DefaultResponse {

    @JsonProperty("session_key")
    private String sessionKey;

    @JsonProperty("cancel_data")
    private FlightCancel flightCancel;


}
