package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelDestinationReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelDestinationResponse;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelDestination;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelDestination;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
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
        response.setStatus(StatusCode.NOT_IMPLEMENTED);
        response.setMessage(StatusCode.S_NOT_IMPLEMENTED);

        if(hotelDestinationReq.getApiSource().equalsIgnoreCase("astrindo")){
            Set<HotelDestination> hotelDestinations = fetchHotelDestination(hotelDestinationReq);
            response = translateResponse(hotelDestinations);

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

        response.setStatus(StatusCode.SUCCESS);
        response.setMessage(StatusCode.S_SUCCESS);

        response.setHotelDestination(hotelDestinationSet);

        return response;
    }
}
