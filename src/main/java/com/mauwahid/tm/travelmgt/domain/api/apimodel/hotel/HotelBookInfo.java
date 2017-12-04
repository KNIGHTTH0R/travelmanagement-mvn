package com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelBookInfo {

    @JsonProperty("hotel_data_id")
    private String hotelDataId;

    @JsonProperty("hotel_information")
    private HotelHotel hotelHotel;

    @JsonProperty("book_result")
    private HotelBookResultOld bookResult;

    public String getHotelDataId() {
        return hotelDataId;
    }

    public void setHotelDataId(String hotelDataId) {
        this.hotelDataId = hotelDataId;
    }

    public HotelHotel getHotelHotel() {
        return hotelHotel;
    }

    public void setHotelHotel(HotelHotel hotelHotel) {
        this.hotelHotel = hotelHotel;
    }

    public HotelBookResultOld getBookResult() {
        return bookResult;
    }

    public void setBookResult(HotelBookResultOld bookResult) {
        this.bookResult = bookResult;
    }
}
