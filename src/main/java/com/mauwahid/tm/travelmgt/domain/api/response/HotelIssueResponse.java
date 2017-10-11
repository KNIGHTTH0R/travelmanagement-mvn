package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelIssue;

public class HotelIssueResponse extends DefaultResponse {

    @JsonProperty("issue_result")
    private HotelIssue hotelIssue;

    public HotelIssue getHotelIssue() {
        return hotelIssue;
    }

    public void setHotelIssue(HotelIssue hotelIssue) {
        this.hotelIssue = hotelIssue;
    }
}
