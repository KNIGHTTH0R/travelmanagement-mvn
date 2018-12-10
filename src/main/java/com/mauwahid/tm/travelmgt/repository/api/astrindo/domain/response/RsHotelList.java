package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.response;

import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.Hotel;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.Status;
import lombok.Data;

import java.util.List;

@Data
public class RsHotelList {

    private List<Hotel> hotels = null;
    private Object pointOfInterests;
    private Integer pageNumber;
    private Integer totalPage;
    private Integer totalResults;
    private Object promos;
    private Object suggestedHotels;
    private Status status;
    private String timestamp;
    private String sessionId;
}
