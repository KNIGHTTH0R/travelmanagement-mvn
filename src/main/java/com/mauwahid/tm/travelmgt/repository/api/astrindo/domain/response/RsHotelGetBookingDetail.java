package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.response;

import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.Customer;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.HotelBookingInfo;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.Status;
import lombok.Data;

@Data
public class RsHotelGetBookingDetail {
    private Customer customer;
    private Integer supplierCode;
    private String supplierReservationNo;
    private String supplierBookingStatus;
    private String transactionNo;
    private Object cancellationNo;
    private Integer discount;
    private Integer totalPrice;
    private Integer requestPrice;
    private Integer priceDifference;
    private Boolean priceChanged;
    private Integer paymentAmount;
    private Integer principalCommission;
    private Integer agentCommission;
    private Integer commission;
    private Integer systemCommission;
    private String currency;
    private String bookingDate;
    private Object paymentDate;
    private String paymentTimeLimit;
    private String bookingStatus;
    private String paymentStatus;
    private HotelBookingInfo hotelBookingInfo;
    private Object cancellationPolicy;
    private Object paymentList;
    private String payableBy;
    private Object supplierLogo;
    private Status status;
    private String timestamp;
    private String sessionId;
}
