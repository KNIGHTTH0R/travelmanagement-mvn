package com.mauwahid.tm.travelmgt.domain.api.apimodel.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightSeat {

    private String available;
    private String code;

    @JsonProperty("flight_key")
    private String flightKey;

    @JsonProperty("seat_class")
    private String seatClass;

    @JsonProperty("price")
    private FlightPrice flightPrice;

    @JsonProperty("class_key")
    private String classKey;

}
