package com.mauwahid.tm.travelmgt.service.api;


import com.mauwahid.tm.travelmgt.domain.api.request.ReqSearch;
import com.mauwahid.tm.travelmgt.domain.api.response.pointer.*;
import com.mauwahid.tm.travelmgt.domain.api.response.trevohub.TrevoHubTemplateWObj;
import com.mauwahid.tm.travelmgt.domain.apimodel.*;
import com.mauwahid.tm.travelmgt.service.api.pointer.ApiPointerSearch;
import com.mauwahid.tm.travelmgt.service.api.pointer.PointerSearchSchedule;
import com.mauwahid.tm.travelmgt.service.api.trevohub.ApiSearch;
import com.mauwahid.tm.travelmgt.service.api.trevohub.SearchSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchScheduleService {

    @Autowired
    private SearchSchedule apiSearchTrevohub;

    @Autowired
    private PointerSearchSchedule apiPointerSearch;

    Logger logger = LoggerFactory.getLogger(SearchScheduleService.class);



    private SearchInfo getTrevohub(ReqSearch reqSearch){

        if(reqSearch.getAirlineName().equalsIgnoreCase("lion")){
            reqSearch.setAirlineName("lionair");
        }
        if(reqSearch.getRoundtrip().equalsIgnoreCase("1")){
            reqSearch.setRoundtrip("return");
        }
        if(reqSearch.getRoundtrip().equalsIgnoreCase("0")){
            reqSearch.setRoundtrip("oneway");
        }

        apiSearchTrevohub = new SearchSchedule();
        apiSearchTrevohub.setReqSearch(reqSearch);

        logger.debug("Airline Type : "+reqSearch.getAirlineType());

        return apiSearchTrevohub.populateSchedules();
    }

    private SearchInfo getPointer(ReqSearch reqSearch){


        if(reqSearch.getAirlineName().equalsIgnoreCase("lionair")){
            reqSearch.setAirlineName("lion");
        }
        if(reqSearch.getRoundtrip().equalsIgnoreCase("return")){
            reqSearch.setRoundtrip("1");
        }
        if(reqSearch.getRoundtrip().equalsIgnoreCase("oneway")){
            reqSearch.setRoundtrip("0");
        }

        apiPointerSearch = new PointerSearchSchedule();
        apiPointerSearch.setReqSearch(reqSearch);

        return  apiPointerSearch.populateSchedule();


    }


    public ArrayList<SearchInfo> populateScheduleData(ReqSearch reqSearch){
        ArrayList<SearchInfo> data = new ArrayList<>();

        String[] searchApis = reqSearch.getApiSource();

        logger.debug("searchapis : "+searchApis.toString());


        if(searchApis.length>0){

            Set<String> searchApiSet = new HashSet<>(Arrays.asList(searchApis));


            if(searchApiSet.contains("pointer")){
                logger.debug("cek pointer");
                data.add(getPointer(reqSearch));
            }

            if(searchApiSet.contains("trevohub")){
                logger.debug("cek trevohub");
                data.add(getTrevohub(reqSearch));
            }
        }



        return data;

    }

}
