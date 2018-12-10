package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain;

import lombok.Data;

import java.util.List;

@Data
public class RoomType {

    private Integer sequence;
    private Object serviceNo;
    private String typeName;
    private Object typeCode;
    private Object typeDescription;
    private Boolean reqBedChild;
    private Integer roomCount;
    private Integer maxOccupancy;
    private Integer adultCount;
    private Integer childCount;
    private Integer totalPrice;
    private Integer totalDiscount;
    private Integer avgNightPrice;
    private Integer totalNightPrice;
    private Integer nettPrice;
    private Integer commPrice;
    private Integer agentCommission;
    private Integer grossPrice;
    private List<Rate> rate = null;
    private Object guests;
}
