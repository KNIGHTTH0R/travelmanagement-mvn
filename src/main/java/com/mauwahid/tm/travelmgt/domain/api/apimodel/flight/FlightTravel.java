package com.mauwahid.tm.travelmgt.domain.api.apimodel.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class FlightTravel {

    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("error_status")
    private String errorStatus;

    @JsonProperty("travel_id")
    private String travelId;

    @JsonProperty("travel_key")
    private String travelKey;

    @JsonProperty("travel_api")
    private String travelAPI;

    @JsonProperty("flight_count")
    private String flightCount;

    @JsonProperty("flight_number")
    private String flightNumber;

    @JsonProperty("etd_date")
    private String etdDate;

    @JsonProperty("eta_date")
    private String etaDate;

    @JsonProperty("depart_area")
    private String departArea;

    @JsonProperty("arrive_area")
    private String arriveArea;


    private String etd;
    private String eta;

    @JsonProperty("flights")
    private Set<FlightFlight> flights;

    @JsonProperty("seats")
    private Set<FlightSeat> seats;

    @JsonProperty("connecting_flights")
    private Set<FlightTravel> connectingTravel;

    @JsonProperty("is_connecting")
    private boolean isConnecting;

    @JsonProperty("total_transit")
    private int totalTransit;

    @JsonProperty("is_pairing")
    private boolean isPairing;

    @JsonProperty("pair_flight_fare")
    private FlightPrice pairFlight;

}
