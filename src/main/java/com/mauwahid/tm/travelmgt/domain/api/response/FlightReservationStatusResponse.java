package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.json.FlightPayments;
import lombok.Data;

import java.util.Set;

@Data
public class FlightReservationStatusResponse extends DefaultResponse{

    @JsonProperty("booking_code")
    private String bookingCode;

    @JsonProperty("time_limit")
    private String timeLimit;

    @JsonProperty("status_reservation")
    private String statusReservation;

    @JsonProperty("ticketed")
    private String ticketed;

    private String created;

    private String reserved;

    @JsonProperty("flight_payment")
    private Set<FlightPayments> flightPayments;


}
