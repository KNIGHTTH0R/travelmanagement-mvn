package com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelDestination {

    private String label;

    @JsonProperty("city_code")
    private String cityCode;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("city_name")
    private String cityName;

    @JsonProperty("country_name")
    private String countryName;
}
