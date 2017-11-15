package com.mauwahid.tm.travelmgt.domain.api.request.hotelbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaxData {

    @JsonProperty("PaxNo")
    private String paxNo;

    @JsonProperty("PaxTitle")
    private String paxTitle;

    @JsonProperty("PaxFirstName")
    private String paxFirstName;

    @JsonProperty("PaxLastName")
    private String paxLastName;

    @JsonProperty("PaxTypeCode")
    private String paxTypeCode;

    @JsonProperty("Nationality")
    private String nationality;

    @JsonProperty("Age")
    private String age;

    @JsonProperty("RoomID")
    private String roomId;

}
