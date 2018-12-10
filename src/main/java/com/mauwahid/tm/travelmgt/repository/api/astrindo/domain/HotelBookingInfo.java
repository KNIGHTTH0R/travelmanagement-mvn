package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain;

import lombok.Data;

@Data
public class HotelBookingInfo {

    private String documentId;
    private String voucherNo;
    private String voucherDate;
    private String internalCode;
    private String hotelCode;
    private String hotelName;
    private String address1;
    private String address2;
    private String address3;
    private String telephone;
    private Object facsimile;
    private String checkInDate;
    private String checkOutDate;
    private Boolean canAmend;
    private RoomInfo roomInfo;
    private Object cityName;
    private Object countryName;
    private Integer rating;
    private String heroImage;
    private Object checkInInstructions;
    private Object specialCheckInInstructions;
    private String specialRequest;
}
