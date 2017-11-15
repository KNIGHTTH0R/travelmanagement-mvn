package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelCancelResult;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelIssueResult;
import lombok.Data;

@Data
public class HotelCancelResponse extends DefaultResponse {
    @JsonProperty("cancel_result")
    private HotelCancelResult hotelCancelResult;
}
