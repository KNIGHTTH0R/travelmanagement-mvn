package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.response;

import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.Status;
import lombok.Data;

@Data
public class RsHotelLogin {

    private Status status;
    private String timestamp;
    private String sessionId;
}
