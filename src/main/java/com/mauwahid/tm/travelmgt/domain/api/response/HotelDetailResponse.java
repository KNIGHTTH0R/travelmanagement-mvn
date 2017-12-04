package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelHotel;

public class HotelDetailResponse extends DefaultResponse {


    @JsonProperty("session_key")
    private String sessionKey;

    @JsonProperty("result")
    private HotelHotel hotelResult;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public HotelHotel getHotelResult() {
        return hotelResult;
    }

    public void setHotelResult(HotelHotel hotelResult) {
        this.hotelResult = hotelResult;
    }
}
