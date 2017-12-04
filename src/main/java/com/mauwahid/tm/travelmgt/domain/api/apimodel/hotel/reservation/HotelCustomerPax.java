package com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelCustomerPax {

    @JsonProperty("pax_no")
    private String paxNo;

    @JsonProperty("pax_title")
    private String paxTitle;

    @JsonProperty("pax_first_name")
    private String paxFirstName;

    @JsonProperty("pax_last_name")
    private String paxLastName;

    @JsonProperty("pax_type_code")
    private String paxTypeCode;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("age")
    private String age;

    @JsonProperty("room_id")
    private String roomId;

}
