package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.apimodel.DepartDetail;
import com.mauwahid.tm.travelmgt.domain.apimodel.Price;
import com.mauwahid.tm.travelmgt.domain.apimodel.ReturnDetail;

public class ResSearchDetail {

    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("error_no")
    private String errorNo;

    @JsonProperty("round_trip")
    private String roundTrip;
    private String from;
    private String to;

    @JsonProperty("to_city")
    private String toCity;

    @JsonProperty("from_city")
    private String fromCity;

    @JsonProperty("depart_date")
    private String departDate;

    @JsonProperty("return_date")
    private String returnDate;
    private String adult;
    private String child;
    private String infant;

    @JsonProperty("depart_detail")
    private DepartDetail departDetail;

    @JsonProperty("return_detail")
    private ReturnDetail returnDetail;

    private Price price;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
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

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
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

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getInfant() {
        return infant;
    }

    public void setInfant(String infant) {
        this.infant = infant;
    }

    public DepartDetail getDepartDetail() {
        return departDetail;
    }

    public void setDepartDetail(DepartDetail departDetail) {
        this.departDetail = departDetail;
    }

    public ReturnDetail getReturnDetail() {
        return returnDetail;
    }

    public void setReturnDetail(ReturnDetail returnDetail) {
        this.returnDetail = returnDetail;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
