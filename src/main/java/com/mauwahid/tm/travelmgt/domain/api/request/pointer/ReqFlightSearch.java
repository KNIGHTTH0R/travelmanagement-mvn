package com.mauwahid.tm.travelmgt.domain.api.request.pointer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqFlightSearch {

    private String airline;
    private String token;
    private String from;
    private String to;

    @JsonProperty("depart_date")
    private String departDate;

    @JsonProperty("adult_pax")
    private String adultPax;

    @JsonProperty("child_pax")
    private String childPax;

    @JsonProperty("infant_pax")
    private String infantPax;

    //consist oneway/roundtrip
    @JsonProperty("search_type")
    private String searchType;

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
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

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
