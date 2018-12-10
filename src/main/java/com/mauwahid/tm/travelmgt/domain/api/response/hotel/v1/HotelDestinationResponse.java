package com.mauwahid.tm.travelmgt.domain.api.response.hotel.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelDestination;
import com.mauwahid.tm.travelmgt.domain.api.response.DefaultResponse;
import lombok.Data;

import java.util.Set;

@Data
public class HotelDestinationResponse extends DefaultResponse {

    @JsonProperty("hotel_destination")
    private Set<HotelDestination> hotelDestination;
}
