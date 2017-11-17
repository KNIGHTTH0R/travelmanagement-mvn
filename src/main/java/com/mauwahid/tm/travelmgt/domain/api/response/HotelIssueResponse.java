package com.mauwahid.tm.travelmgt.domain.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelIssueResult;
import lombok.Data;

@Data
public class HotelIssueResponse extends DefaultResponse {

    @JsonProperty("issue_result")
    private HotelIssueResult hotelIssue;
}
