package com.mauwahid.tm.travelmgt.domain.apimodel.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;


public class HotelAddInfo {

    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("error_desc")
    private String errorDesc;

    @JsonProperty("room_info")
    private String roomInfo;

    private String property;

    @JsonProperty("room_detail")
    private String roomDetail;

    @JsonProperty("check_in_instruction")
    private String checkInInstructions;

    private String area;

    @JsonProperty("facilities")
    private Set<String> facitilies;

    public String getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(String roomInfo) {
        this.roomInfo = roomInfo;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getRoomDetail() {
        return roomDetail;
    }

    public void setRoomDetail(String roomDetail) {
        this.roomDetail = roomDetail;
    }

    public String getCheckInInstructions() {
        return checkInInstructions;
    }

    public void setCheckInInstructions(String checkInInstructions) {
        this.checkInInstructions = checkInInstructions;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Set<String> getFacitilies() {
        return facitilies;
    }

    public void setFacitilies(Set<String> facitilies) {
        this.facitilies = facitilies;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
