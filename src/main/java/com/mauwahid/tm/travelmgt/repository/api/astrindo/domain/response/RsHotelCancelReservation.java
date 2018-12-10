package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.response;

import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.Status;
import lombok.Data;

@Data
public class RsHotelCancelReservation {
    private String supplierReservationNo;
    private String transactionNo;
    private String voucherNo;
    private Object cancellationNo;
    private String cancellationDate;
    private Integer agentCommission;
    private Integer commission;
    private Integer systemCommission;
    private Object cancellationPolicy;
    private Boolean cancelled;
    private Boolean isSuccess;
    private Object cancellationDetail;
    private Status status;
    private String timestamp;
    private String sessionId;
}
