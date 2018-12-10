package com.mauwahid.tm.travelmgt.repository.api.interfaces;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelHotel;

import java.util.Map;
import java.util.Set;

public interface HotelSearchInterface {

    Set<HotelHotel> searchHotel(Map param);
}
