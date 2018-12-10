package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.request;

import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.Customer;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.Hotel;
import lombok.Data;

@Data
public class RqHotelSaveBooking {

    private String transactionNo;
    private String supplierReservationNo;
    private String paxPassport;
    private String checkInDate;
    private String checkOutDate;
    private Customer customer;
    private Hotel hotel;
    private Boolean overrideDuplication;
    private String sessionId;
    private String ipAddress;
    private String userAgent;
}
