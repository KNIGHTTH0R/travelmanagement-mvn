package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.response;

import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.CityList;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.Status;
import lombok.Data;

import java.util.List;

@Data
public class RsHotelDestinationLocation {
    private List<CityList> cityList = null;
    private Object hotelList;
    private Status status;
    private String timestamp;
    private String sessionId;


}
