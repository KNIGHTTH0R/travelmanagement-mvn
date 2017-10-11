package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelBookInfo;

public class HotelBookResponse extends DefaultResponse{


    @JsonProperty("book_info")
    HotelBookInfo bookInfo;

    public HotelBookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(HotelBookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }
}
