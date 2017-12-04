package com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelReservationFare {

    @JsonProperty("reservation_hotel_fare_id")
    private String reservationHotelFareID;

    @JsonProperty("reservation_hotel_room_id")
    private String reservationHotelRoomID;

    @JsonProperty("curr_code")
    private String currCode;

    @JsonProperty("sell_curr_code")
    private String sellCurrCode;

    private String price;

    @JsonProperty("price_converted")
    private String priceConverted;


    @JsonProperty("service_fee")
    private String serviceFee;

    private String discount;

    @JsonProperty("payment_curr_code")
    private String paymentCurrCode;

    @JsonProperty("payment_amount")
    private String paymentAmount;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("created_time")
    private String createdTime;

    @JsonProperty("created_ip_address")
    private String createdIPAddress;

    @JsonProperty("last_updated_by")
    private String lastUpdatedBy;

    @JsonProperty("last_updated_ip_address")
    private String lastUpdatedIPAddress;

    @JsonProperty("last_updated_time")
    private String lastUpdatedTime;

    @JsonProperty("active")
    private String active;
}
