package com.mauwahid.tm.travelmgt.repository.api.interfaces;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelHotel;

import java.util.Map;
import java.util.Set;

public interface HotelSearchInterface {


    public Set<HotelHotel> searchHotel(Map param);
}
