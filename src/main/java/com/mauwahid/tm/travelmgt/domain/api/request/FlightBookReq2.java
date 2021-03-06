package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightContact;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightPassenger;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightSegment;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class FlightBookReq2 {

    @JsonProperty("cust_contact")
    private FlightContact flightContact;

    @JsonProperty("passenger")
    private Set<FlightPassenger> passengers;

    @JsonProperty("segments")
    private LinkedHashSet<FlightSegment> flightSegments;

    @JsonProperty("flight_type")
    private String flightType;

    @JsonProperty("call_back_uri")
    private String callBackUri;

    @JsonProperty("api_source")
    private String apiSource;


}
