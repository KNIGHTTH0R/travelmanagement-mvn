package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelBookInfo;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelBookResult;
import lombok.Data;

@Data
public class HotelBookResponse extends DefaultResponse{


   // @JsonProperty("book_info")
   // HotelBookInfo bookInfo;
    @JsonProperty("book_result")
    HotelBookResult bookResult;

}
