package com.mauwahid.tm.travelmgt.repository.api.opsigo.json.flightAv;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class FlightBookOpsReq {

    @JsonProperty("Contact")
    private Contact contact;

    @JsonProperty("Passengers")
    private Set<PassengerOps> passengers;

    @JsonProperty("Segments")
    private Set<SegmentOps> segments;

    @JsonProperty("CallbackUri")
    private String callBackUri;

    @JsonProperty("FlightType")
    private String flightType;
}
