package com.mauwahid.tm.travelmgt.repository.api.interfaces;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelCancelResult;

import java.util.Map;

public interface HotelCancelInterface {

    HotelCancelResult cancelHotel(Map param);
}
