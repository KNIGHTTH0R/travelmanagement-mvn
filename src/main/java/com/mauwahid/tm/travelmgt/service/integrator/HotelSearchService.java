package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelSearchResponse;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelHotel;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelAvailability;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.HotelSearchInterface;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelSearch;
import com.mauwahid.tm.travelmgt.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class HotelSearchService {


    private HotelSearchResponse hotelSearchResponse;

    @Autowired
    private TrevoHotelSearch trevoHotelSearch;


    private HotelSearchInterface hotelSearchInterface;

    @Autowired
    private AstriHotelAvailability astriHotelAvailability;


    public HotelSearchResponse searchHotel(HotelSearchReq hotelSearchReq){

        Set<HotelHotel> hotels = agregate(hotelSearchReq);

        HotelSearchResponse response = translateResponse(hotels);

        return response;

    }

    private Set<HotelHotel> agregate(HotelSearchReq hotelSearchReq){


        Map param = null;
        Set<String> apiSources = new HashSet<>(Arrays.asList(hotelSearchReq.getApiSource()));

        Set<HotelHotel> hotels = new HashSet<>();

        if(apiSources.contains(Common.API_ASTRINDO)){
            hotelSearchInterface = new AstriHotelAvailability();
            AstriHotelAvailability.translateToParam(hotelSearchReq);

            hotels = hotelSearchInterface.searchHotel(param);
        }


      //  Set<HotelHotel> trevoHubHotel = trevoHotelSearch.searchHotel(param);
       // Set<HotelHotel> hotels = astriHotelAvailability.searchHotel(param);

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
