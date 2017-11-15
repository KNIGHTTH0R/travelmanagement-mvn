package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelCancelPolicy;
import lombok.Data;

@Data
public class HotelCancelPolicyResponse extends DefaultResponse {

    @JsonProperty("cancel_policy")
    private HotelCancelPolicy hotelCancelPolicy;
}
