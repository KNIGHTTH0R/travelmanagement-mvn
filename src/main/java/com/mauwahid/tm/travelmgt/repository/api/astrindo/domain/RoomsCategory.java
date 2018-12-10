package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain;

import lombok.Data;

@Data
public class RoomsCategory {

    private String code;
    private String name;
    private Object rateType;
    private Boolean isRefundable;
    private Integer totalDiscount;
    private Integer totalNightPrice;
    private Integer netPrice;
    private Integer grossPrice;
    private Integer commPrice;
    private Integer totalAdditionalFee;
    private String currency;
    private String bfType;
    private Object bfTypeName;
    private RoomType roomType;
    private Object roomList;
    private Object roomImage;
    private Object facility;
    private Object additionalFees;
    private Object onTheSpotFees;
}
