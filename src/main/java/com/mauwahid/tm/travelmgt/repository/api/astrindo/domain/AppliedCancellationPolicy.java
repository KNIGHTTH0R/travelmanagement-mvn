package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain;

import lombok.Data;

@Data
public class AppliedCancellationPolicy {
    private Integer exCancelDays;
    private String cancelDateFrom;
    private String cancelDateTo;
    private Object roomCategoryCode;
    private Object roomCategoryName;
    private Object remarks;
    private String chargeType;
    private String currency;
    private Integer chargeRate;
    private String description;
}
