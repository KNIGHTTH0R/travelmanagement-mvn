package com.mauwahid.tm.travelmgt.service.agent;


import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.apimodel.*;
import com.mauwahid.tm.travelmgt.service.agent.pointer.PointerSearchSchedule;
import com.mauwahid.tm.travelmgt.service.agent.trevohub.SearchScheduleApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchScheduleService {

    @Autowired
    private SearchScheduleApi apiSearchTrevohub;

    @Autowired
    private PointerSearchSchedule apiPointerSearch;

    Logger logger = LoggerFactory.getLogger(SearchScheduleService.class);



    private SearchInfo getTrevohub(FlightSearchReq flightSearchReq){

        if(flightSearchReq.getAirlineName().equalsIgnoreCase("lion")){
            flightSearchReq.setAirlineName("lionair");
        }
        if(flightSearchReq.getRoundtrip().equalsIgnoreCase("1")){
            flightSearchReq.setRoundtrip("return");
        }
        if(flightSearchReq.getRoundtrip().equalsIgnoreCase("0")){
            flightSearchReq.setRoundtrip("oneway");
        }

        apiSearchTrevohub = new SearchScheduleApi();
        apiSearchTrevohub.setFlightSearchReq(flightSearchReq);

        logger.debug("Airline Type : "+ flightSearchReq.getAirlineType());

        return apiSearchTrevohub.populateSchedules();
    }

    private SearchInfo getPointer(FlightSearchReq flightSearchReq){


        if(flightSearchReq.getAirlineName().equalsIgnoreCase("lionair")){
            flightSearchReq.setAirlineName("lion");
        }
        if(flightSearchReq.getRoundtrip().equalsIgnoreCase("return")){
            flightSearchReq.setRoundtrip("1");
        }
        if(flightSearchReq.getRoundtrip().equalsIgnoreCase("oneway")){
            flightSearchReq.setRoundtrip("0");
        }

        apiPointerSearch = new PointerSearchSchedule();
        apiPointerSearch.setFlightSearchReq(flightSearchReq);

        return  apiPointerSearch.populateSchedule();


    }


    public ArrayList<SearchInfo> populateScheduleData(FlightSearchReq flightSearchReq){
        ArrayList<SearchInfo> data = new ArrayList<>();

        String[] searchApis = flightSearchReq.getApiSource();

        logger.debug("searchapis : "+searchApis.toString());


        if(searchApis.length>0){

            Set<String> searchApiSet = new HashSet<>(Arrays.asList(searchApis));


            if(searchApiSet.contains("trevohub")){
                logger.debug("cek trevohub");
                data.add(getTrevohub(flightSearchReq));
            }

            if(searchApiSet.contains("pointer")){
                logger.debug("cek pointer");
                data.add(getPointer(flightSearchReq));
            }


        }



        return data;

    }

}
