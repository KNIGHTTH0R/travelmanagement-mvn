package com.mauwahid.tm.travelmgt.controller;


import com.mauwahid.tm.travelmgt.domain.api.request.ReqSearch;
import com.mauwahid.tm.travelmgt.domain.api.response.ResSearch;
import com.mauwahid.tm.travelmgt.domain.api.response.pointer.PointerSearch;
import com.mauwahid.tm.travelmgt.domain.apimodel.SearchInfo;
import com.mauwahid.tm.travelmgt.service.api.SearchScheduleService;
import com.mauwahid.tm.travelmgt.service.api.pointer.PointerSearchSchedule;
import com.mauwahid.tm.travelmgt.service.api.trevohub.SearchSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {


    @Autowired
    private SearchScheduleService searchSchedule;

    @PostMapping("/search")
    public ResponseEntity searchFlight(@RequestHeader(name = "api-key") String apiKey,
                                       @RequestBody ReqSearch reqSearch){

        ArrayList<SearchInfo> searchInfoList = new ArrayList<>();
        searchInfoList = searchSchedule.populateScheduleData(reqSearch);

        ResSearch resSearch = new ResSearch();
        resSearch.setErrorNo("0");
        resSearch.setSessionId("test-session-id");
        resSearch.setSearchInfos(searchInfoList);

        return new ResponseEntity(resSearch, HttpStatus.OK);

    }

    @PostMapping("/search-pointer")
    public ResponseEntity searchFlightPointer(@RequestHeader(name = "api-key") String apiKey,
                                      @RequestBody ReqSearch reqSearch){
        PointerSearchSchedule pointerSearchSchedule = new PointerSearchSchedule();

        pointerSearchSchedule.setReqSearch(reqSearch);

        PointerSearch pointerSearch = pointerSearchSchedule.getPointerSearch();
        return new ResponseEntity(pointerSearch, HttpStatus.OK);

    }
}
