package com.mauwahid.tm.travelmgt.controller.flight;

import com.mauwahid.tm.travelmgt.domain.api.old.ResSearch;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightSearchResponse;
import com.mauwahid.tm.travelmgt.domain.apimodel.old.SearchInfo;
import com.mauwahid.tm.travelmgt.service.integrator.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/flight")
public class FlightSearchController {


    @Autowired
    private FlightSearchService flightSearchService;


    @PostMapping("/search")
    public ResponseEntity searchFlight(@RequestHeader(name = "api-key") String apiKey,
                                       @RequestBody FlightSearchReq flightSearchReq){

        FlightSearchResponse response = flightSearchService.searchFlight(flightSearchReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
