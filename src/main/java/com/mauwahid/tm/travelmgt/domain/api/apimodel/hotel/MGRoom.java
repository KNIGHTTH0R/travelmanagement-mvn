package com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MGRoom {

    @JsonProperty("room_id")
    private String roomId;

    @JsonProperty("pax_passport")
    private String paxPassport;


    @JsonProperty("cancel_policy_id")
    private String cancelPolicyId;

    @JsonProperty("internal_code")
    private String internalCode;

    @JsonProperty("category_code")
    private String categoryCode;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("type_name")
    private String typeName;

    @JsonProperty("price_per_night")
    private String pricePerNight;

    @JsonProperty("total_price")
    private String totalPrice;

    @JsonProperty("num_rooms")
    private String numRooms;

    @JsonProperty("bf_type")
    private String bfType;
}
