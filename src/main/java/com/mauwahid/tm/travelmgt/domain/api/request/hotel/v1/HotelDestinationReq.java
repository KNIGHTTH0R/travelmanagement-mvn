package com.mauwahid.tm.travelmgt.domain.api.request.hotel.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelDestinationReq {

    @JsonProperty("api_source")
    private String apiSource;

    @JsonProperty("country_code")
    private String countryCode;

}
