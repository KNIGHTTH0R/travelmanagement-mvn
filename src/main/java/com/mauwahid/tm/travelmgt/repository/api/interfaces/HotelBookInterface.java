package com.mauwahid.tm.travelmgt.repository.api.interfaces;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelBookResult;

import java.util.Map;

public interface HotelBookInterface {

    HotelBookResult bookHotel(Map param);
}
