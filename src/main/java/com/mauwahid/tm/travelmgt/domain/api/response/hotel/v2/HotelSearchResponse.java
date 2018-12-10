package com.mauwahid.tm.travelmgt.domain.api.response.hotel.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelHotel;
import com.mauwahid.tm.travelmgt.domain.api.response.DefaultResponse;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.Hotel;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.Status;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class HotelSearchResponse extends DefaultResponse {


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "api_session_key",
            "hotels"
    })

    @JsonProperty("api_session_key")
    private String sessionId;

    private List<Hotel> hotels;

    private Integer pageNumber;
    private Integer totalPage;
    private Integer totalResults;


}

