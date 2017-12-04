package com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelReservationRoom {

    @JsonProperty("reservation_hotel_room_id")
    private String reservationHotelRoomID;

    @JsonProperty("reservation_hotel_id")
    private String reservationHotelID;

    @JsonProperty("room_type")
    private String roomType;

    @JsonProperty("room_name")
    private String roomName;

    @JsonProperty("board_name")
    private String boardName;

    @JsonProperty("supp_room_id")
    private String suppRoomID;

    @JsonProperty("total_room")
    private String totalRoom;


}
