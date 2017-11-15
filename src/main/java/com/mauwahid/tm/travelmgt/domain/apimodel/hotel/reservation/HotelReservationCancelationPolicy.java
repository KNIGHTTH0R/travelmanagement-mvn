package com.mauwahid.tm.travelmgt.domain.apimodel.hotel.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelReservationCancelationPolicy {


    @JsonProperty("reservation_hotel_cancel_policy_id")
    private String reservationHotelCancelPolicyId;

    @JsonProperty("reservation_hotel_id")
    private String reservationHotelID;

    @JsonProperty("reservation_hotel_room_id")
    private String reservationHotelRoomID;

    @JsonProperty("from_date")
    private String fromDate;

    @JsonProperty("to_date")
    private String toDate;

    @JsonProperty("curr_code")
    private String currCode;

    @JsonProperty("sell_curr_code")
    private String sellCurrCode;

    @JsonProperty("charge_amount")
    private String chargeAmount;

    @JsonProperty("charge_amount_converted")
    private String chargeAmountConverted;

    private String description;
}
