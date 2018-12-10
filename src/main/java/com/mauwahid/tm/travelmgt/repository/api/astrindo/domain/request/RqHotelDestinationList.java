package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.request;

import lombok.Data;

@Data
public class RqHotelDestinationList {

    private String keyword;
    private String sessionId;
    private String ipAddress;
    private String userAgent;
}
