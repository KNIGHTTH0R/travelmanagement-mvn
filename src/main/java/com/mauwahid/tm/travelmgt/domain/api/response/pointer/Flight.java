package com.mauwahid.tm.travelmgt.domain.api.response.pointer;

public class Flight {

    private String code;
    private String etd;
    private String eta;
    private String etdDate;
    private String departArea;
    private String etaDate;
    private String flightId;
    private String arriveArea;

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

    public String getEtdDate() {
        return etdDate;
    }

    public void setEtdDate(String etdDate) {
        this.etdDate = etdDate;
    }

    public String getDepartArea() {
        return departArea;
    }

    public void setDepartArea(String departArea) {
        this.departArea = departArea;
    }

    public String getEtaDate() {
        return etaDate;
    }

    public void setEtaDate(String etaDate) {
        this.etaDate = etaDate;
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
}
