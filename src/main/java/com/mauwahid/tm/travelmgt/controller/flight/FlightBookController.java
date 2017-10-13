package com.mauwahid.tm.travelmgt.controller.flight;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightBookReq;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightBookResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightSearchResponse;
import com.mauwahid.tm.travelmgt.service.integrator.FlightBookService;
import com.mauwahid.tm.travelmgt.service.integrator.FlightSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/flight")
@Api(tags = "Flight Book",  description="Operations for flight booking")
@Slf4j
public class FlightBookController {

    @Autowired
    private FlightBookService flightBookService;


    @ApiOperation(value = "Flight Book", response = FlightBookResponse.class)
    @PostMapping("/book")
    public ResponseEntity book(@RequestHeader(name = "api-key") String apiKey,
                                       @RequestBody FlightBookReq flightBookReq){


        FlightBookResponse response = flightBookService.bookFlight(flightBookReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
