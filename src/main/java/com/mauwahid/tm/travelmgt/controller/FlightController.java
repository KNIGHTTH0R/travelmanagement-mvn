package com.mauwahid.tm.travelmgt.controller;


import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchDetailReq;
import com.mauwahid.tm.travelmgt.domain.api.response.ResSearch;
import com.mauwahid.tm.travelmgt.domain.api.response.ResSearchDetail;
import com.mauwahid.tm.travelmgt.domain.api.response.pointer.PointerSearch;
import com.mauwahid.tm.travelmgt.domain.apimodel.SearchInfo;
import com.mauwahid.tm.travelmgt.service.agent.ScheduleDetailService;
import com.mauwahid.tm.travelmgt.service.agent.SearchScheduleService;
import com.mauwahid.tm.travelmgt.service.agent.pointer.PointerSearchSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/flight")
public class FlightController {


    @Autowired
    private SearchScheduleService searchSchedule;

    @Autowired
    private ScheduleDetailService scheduleDetailService;

    @PostMapping("/search")
    public ResponseEntity searchFlight(@RequestHeader(name = "agent-key") String apiKey,
                                       @RequestBody FlightSearchReq flightSearchReq){

        ArrayList<SearchInfo> searchInfoList = new ArrayList<>();
        searchInfoList = searchSchedule.populateScheduleData(flightSearchReq);

        ResSearch resSearch = new ResSearch();
        resSearch.setErrorNo("0");
        resSearch.setSessionId("test-session-id");
        resSearch.setSearchInfos(searchInfoList);

        return new ResponseEntity(resSearch, HttpStatus.OK);

    }


    @PostMapping("/detail")
    public ResponseEntity searchFlight(@RequestHeader(name = "agent-key") String apiKey,
                                       @RequestBody FlightSearchDetailReq reqSearch){

        ResSearchDetail resSearchDetail = new ResSearchDetail();
        resSearchDetail = scheduleDetailService.getScheduleDetail(reqSearch);

        return new ResponseEntity(resSearchDetail, HttpStatus.OK);

    }

    @PostMapping("/search-pointer")
    public ResponseEntity searchFlightPointer(@RequestHeader(name = "agent-key") String apiKey,
                                      @RequestBody FlightSearchReq flightSearchReq){
        PointerSearchSchedule pointerSearchSchedule = new PointerSearchSchedule();

        pointerSearchSchedule.setFlightSearchReq(flightSearchReq);

        PointerSearch pointerSearch = pointerSearchSchedule.getPointerSearch();
        return new ResponseEntity(pointerSearch, HttpStatus.OK);

    }
}
