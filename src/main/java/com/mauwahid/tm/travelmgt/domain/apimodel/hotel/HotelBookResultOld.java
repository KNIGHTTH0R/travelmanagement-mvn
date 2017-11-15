package com.mauwahid.tm.travelmgt.domain.apimodel.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelBookResultOld {

    @JsonProperty("hotel_id")
    private String hotelId;

    @JsonProperty("room_id")
    private String roomId;

    @JsonProperty("room_quality")
    private String roomQuality;

    private String night;

    @JsonProperty("check_in")
    private String checkIn;

    @JsonProperty("check_out")
    private String checkOut;

    @JsonProperty("currency_code")
    private String currencyCode;

    @JsonProperty("booking_date")
    private String bookingDate;

    @JsonProperty("custoemr_name")
    private String customerName;

    @JsonProperty("customer_email")
    private String customerEmail;

    @JsonProperty("customer_phone")
    private String customerPhone;

    private String status;

    @JsonProperty("book_id")
    private String bookId;

    @JsonProperty("time_limit")
    private String timeLimit;

    @JsonProperty("price_per_room")
    private HotelPricePerRoom pricePerRoom;

}
