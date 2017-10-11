package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelDetailReq {

    @JsonProperty("api_source")
    private String apiSource;

    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("hotel_key")
    private String hotelKey;

    @JsonProperty("hotel_id")
    private String hotelId;

    @JsonProperty("service_id")
    private String serviceId;

    public String getApiSource() {
        return apiSource;
    }

    public void setApiSource(String apiSource) {
        this.apiSource = apiSource;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getHotelKey() {
        return hotelKey;
    }

    public void setHotelKey(String hotelKey) {
        this.hotelKey = hotelKey;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
