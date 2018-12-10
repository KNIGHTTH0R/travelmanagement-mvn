package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain;

import lombok.Data;

import java.util.List;

@Data
public class Promo {

    private String effectiveStartDate;
    private String effectiveEndDate;
    private String travelStartDate;
    private String travelEndDate;
    private String promoDescription;
    private String promoType;
    private Integer promoValue;
    private Integer nightOfStayApply;
    private Boolean nightOfStayApplyRecurring;
    private Integer stayMaximumDurationDays;
    private Integer stayMinimumDurationDays;
    private List<String> rateType = null;
    private Integer advancePurchaseMinimumDays;
    private Object promoExcludedDates;
    private Boolean partialExcludedDates;
    private Object advancePurchaseMaximumDays;
    private Object promoApplies;
    private Object stayStartRestriction;
    private Object stayEndRestriction;
    private Object stayIncludeEachOf;
    private Object stayIncludeOneOf;
    private Boolean partialApply;
}
