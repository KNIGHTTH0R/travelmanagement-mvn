package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain;

import lombok.Data;

import java.util.List;

@Data
public class RoomList {
    private String sequence;
    private String serviceNo;
    private String typeName;
    private String typeCode;
    private String typeDescription;
    private String reqBedChild;
    private String roomCount;
    private String maxOccupancy;
    private String adultCount;
    private String childCount;
    private String totalPrice;
    private String totalDiscount;
    private String avgNightPrice;
    private String totalNightPrice;
    private String nettPrice;
    private String commPrice;
    private String agentCommission;
    private String grossPrice;
    private List<Rate_> rate = null;
    private List<Guest_> guests = null;
}
