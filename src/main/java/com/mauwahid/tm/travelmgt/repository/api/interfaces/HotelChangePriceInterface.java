package com.mauwahid.tm.travelmgt.repository.api.interfaces;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelChangePriceResult;

import java.util.Map;

public interface HotelChangePriceInterface {

    HotelChangePriceResult changePrice(Map param);
}
