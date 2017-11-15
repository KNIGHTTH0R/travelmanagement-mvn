package com.mauwahid.tm.travelmgt.domain.apimodel.hotel.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelReservationPax {

    @JsonProperty("ReservationPaxId")
    private String reservationPaxID;

    @JsonProperty("ReservationId")
    private String reservationID;

    @JsonProperty("PaxNo")
    private String paxNo;

    @JsonProperty("PaxTitle")
    private String paxTitle;

    @JsonProperty("PaxFirstName")
    private String paxFirstName;

    @JsonProperty("PaxLastName")
    private String paxLastName;

    @JsonProperty("PaxTypeCode")
    private String paxTypeCode;

    @JsonProperty("Age")
    private String age;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Nationality")
    private String nationality;

    @JsonProperty("LeaderPax")
    private String leaderPax;

    @JsonProperty("ContractNumber")
    private String contactNumber;

}
