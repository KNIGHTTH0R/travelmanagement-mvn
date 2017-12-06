package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightIssue;
import lombok.Data;

@Data
public class FlightIssueResponse extends DefaultResponse {

    @JsonProperty("session_key")
    private String sessionKey;

    @JsonProperty("issue_data")
    private FlightIssue flightIssue;

}
