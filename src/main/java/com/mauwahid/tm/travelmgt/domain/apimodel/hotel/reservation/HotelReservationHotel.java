package com.mauwahid.tm.travelmgt.domain.apimodel.hotel.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class HotelReservationHotel {

    @JsonProperty("reservation_hotel_id")
    private String reservationHotelID;

    @JsonProperty("reservation_id")
    private String reservationID;

    @JsonProperty("hotel_id")
    private String hotelID;

    @JsonProperty("hotel_name")
    private String hotelName;

    @JsonProperty("hotel_address")
    private String hotelAddress;

    @JsonProperty("hotel_phone")
    private String hotelPhone;

    @JsonProperty("supp_hotel_code")
    private String suppHotelCode;

    @JsonProperty("supplier_code")
    private String supplierCode;

    @JsonProperty("check_in_date")
    private String checkInDate;

    @JsonProperty("check_out_date")
    private String checkOutDate;

    @JsonProperty("booking_status")
    private String bookingStatus;

    @JsonProperty("time_limit")
    private String timeLimit;

    @JsonProperty("confirmation_code")
    private String confirmationCode;

    @JsonProperty("cancellation_code")
    private String cancellationCode;

    @JsonProperty("voucher_code")
    private String voucherNo;

    private String createdBy;

    private String createdTime;

    private String createdIPAddress;

    private String lastUpdatedBy;

    private String lastUpdatedIPAddress;


    private String lastUpdatedTime;

    private String active;

    @JsonProperty("reservation_room")
    private Set<HotelReservationRoom> hotelReservationRooms;

    @JsonProperty("reservation_remarks")
    private HotelReservationRemarks hotelReservationRemarks;

    @JsonProperty("reservation_fare")
    private Set<HotelReservationFare> hotelReservationFares;

    @JsonProperty("reservation_cancel_policy")
    private Set<HotelReservationCancelationPolicy> hotelReservationCancelationPolicies;






}
