package com.mauwahid.tm.travelmgt.domain.api.response.hotel.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelIssueResult;
import com.mauwahid.tm.travelmgt.domain.api.response.DefaultResponse;
import lombok.Data;

@Data
public class HotelIssueResponse extends DefaultResponse {

    @JsonProperty("issue_result")
    private HotelIssueResult hotelIssue;
}
