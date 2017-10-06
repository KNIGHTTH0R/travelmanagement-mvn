package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightIssueReq {

    @JsonProperty("session_key")
    private String sessionKey;

    private String airline;

    @JsonProperty("booking_code")
    private String bookingCode;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }
}
