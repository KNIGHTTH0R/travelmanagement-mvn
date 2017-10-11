package com.mauwahid.tm.travelmgt.domain.apimodel.flight;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightPassenger {


    @JsonProperty("birth_date")
    private String birthDate;

    @JsonProperty("passenger_type")
    private String passengerType;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("ticket_no")
    private String ticketNo;

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }
}
