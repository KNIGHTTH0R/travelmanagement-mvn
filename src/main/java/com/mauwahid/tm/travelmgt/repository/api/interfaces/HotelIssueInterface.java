package com.mauwahid.tm.travelmgt.repository.api.interfaces;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelIssueResult;

import java.util.Map;

public interface HotelIssueInterface {

    HotelIssueResult issueHotel(Map param);
}
