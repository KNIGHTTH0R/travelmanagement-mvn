package com.mauwahid.tm.travelmgt.repository.api.interfaces;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelHotel;

import java.util.Map;

public interface HotelDetailInterface {

    HotelHotel getDetailHotel(Map param);
}
