package com.mauwahid.tm.travelmgt.controller.flight;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightBookReq;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightBookResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightSearchResponse;
import com.mauwahid.tm.travelmgt.service.integrator.FlightBookService;
import com.mauwahid.tm.travelmgt.service.integrator.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/flight")
public class FlightBookController {

    @Autowired
    private FlightBookService flightBookService;


    @PostMapping("/book")
    public ResponseEntity book(@RequestHeader(name = "api-key") String apiKey,
                                       @RequestBody FlightBookReq flightBookReq){

        FlightBookResponse response = flightBookService.bookFlight(flightBookReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
