package com.mauwahid.tm.travelmgt.repository.api.interfaces;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelChangePriceResult;

import java.util.Map;

public interface HotelChangePriceInterface {

    HotelChangePriceResult changePrice(Map param);
}
