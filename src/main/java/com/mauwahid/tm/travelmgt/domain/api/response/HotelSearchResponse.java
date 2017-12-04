package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelHotel;
import lombok.Data;

import java.util.Set;

@Data
public class HotelSearchResponse extends DefaultResponse {


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "api_session_key",
            "hotels"
    })

    @JsonProperty("api_session_key")
    private String sessionKey;



    private Set<HotelHotel> hotels;

}

