package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DefaultResponse {

    @JsonProperty("status_code")
    private String statusCode = "0";

    @JsonProperty("status_desc")
    private String statusDesc = "success";


    public DefaultResponse() {
    }

    public DefaultResponse(String statusCode, String statusDesc) {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
