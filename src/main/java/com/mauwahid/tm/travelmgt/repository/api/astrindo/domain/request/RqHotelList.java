package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.request;

import lombok.Data;

import java.util.List;

@Data
public class RqHotelList {


    private String paxPassport;
    private String destCountryCode;
    private String destCityCode;
    private String hotelCode;
    private Object roomCode;
    private String checkInDate;
    private String checkOutDate;
    private String bedType;
    private List<String> occupancy = null;
    private Integer numberOfResults;
    private List<String> displayOptions = null;
    private Integer page;
    private Object rateType;
    private String sessionId;
    private String ipAddress;
    private String userAgent;
}
