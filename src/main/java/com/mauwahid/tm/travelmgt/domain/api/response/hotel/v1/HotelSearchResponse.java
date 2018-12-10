package com.mauwahid.tm.travelmgt.domain.api.response.hotel.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelHotel;
import com.mauwahid.tm.travelmgt.domain.api.response.DefaultResponse;
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

