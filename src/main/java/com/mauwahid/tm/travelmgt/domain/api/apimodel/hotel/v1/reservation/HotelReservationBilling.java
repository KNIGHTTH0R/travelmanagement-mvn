package com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelReservationBilling {

    @JsonProperty("reservation_billing_id")
    private String reservationBillingID;

    @JsonProperty("reservation_id")
    private String reservationID;

    @JsonProperty("curr_code")
    private String currCode;

    @JsonProperty("total_sell")
    private String totalSell;

    private String ppn;

    private String surcharge;

    private String discount;

    @JsonProperty("agent_comm")
    private String agentComm;

    @JsonProperty("issue_fee")
    private String issueFee;

    @JsonProperty("voucher_number")
    private String voucherNumber;

    private String rounding;

    @JsonProperty("total_billing")
    private String totalBilling;

    @JsonProperty("selected_payment_method")
    private String selectedPaymentMethod;

    private String paid;
}
