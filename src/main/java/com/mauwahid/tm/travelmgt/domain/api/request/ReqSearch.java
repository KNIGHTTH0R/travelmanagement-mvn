package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqSearch {

    @JsonProperty("airline_name")
    private String airlineName;

    @JsonProperty("round_trip")
    private String roundtrip;

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

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

    @JsonProperty("airline_type")
    private String airlineType;

    @JsonProperty("api_source")
    private String[] apiSource;

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getRoundtrip() {
        return roundtrip;
    }

    public void setRoundtrip(String roundtrip) {
        this.roundtrip = roundtrip;
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

    public String getAirlineType() {
        return airlineType;
    }

    public void setAirlineType(String airlineType) {
        this.airlineType = airlineType;
    }

    public String[] getApiSource() {
        return apiSource;
    }

    public void setApiSource(String[] apiSource) {
        this.apiSource = apiSource;
    }
}
