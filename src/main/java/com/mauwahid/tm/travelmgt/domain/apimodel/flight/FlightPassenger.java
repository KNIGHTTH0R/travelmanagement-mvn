package com.mauwahid.tm.travelmgt.domain.apimodel.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightPassenger {


    @JsonProperty("birth_date")
    private String birthDate;

    @JsonProperty("passenger_type")
    private String passengerType;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("ticket_no")
    private String ticketNo;
}
