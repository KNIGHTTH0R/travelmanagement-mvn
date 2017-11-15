package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelChangePriceResult;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelIssueResult;
import lombok.Data;

@Data
public class HotelChangePriceResponse extends DefaultResponse {

    @JsonProperty("change_price_result")
    private HotelChangePriceResult hotelChangePriceResult;

}
