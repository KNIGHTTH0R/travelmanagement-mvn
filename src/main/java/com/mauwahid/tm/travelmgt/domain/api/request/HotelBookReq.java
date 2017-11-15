package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.request.hotelbook.CustomerData;
import com.mauwahid.tm.travelmgt.domain.api.request.hotelbook.PaxData;
import io.swagger.annotations.ApiKeyAuthDefinition;
import lombok.Data;

import java.util.Set;

@Data
public class HotelBookReq {


    @JsonProperty("api_source")
    private String apiSource;

    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("room_id")
    private String roomId;

    @JsonProperty("hotel_key")
    private String hotelKey;

    @JsonProperty("customer")
    private CustomerData customerData;

    @JsonProperty("pax_data")
    private Set<PaxData> paxData;



}
