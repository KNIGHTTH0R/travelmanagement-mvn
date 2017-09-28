package com.mauwahid.tm.travelmgt.domain.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Depart {

    @JsonProperty("schedule_key")
    private String scheduleKey;

    @JsonProperty("airline_id")
    private String airlineId;

    @JsonProperty("airline_code")
    private String airlineCode;

    @JsonProperty("airline_name")
    private String airlineName;

    @JsonProperty("from")
    private String from;

    @JsonProperty("from_city")
    private String fromCity;

    @JsonProperty("to")
    private String to;

    @JsonProperty("to_city")
    private String toCity;

    @JsonProperty("date")
    private String date;

    @JsonProperty("via")
    private String via;

    @JsonProperty("fno")
    private String fno;

    @JsonProperty("etd")
    private String etd;

    @JsonProperty("eta")
    private String eta;

    @JsonProperty("etd_date")
    private String etdDate;

    @JsonProperty("eta_date")
    private String etaDate;

    @JsonProperty("type")
    private String type;

    @JsonProperty("class")
    private ArrayList<AirlineClass> classes;

    public String getScheduleKey() {
        return scheduleKey;
    }

    public void setScheduleKey(String scheduleKey) {
        this.scheduleKey = scheduleKey;
    }

    public String getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(String airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getFno() {
        return fno;
    }

    public void setFno(String fno) {
        this.fno = fno;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<AirlineClass> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<AirlineClass> classes) {
        this.classes = classes;
    }
}
