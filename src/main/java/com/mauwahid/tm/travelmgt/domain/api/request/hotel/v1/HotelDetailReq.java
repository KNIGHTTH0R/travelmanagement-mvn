package com.mauwahid.tm.travelmgt.domain.api.request.hotel.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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

}
