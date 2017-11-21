package com.mauwahid.tm.travelmgt.domain.apimodel.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class FlightBook {

    @JsonProperty("status_code")
    private String statusCode;

    @JsonProperty("status_desc")
    private String statusDesc;

    private String maskapai;

    @JsonProperty("booking_code")
    private String bookingCode;

    @JsonProperty("time_limit")
    private String timeLimit;

    @JsonProperty("flight_list")
    private Set<FlightFlight> flights;

    @JsonProperty("passenger_list")
    private Set<FlightPassenger> passengers;

    @JsonProperty("contact_detail")
    private FlightContact flightContact;
}
