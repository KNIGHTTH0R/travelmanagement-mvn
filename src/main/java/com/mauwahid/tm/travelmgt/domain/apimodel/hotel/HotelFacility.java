package com.mauwahid.tm.travelmgt.domain.apimodel.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelFacility {

    @JsonProperty("facility_name")
    private String facilityName;

    private String fee;

    private String text;


}
