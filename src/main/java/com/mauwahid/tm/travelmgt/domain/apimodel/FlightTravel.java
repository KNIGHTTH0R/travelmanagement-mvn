package com.mauwahid.tm.travelmgt.domain.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class FlightTravel {

    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("error_status")
    private String errorStatus;

    @JsonProperty("travel_id")
    private String travelId;

    @JsonProperty("travel_api")
    private String travelAPI;

    @JsonProperty("flight_count")
    private String flightCount;

    @JsonProperty("flight_id")
    private String flightId;

    @JsonProperty("etd_date")
    private String etdDate;

    @JsonProperty("eta_date")
    private String etaDate;

    @JsonProperty("depart_area")
    private String departArea;

    @JsonProperty("arrive_area")
    private String arriveArea;


    private String etd;
    private String eta;

    @JsonProperty("flights")
    private Set<FlightFlight> flights;

    @JsonProperty("seats")
    private Set<FlightSeat> seats;


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    public String getTravelAPI() {
        return travelAPI;
    }

    public void setTravelAPI(String travelAPI) {
        this.travelAPI = travelAPI;
    }

    public String getFlightCount() {
        return flightCount;
    }

    public void setFlightCount(String flightCount) {
        this.flightCount = flightCount;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getEtdDate() {
        return etdDate;
    }

    public void setEtdDate(String etdDate) {
        this.etdDate = etdDate;
    }

    public String getEtaDate() {
        return etaDate;
    }

    public void setEtaDate(String etaDate) {
        this.etaDate = etaDate;
    }

    public String getDepartArea() {
        return departArea;
    }

    public void setDepartArea(String departArea) {
        this.departArea = departArea;
    }

    public String getArriveArea() {
        return arriveArea;
    }

    public void setArriveArea(String arriveArea) {
        this.arriveArea = arriveArea;
    }

    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public Set<FlightFlight> getFlights() {
        return flights;
    }

    public void setFlights(Set<FlightFlight> flights) {
        this.flights = flights;
    }

    public Set<FlightSeat> getSeats() {
        return seats;
    }

    public void setSeats(Set<FlightSeat> seats) {
        this.seats = seats;
    }
}
