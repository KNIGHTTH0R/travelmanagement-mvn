package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightSearchResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelSearchResponse;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightTravel;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelHotel;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelAvailability;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightSearch;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class HotelSearchService {


    private HotelSearchResponse hotelSearchResponse;

    @Autowired
    private TrevoHotelSearch trevoHotelSearch;

    @Autowired
    private AstriHotelAvailability astriHotelAvailability;


    public HotelSearchResponse searchHotel(HotelSearchReq hotelSearchReq){

        Set<HotelHotel> hotels = agreate(hotelSearchReq);

        HotelSearchResponse response = translateResponse(hotels);

        return response;

    }

    private Set<HotelHotel> agreate(HotelSearchReq hotelSearchReq){

        Map param = AstriHotelAvailability.translateToParam(hotelSearchReq);


      //  Set<HotelHotel> trevoHubHotel = trevoHotelSearch.searchHotel(param);
        Set<HotelHotel> hotels = astriHotelAvailability.searchHotel(param);

        return hotels;

    }



    private HotelSearchResponse translateResponse(Set<HotelHotel> hotels){
        HotelSearchResponse hotelSearchResponse = new HotelSearchResponse();
        hotelSearchResponse.setSessionKey("abcdefghijklm");
        hotelSearchResponse.setHotels(hotels);
        hotelSearchResponse.setStatusCode("0");
        hotelSearchResponse.setStatusDesc("success");

        return hotelSearchResponse;
    }
}
