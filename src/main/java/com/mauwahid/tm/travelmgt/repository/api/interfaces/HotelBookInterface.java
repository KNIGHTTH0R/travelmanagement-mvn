package com.mauwahid.tm.travelmgt.repository.api.interfaces;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelBookResult;

import java.util.Map;

public interface HotelBookInterface {

    HotelBookResult bookHotel(Map param);
}
