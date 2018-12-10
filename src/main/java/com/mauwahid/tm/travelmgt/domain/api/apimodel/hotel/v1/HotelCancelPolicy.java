package com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelCancelPolicy {

    @JsonProperty("merchant_id")
    private String merchantID;

    @JsonProperty("session_id")
    private String sessionID;

    @JsonProperty("hotel_key")
    private String hotelKey;

    @JsonProperty("room_id")
    private String roomID;

    private String description;


}
