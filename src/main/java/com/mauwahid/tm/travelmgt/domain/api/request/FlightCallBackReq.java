package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightCallBackReq {


    @JsonProperty("PnrId")
    private String pnrId;

    @JsonProperty("Progress")
    private double progress;

    @JsonProperty("Text")
    private String text;

    @JsonProperty("JobType")
    private String jobType;

}
