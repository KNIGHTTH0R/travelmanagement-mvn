package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelCancelReq {
    @JsonProperty("api_source")
    private String apiSource;

    @JsonProperty("trx_no")
    private String trxNo;

}

