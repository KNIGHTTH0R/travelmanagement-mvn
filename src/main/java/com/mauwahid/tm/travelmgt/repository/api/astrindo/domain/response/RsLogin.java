package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.response;

import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.Error;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class RsLogin {

    private Integer userId;
    private String sessionId;
    private List<Error> errors = null;
    private String timestamp;
    private Integer responseTime;
}
