package com.mauwahid.tm.travelmgt.repository.api.astrindo.v2;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelHotel;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.HotelSearchInterface;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Component
public class AstriHotelList implements HotelSearchInterface {

    @Override
    public Set<HotelHotel> searchHotel(Map param) {
        return null;
    }
}
