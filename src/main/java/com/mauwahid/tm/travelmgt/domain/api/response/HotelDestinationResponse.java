package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelDestination;
import lombok.Data;

import java.util.Set;

@Data
public class HotelDestinationResponse extends DefaultResponse {

    @JsonProperty("hotel_destination")
    private Set<HotelDestination> hotelDestination;
}
