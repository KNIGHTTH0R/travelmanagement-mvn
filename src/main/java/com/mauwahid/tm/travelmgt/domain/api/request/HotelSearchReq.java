package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelSearchReq {

    private String city;

    @JsonProperty("destination_key")
    private String destinationKey;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("check_in")
    private String checkIn;

    @JsonProperty("check_out")
    private String checkOut;

  //  private String room;

    private String limit;

    private String sort;

    @JsonProperty("total_adult")
    private String totalAdult;

    @JsonProperty("total_child")
    private String totalChild;

    @JsonProperty("api_source")
    private String[] apiSource;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDestinationKey() {
        return destinationKey;
    }

    public void setDestinationKey(String destinationKey) {
        this.destinationKey = destinationKey;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String[] getApiSource() {
        return apiSource;
    }

    public void setApiSource(String[] apiSource) {
        this.apiSource = apiSource;
    }

    public String getTotalAdult() {
        return totalAdult;
    }

    public void setTotalAdult(String totalAdult) {
        this.totalAdult = totalAdult;
    }

    public String getTotalChild() {
        return totalChild;
    }

    public void setTotalChild(String totalChild) {
        this.totalChild = totalChild;
    }
}
