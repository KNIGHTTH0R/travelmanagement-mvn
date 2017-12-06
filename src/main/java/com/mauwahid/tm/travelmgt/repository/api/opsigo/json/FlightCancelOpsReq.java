package com.mauwahid.tm.travelmgt.repository.api.opsigo.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightCancelOpsReq {

    @JsonProperty("PnrId")
    private String pnrId;

    @JsonProperty("CallbackUri")
    private String callbackUri;
}
