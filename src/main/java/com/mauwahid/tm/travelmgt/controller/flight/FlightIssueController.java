package com.mauwahid.tm.travelmgt.controller.flight;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightIssueReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightBookResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightIssueResponse;
import com.mauwahid.tm.travelmgt.service.integrator.FlightIssueService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/flight")
public class FlightIssueController {

    @Autowired
    private FlightIssueService flightIssueService;


    @ApiOperation(value = "Flight Book", response = FlightIssueResponse.class)
    @PostMapping("/issue")
    public ResponseEntity book(@RequestHeader(name = "api-key") String apiKey,
                               @RequestBody FlightIssueReq flightIssueReq){

        FlightIssueResponse response = flightIssueService.issueFlight(flightIssueReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
