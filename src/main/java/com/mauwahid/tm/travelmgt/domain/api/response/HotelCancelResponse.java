package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelCancelResult;
import lombok.Data;

@Data
public class HotelCancelResponse extends DefaultResponse {
    @JsonProperty("cancel_result")
    private HotelCancelResult hotelCancelResult;
}
