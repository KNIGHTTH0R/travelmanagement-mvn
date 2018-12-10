package com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelReservationCustomer {

    @JsonProperty("reservation_customer_id")
    private String reservationCustomerID;

    @JsonProperty("reservation_id")
    private String reservationID;

    private String title;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String email;

    @JsonProperty("contact_number")
    private String contactNumber;

    private String address;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("last_updated_by")
    private String lastUpdatedBy;

    @JsonProperty("last_updated_time")
    private String lastUpdatedTime;

    private String active;
}
