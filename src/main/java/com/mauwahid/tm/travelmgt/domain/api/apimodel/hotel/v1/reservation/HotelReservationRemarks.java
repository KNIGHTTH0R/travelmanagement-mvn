package com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelReservationRemarks {

    @JsonProperty("reservation_hotel_remarks_id")
    private String reservationHotelRemarksID;

    @JsonProperty("reservation_hotel_id")
    private String reservationHotelID;

    private String remarks;

}
