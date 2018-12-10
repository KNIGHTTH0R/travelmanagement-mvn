package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain;

import lombok.Data;

import java.util.List;

@Data
public class RoomInfo {


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
    private Object currency;
    private String bfType;
    private Object bfTypeName;
    private Object roomType;
    private List<RoomList> roomList = null;
    private Object roomImage;
    private Object facility;
    private Object additionalFees;
    private Object onTheSpotFees;
}
