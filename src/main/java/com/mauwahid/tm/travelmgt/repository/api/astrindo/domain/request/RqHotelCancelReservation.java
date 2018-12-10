package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.request;

import lombok.Data;

@Data
public class RqHotelCancelReservation {
    private String transactionNo;
    private String sessionId;
    private String ipAddress;
    private String userAgent;
}
