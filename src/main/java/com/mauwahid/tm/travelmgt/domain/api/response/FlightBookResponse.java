package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightBook;

public class FlightBookResponse extends DefaultResponse {

    @JsonProperty("session_key")
    private String sessionKey;

    @JsonProperty("book_result")
    private FlightBook flightBook;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public FlightBook getFlightBook() {
        return flightBook;
    }

    public void setFlightBook(FlightBook flightBook) {
        this.flightBook = flightBook;
    }
}
