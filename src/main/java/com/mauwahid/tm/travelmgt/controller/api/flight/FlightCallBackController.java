package com.mauwahid.tm.travelmgt.controller.api.flight;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightCallBackReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightIssueResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.FlightIssueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/flight")
@Api(tags = "Call Back API", description="Operations for callback flight response")
@Slf4j
public class FlightCallBackController {


    @Autowired
    private FlightIssueService flightIssueService;

    @Autowired
    private AuthService authService;


    @ApiOperation(value = "Book Callback", response = FlightIssueResponse.class)
    @PostMapping("/book-callback")
    public ResponseEntity book(HttpServletRequest request,
                               @RequestBody FlightCallBackReq flightCallBackReq){

        return  new ResponseEntity(HttpStatus.OK);
    }


    @ApiOperation(value = "Flight Issue Callback", response = FlightIssueResponse.class)
    @PostMapping("/issue-callback")
    public ResponseEntity issue(HttpServletRequest request,
                               @RequestBody FlightCallBackReq flightCallBackReq){

        return  new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Flight Cancel Callback", response = FlightIssueResponse.class)
    @PostMapping("/cancel-callback")
    public ResponseEntity cancel(HttpServletRequest request,
                               @RequestBody FlightCallBackReq flightCallBackReq){

        return  new ResponseEntity(HttpStatus.OK);
    }


}
