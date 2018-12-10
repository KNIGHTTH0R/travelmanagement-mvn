package com.mauwahid.tm.travelmgt.domain.api.response.hotel.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelCancelPolicy;
import com.mauwahid.tm.travelmgt.domain.api.response.DefaultResponse;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HotelCancelPolicyResponse extends DefaultResponse {

    @JsonProperty("cancel_policy")
    private HotelCancelPolicy hotelCancelPolicy;
}
