package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain;

import lombok.Data;

@Data
public class RoomRate {

    private Integer no;
    private Integer adultCount;
    private Integer childCount;
    private Integer childAge1;
    private Integer childAge2;
    private Integer roomPrice;
    private Integer discount;
    private Integer minstayPrice;
    private Integer compulsoryPrice;
    private Integer supplementPrice;
    private Integer promotionBfPrice;
    private Integer earlyBirdDiscount;
    private Integer commissionPrice;
    private String sRoomType;
}
