package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightTravel;

import java.util.Set;

public class FlightSearchResponse extends DefaultResponse {


    @JsonProperty("session_key")
    private String sessionKey;

    @JsonProperty("depart_travel")
    private Set<FlightTravel> departTravel;

    @JsonProperty("return_travel")
    private Set<FlightTravel> returnTravel;

    @JsonProperty("session_key")
    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Set<FlightTravel> getDepartTravel() {
        return departTravel;
    }

    public void setDepartTravel(Set<FlightTravel> departTravel) {
        this.departTravel = departTravel;
    }

    public Set<FlightTravel> getReturnTravel() {
        return returnTravel;
    }

    public void setReturnTravel(Set<FlightTravel> returnTravel) {
        this.returnTravel = returnTravel;
    }
}
