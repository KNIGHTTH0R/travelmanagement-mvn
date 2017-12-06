package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import lombok.Data;

@Data
public class DefaultResponse implements ResponseInterface {

    @JsonProperty("status")
    private int status = StatusCode.NOT_IMPLEMENTED;

    @JsonProperty("message")
    private String message = StatusCode.S_NOT_IMPLEMENTED;


    public DefaultResponse() {
    }

    public DefaultResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
