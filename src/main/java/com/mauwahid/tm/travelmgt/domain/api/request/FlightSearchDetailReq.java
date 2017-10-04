package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightSearchDetailReq {


    @JsonProperty("api_name")
    private String apiName;

    @JsonProperty("session_id_depart")
    private String sessionIdDepart;

    @JsonProperty("schedule_key_depart")
    private String scheduleKeyDepart;

    @JsonProperty("schedule_class_depart")
    private String scheduleClassDepart;

    @JsonProperty("session_id_return")
    private String sessionIdReturn;

    @JsonProperty("schedule_key_return")
    private String scheduleKeyReturn;

    @JsonProperty("schedule_class_return")
    private String scheduleClassReturn;

    @JsonProperty("airline_type")
    private String airlineType;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getSessionIdDepart() {
        return sessionIdDepart;
    }

    public void setSessionIdDepart(String sessionIdDepart) {
        this.sessionIdDepart = sessionIdDepart;
    }

    public String getScheduleKeyDepart() {
        return scheduleKeyDepart;
    }

    public void setScheduleKeyDepart(String scheduleKeyDepart) {
        this.scheduleKeyDepart = scheduleKeyDepart;
    }

    public String getScheduleClassDepart() {
        return scheduleClassDepart;
    }

    public void setScheduleClassDepart(String scheduleClassDepart) {
        this.scheduleClassDepart = scheduleClassDepart;
    }

    public String getSessionIdReturn() {
        return sessionIdReturn;
    }

    public void setSessionIdReturn(String sessionIdReturn) {
        this.sessionIdReturn = sessionIdReturn;
    }

    public String getScheduleKeyReturn() {
        return scheduleKeyReturn;
    }

    public void setScheduleKeyReturn(String scheduleKeyReturn) {
        this.scheduleKeyReturn = scheduleKeyReturn;
    }

    public String getScheduleClassReturn() {
        return scheduleClassReturn;
    }

    public void setScheduleClassReturn(String scheduleClassReturn) {
        this.scheduleClassReturn = scheduleClassReturn;
    }

    public String getAirlineType() {
        return airlineType;
    }

    public void setAirlineType(String airlineType) {
        this.airlineType = airlineType;
    }
}
