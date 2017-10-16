package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightSearchResponse;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightTravel;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightSearch;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoFlightSearch;
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


    @Autowired
    private TrevoFlightSearch trevoFlightSearch;



    public FlightSearchResponse searchFlight(FlightSearchReq flightSearchReq){

        Set<FlightTravel> departs = null;
        departs = departTravel(flightSearchReq);

        Set<FlightTravel> returns = null;
        if(flightSearchReq.getRoundtrip().equalsIgnoreCase("1"))
                returns = returnTravel(flightSearchReq);

        FlightSearchResponse response = translateResponse(departs,returns);

        return response;

    }

    private Set<FlightTravel> departTravel(FlightSearchReq flightSearchReq){

        //api pointer
        Set<FlightTravel> flightTravels = new HashSet<>();
        Set<FlightTravel> travelsTemp = null;

        Set<String> apis = new HashSet<>(Arrays.asList(flightSearchReq.getApiSource()));

        if(apis.contains("pointer")) {
            travelsTemp = pointerFlightSearch.departTravel(flightSearchReq);
            if (travelsTemp != null)
                flightTravels.addAll(travelsTemp);
        }

        if(apis.contains("trevohub")){
            travelsTemp = trevoFlightSearch.departTravel(flightSearchReq);
            if(travelsTemp!=null)
                flightTravels.addAll(travelsTemp);

        }

        return flightTravels;

    }

    private Set<FlightTravel> returnTravel(FlightSearchReq flightSearchReq){

        //api pointer
        Set<FlightTravel> flightTravels = new HashSet<>();
        Set<FlightTravel> travelsTemp = null;

        Set<String> apis = new HashSet<>(Arrays.asList(flightSearchReq.getApiSource()));


        if(apis.contains("pointer")){
            travelsTemp = pointerFlightSearch.returnTravel(flightSearchReq);
            if(travelsTemp!=null)
                flightTravels.addAll(travelsTemp);
        }

        if(apis.contains("trevohub")){
            travelsTemp = trevoFlightSearch.returnTravel(flightSearchReq);
            if(travelsTemp!=null)
                flightTravels.addAll(travelsTemp);

        }

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
