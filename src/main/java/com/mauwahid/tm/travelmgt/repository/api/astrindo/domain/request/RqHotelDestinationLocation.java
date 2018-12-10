package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.request;

import lombok.Data;

@Data
public class RqHotelDestinationLocation {

    private String latitude;
    private String longitude;
    private String sessionId;
    private String ipAddress;
    private String userAgent;

}
