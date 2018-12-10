package com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.reservation.*;
import lombok.Data;

import java.util.Set;

@Data
public class HotelCancelResult {

    @JsonProperty("reservation_id")
    private String reservationID;

    @JsonProperty("merchant_id")
    private String merchantID;

    @JsonProperty("reservation_code")
    private String reservationCode;

    @JsonProperty("member_id")
    private String memberID;

    @JsonProperty("itinerary_name")
    private String itineraryName;

    @JsonProperty("product_type")
    private String productType;

    @JsonProperty("email_alert")
    private String emailAlert;

    @JsonProperty("status_code")
    private String statusCode;

    @JsonProperty("created_ip_address")
    private String createdIPAddress;

    @JsonProperty("last_updated_ip_address")
    private String lastUpdatedIPAddress;

    @JsonProperty("message_code")
    private String messageCode;

    @JsonProperty("message_desc")
    private String messageDesc;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("created_time")
    private String createdTime;

    @JsonProperty("last_updated_by")
    private String lastUpdatedBy;

    @JsonProperty("last_updated_time")
    private String lastUpdatedTime;

    private String active;

    private Set<HotelReservationPax> hotelReservationPaxes;

    private Set<HotelReservationPaymentMethod> hotelReservationPaymentMethods;

    @JsonProperty("reservation_billing")
    private Set<HotelReservationBilling> hotelReservationBillings;


    @JsonProperty("reservation_hotel")
    private HotelReservationHotel hotelReservationHotel;

    @JsonProperty("reservation_customer")
    private HotelReservationCustomer hotelReservationCustomer;

}
