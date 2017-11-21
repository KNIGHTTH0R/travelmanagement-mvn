package com.mauwahid.tm.travelmgt.domain.apimodel.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightPrice {

    private String fare;

    @JsonProperty("total_price")
    private String totalPrice;
    private String tax;

    @JsonProperty("fare_adult_pax")
    private String fareAdultPax;

    @JsonProperty("fare_child_pax")
    private String fareChildPax;

    @JsonProperty("fare_infant_pax")
    private String fareInfantPax;

}
