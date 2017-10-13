package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightSearchResponse;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightTravel;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class FlightSearchService {


    @Autowired
    private PointerFlightSearch pointerFlightSearch;



    public FlightSearchResponse searchFlight(FlightSearchReq flightSearchReq){

        Set<FlightTravel> departTravel = null;


        Set<String> apiSource = new HashSet<>(Arrays.asList(flightSearchReq.getApiSource()));

        if(apiSource.contains("pointer"))
            departTravel = departTravel(flightSearchReq);

        Set<FlightTravel> returnTravel = null;

        if(flightSearchReq.getRoundtrip().equalsIgnoreCase("1"))
            if(apiSource.contains("pointer"))
                returnTravel = returnTravel(flightSearchReq);

        FlightSearchResponse response = translateResponse(departTravel,returnTravel);

        return response;

    }

    private Set<FlightTravel> departTravel(FlightSearchReq flightSearchReq){

        //api pointer
        Map param = PointerFlightSearch.translateParamDepart(flightSearchReq);
        Set<FlightTravel> flightTravels = pointerFlightSearch.searchTravel(param);
        return flightTravels;

    }

    private Set<FlightTravel> returnTravel(FlightSearchReq flightSearchReq){
        Map param = PointerFlightSearch.translateParamReturn(flightSearchReq);
        Set<FlightTravel> flightTravels = pointerFlightSearch.searchTravel(param);
        return flightTravels;

    }

    private FlightSearchResponse translateResponse(Set<FlightTravel> departTravel, Set<FlightTravel> returnTravel){
        FlightSearchResponse flightSearchResponse = new FlightSearchResponse();
        flightSearchResponse.setSessionKey("abcdefghijklm");
        flightSearchResponse.setDepartTravel(departTravel);
        flightSearchResponse.setReturnTravel(returnTravel);
        flightSearchResponse.setStatusCode("0");
        flightSearchResponse.setStatusDesc("success");

        return flightSearchResponse;
    }
}
