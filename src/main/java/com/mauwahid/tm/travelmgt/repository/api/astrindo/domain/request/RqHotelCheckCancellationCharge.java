package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.request;

import lombok.Data;

@Data
public class RqHotelCheckCancellationCharge {
    private String checkDate;
    private String transactionNo;
    private String sessionId;
    private String ipAddress;
    private String userAgent;
}
