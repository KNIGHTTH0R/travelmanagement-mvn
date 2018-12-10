package com.mauwahid.tm.travelmgt.domain.api.response.hotel.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelCancelResult;
import com.mauwahid.tm.travelmgt.domain.api.response.DefaultResponse;
import lombok.Data;

@Data
public class HotelCancelResponse extends DefaultResponse {
    @JsonProperty("cancel_result")
    private HotelCancelResult hotelCancelResult;
}
