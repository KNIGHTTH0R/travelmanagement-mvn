package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain;

import lombok.Data;

@Data
public class RoomRateInfo {
    private Integer childMinAge;
    private Integer childMaxAge;
    private Integer minStayDay;
    private String minStayType;
    private Integer minStayRate;
    private String promotionName;
    private Boolean promotionValue;
    private Object promoCode;
}
