package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.reservation.HotelCustomerData;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.reservation.HotelCustomerPax;
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
