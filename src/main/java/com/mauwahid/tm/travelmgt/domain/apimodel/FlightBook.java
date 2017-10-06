package com.mauwahid.tm.travelmgt.domain.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class FlightBook {

    @JsonProperty("status_code")
    private String statusCode;

    @JsonProperty("status_desc")
    private String statusDesc;

    private String maskapai;

    @JsonProperty("booking_code")
    private String bookingCode;

    @JsonProperty("time_limit")
    private String timeLimit;

    @JsonProperty("flight_list")
    private Set<FlightFlight> flights;

    @JsonProperty("passenger_list")
    private Set<FlightPassenger> passengers;

    @JsonProperty("contact_detail")
    private FlightContact flightContact;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getMaskapai() {
        return maskapai;
    }

    public void setMaskapai(String maskapai) {
        this.maskapai = maskapai;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Set<FlightFlight> getFlights() {
        return flights;
    }

    public void setFlights(Set<FlightFlight> flights) {
        this.flights = flights;
    }

    public Set<FlightPassenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<FlightPassenger> passengers) {
        this.passengers = passengers;
    }

    public FlightContact getFlightContact() {
        return flightContact;
    }

    public void setFlightContact(FlightContact flightContact) {
        this.flightContact = flightContact;
    }
}
