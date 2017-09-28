package com.mauwahid.tm.travelmgt.domain.apimodel;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AirlineClass {

    @JsonProperty("class_id")
    private String classId;

    @JsonProperty("class_name")
    private String className;

    @JsonProperty("class_type")
    private String classType;

    @JsonProperty("seat")
    private String seat;

    private Price price;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
