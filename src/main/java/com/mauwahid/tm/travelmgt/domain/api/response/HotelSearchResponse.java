package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelHotel;

import java.util.Set;

public class HotelSearchResponse extends DefaultResponse {

    @JsonProperty("api_session_key")
    private String sessionKey;

    private Set<HotelHotel> hotels;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Set<HotelHotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<HotelHotel> hotels) {
        this.hotels = hotels;
    }
}

