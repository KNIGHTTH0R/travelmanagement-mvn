package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightReservationStatusReq {

    @JsonProperty("api_source")
    private String apiSource;

    @JsonProperty("pnr_id")
    private String pnrId;
}
