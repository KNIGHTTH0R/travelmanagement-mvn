package com.mauwahid.tm.travelmgt.domain.apimodel.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightSegment {


    @JsonProperty("class_id")
    private String classId;

    @JsonProperty("airline")
    private String airline;

    @JsonProperty("flight_number")
    private String flightNumber;

    @JsonProperty("origin")
    private String origin;

    @JsonProperty("depart_date")
    private String departDate;

    @JsonProperty("depart_time")
    private String departTime;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("arrive_date")
    private String arriveDate;

    @JsonProperty("arrive_time")
    private String arriveTime;

    @JsonProperty("class_code")
    private String classCode;

    @JsonProperty("flight_id")
    private String flightId;

    @JsonProperty("num")
    private String num;

    @JsonProperty("seq")
    private String seq;
}
