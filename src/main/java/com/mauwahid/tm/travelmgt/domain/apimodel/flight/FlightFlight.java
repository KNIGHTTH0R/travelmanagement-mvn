package com.mauwahid.tm.travelmgt.domain.apimodel.flight;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightFlight {

    private String code;
    private String etd;
    private String eta;

    @JsonProperty("depart_area")
    private String departArea;

    @JsonProperty("etd_date")
    private String etdDate;

    @JsonProperty("eta_date")
    private String etaDate;

    @JsonProperty("flight_id")
    private String flightId;

    @JsonProperty("arrive_area")
    private String arriveArea;

    @JsonProperty("flight_type")
    private String flightType;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getDepartArea() {
        return departArea;
    }

    public void setDepartArea(String departArea) {
        this.departArea = departArea;
    }

    public String getEtdDate() {
        return etdDate;
    }

    public void setEtdDate(String etdDate) {
        this.etdDate = etdDate;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getArriveArea() {
        return arriveArea;
    }

    public void setArriveArea(String arriveArea) {
        this.arriveArea = arriveArea;
    }

    public String getEtaDate() {
        return etaDate;
    }

    public void setEtaDate(String etaDate) {
        this.etaDate = etaDate;
    }
}
