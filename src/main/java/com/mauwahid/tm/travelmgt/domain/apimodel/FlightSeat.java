package com.mauwahid.tm.travelmgt.domain.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightSeat {

    private String available;
    private String code;

    @JsonProperty("flight_key")
    private String flightKey;

    @JsonProperty("seat_class")
    private String seatClass;

    @JsonProperty("price")
    private FlightPrice flightPrice;

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFlightKey() {
        return flightKey;
    }

    public void setFlightKey(String flightKey) {
        this.flightKey = flightKey;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public FlightPrice getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(FlightPrice flightPrice) {
        this.flightPrice = flightPrice;
    }
}
