package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightIssue;

public class FlightIssueResponse extends DefaultResponse {

    @JsonProperty("session_key")
    private String sessionKey;

    @JsonProperty("issue_data")
    private FlightIssue flightIssue;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public FlightIssue getFlightIssue() {
        return flightIssue;
    }

    public void setFlightIssue(FlightIssue flightIssue) {
        this.flightIssue = flightIssue;
    }
}
