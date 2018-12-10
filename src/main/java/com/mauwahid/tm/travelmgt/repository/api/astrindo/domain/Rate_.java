package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain;

import lombok.Data;

import java.util.List;

@Data
public class Rate_ {
    private Integer offset;
    private Integer nightPrice;
    private List<RoomRate_> roomRate = null;
    private RoomRateInfo_ roomRateInfo;
}
