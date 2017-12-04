package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelDestinationReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelDestinationResponse;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelDestination;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class HotelDestinationService {

    @Autowired
    private AstriHotelDestination astriHotelDestination;


    public HotelDestinationResponse getDestination(HotelDestinationReq hotelDestinationReq){

        HotelDestinationResponse response = new HotelDestinationResponse();
        response.setStatus("2");
        response.setMessage("Not Implemented");

        if(hotelDestinationReq.getApiSource().equalsIgnoreCase("astrindo")){
            Set<HotelDestination> hotelDestinations = fetchHotelDestination(hotelDestinationReq);
            return translateResponse(hotelDestinations);

        }


        return response;

    }

    private Set<HotelDestination> fetchHotelDestination(HotelDestinationReq hotelDestinationReq){

        Map param = AstriHotelDestination.translateToParam(hotelDestinationReq);


        Set<HotelDestination> hotelDestinations = astriHotelDestination.getDestination(param);


        return hotelDestinations;

    }


    private HotelDestinationResponse translateResponse(Set<HotelDestination> hotelDestinationSet){
        HotelDestinationResponse response = new HotelDestinationResponse();

        response.setHotelDestination(hotelDestinationSet);

        return response;
    }
}
