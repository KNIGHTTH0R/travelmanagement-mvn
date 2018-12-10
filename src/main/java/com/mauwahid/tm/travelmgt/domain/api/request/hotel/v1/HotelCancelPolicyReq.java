package com.mauwahid.tm.travelmgt.domain.api.request.hotel.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelCancelPolicyReq {

    @JsonProperty("session_id")
    private String sessionID;

    @JsonProperty("hotel_key")
    private String hotelKey;

    @JsonProperty("room_id")
    private String roomID;


}
