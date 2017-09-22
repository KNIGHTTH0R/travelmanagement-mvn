package com.mauwahid.tm.travelmgt.domain.apimodel;

import com.mauwahid.tm.travelmgt.domain.api.response.IData;

public class SearchInfo implements IData {

    private String sessionId;
    private String apiId;
    private String roundTrip;
    private String from;
    private String fromCity;
    private String to;
    private String toCity;
    private String departDate;
    private String returnDate;
    private String adultPax;
    private String childPax;
    private String infantPax;
    private Airline airline;
    private Schedule schedule;

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
