package com.mauwahid.tm.travelmgt.repository.api.opsigo.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SegmentOps {

    @JsonProperty("ClassId")
    private String classId;

    @JsonProperty("Airline")
    private int airline;

    @JsonProperty("FlightNumber")
    private String flightNumber;

    @JsonProperty("Origin")
    private String origin;

    @JsonProperty("DepartDate")
    private String departDate;

    @JsonProperty("DepartTime")
    private String departTime;

    @JsonProperty("Destination")
    private String destination;

    @JsonProperty("ArriveDate")
    private String arriveDate;

    @JsonProperty("ArriveTime")
    private String arriveTime;

    @JsonProperty("ClassCode")
    private String classCode;

    @JsonProperty("FlightId")
    private String flightId;

    @JsonProperty("Num")
    private int num;

    @JsonProperty("Seq")
    private int seq;
}
