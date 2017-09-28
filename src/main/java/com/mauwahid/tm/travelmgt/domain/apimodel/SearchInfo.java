package com.mauwahid.tm.travelmgt.domain.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.response.IData;

public class SearchInfo implements IData {


    @JsonProperty("status")
    private String status;

    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("api_id")
    private String apiId;

    @JsonProperty("round_trip")
    private String roundTrip;

    @JsonProperty("from")
    private String from;

    @JsonProperty("from_city")
    private String fromCity;

    @JsonProperty("to")
    private String to;

    @JsonProperty("to_city")
    private String toCity;

    @JsonProperty("depart_date")
    private String departDate;

    @JsonProperty("return_date")
    private String returnDate;

    @JsonProperty("adult_pax")
    private String adultPax;

    @JsonProperty("child_pax")
    private String childPax;

    @JsonProperty("infant_pax")
    private String infantPax;

    @JsonProperty("airline")
    private Airline airline;

    @JsonProperty("schedule")
    private Schedule schedule;

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getRoundTrip() {
        return roundTrip;
    }

    public void setRoundTrip(String roundTrip) {
        this.roundTrip = roundTrip;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getAdultPax() {
        return adultPax;
    }

    public void setAdultPax(String adultPax) {
        this.adultPax = adultPax;
    }

    public String getChildPax() {
        return childPax;
    }

    public void setChildPax(String childPax) {
        this.childPax = childPax;
    }

    public String getInfantPax() {
        return infantPax;
    }

    public void setInfantPax(String infantPax) {
        this.infantPax = infantPax;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }


}
