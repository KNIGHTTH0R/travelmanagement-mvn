package com.mauwahid.tm.travelmgt.domain.api.response.hotel.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelChangePriceResult;
import com.mauwahid.tm.travelmgt.domain.api.response.DefaultResponse;
import lombok.Data;

@Data
public class HotelChangePriceResponse extends DefaultResponse {

    @JsonProperty("change_price_result")
    private HotelChangePriceResult hotelChangePriceResult;

}
