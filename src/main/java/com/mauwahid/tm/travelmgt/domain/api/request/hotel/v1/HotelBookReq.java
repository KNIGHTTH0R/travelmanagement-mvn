package com.mauwahid.tm.travelmgt.domain.api.request.hotel.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.reservation.HotelCustomerData;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.reservation.HotelCustomerPax;
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
    private HotelCustomerData customerData;

    @JsonProperty("pax_data")
    private Set<HotelCustomerPax> paxData;



}
