package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.response;

import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.AppliedCancellationPolicy;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.Status;
import lombok.Data;

import java.util.List;

@Data
public class RsHotelCheckCancellationCharge {

    private String transactionNo;
    private String checkDate;
    private Integer agentCommission;
    private Integer commission;
    private Integer systemCommission;
    private Integer cancellationCharge;
    private Integer refundableAmount;
    private List<AppliedCancellationPolicy> appliedCancellationPolicies = null;
    private Status status;
    private String timestamp;
    private String sessionId;
}
