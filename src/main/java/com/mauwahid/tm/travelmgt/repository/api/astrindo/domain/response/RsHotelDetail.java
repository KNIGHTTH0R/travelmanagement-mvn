package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.response;

import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.Hotel;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.Status;
import lombok.Data;

@Data
public class RsHotelDetail {

    private Hotel hotel;
    private Boolean priceChanged;
    private Object pointOfInterests;
    private Status status;
    private String timestamp;
    private String sessionId;

}
