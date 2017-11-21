package com.mauwahid.tm.travelmgt.domain.apimodel.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightFlight {

    private String code;
    private String etd;
    private String eta;

    @JsonProperty("depart_area")
    private String departArea;

    @JsonProperty("etd_date")
    private String etdDate;

    @JsonProperty("eta_date")
    private String etaDate;

    @JsonProperty("flight_id")
    private String flightId;

    @JsonProperty("arrive_area")
    private String arriveArea;

    @JsonProperty("flight_type")
    private String flightType;

}
