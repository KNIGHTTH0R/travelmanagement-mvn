package com.mauwahid.tm.travelmgt.domain.api.response.hotel.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelBookResult;
import com.mauwahid.tm.travelmgt.domain.api.response.DefaultResponse;
import lombok.Data;

@Data
public class HotelBookResponse extends DefaultResponse {


   // @JsonProperty("book_info")
   // HotelBookInfo bookInfo;
    @JsonProperty("book_result")
    HotelBookResult bookResult;

}
