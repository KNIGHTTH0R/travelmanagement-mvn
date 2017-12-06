package com.mauwahid.tm.travelmgt.controller.api.flight;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightIssueReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightIssueResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.FlightIssueService;
import com.mauwahid.tm.travelmgt.utils.ApiStatic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/flight")
@Api(tags = "Flight Issue",  description="Operations for flight issuing")
public class FlightIssueController {

    @Autowired
    private FlightIssueService flightIssueService;

    @Autowired
    private AuthService authService;


    @ApiOperation(value = "Flight Issue", response = FlightIssueResponse.class)
    @PostMapping("/issue")
    public ResponseEntity book(HttpServletRequest request, @RequestHeader(name = "api-key") String apiKey,
                               @RequestBody FlightIssueReq flightIssueReq){

        long userId = authService.authKey(apiKey, ApiStatic.API_FLIGHT, ApiStatic.API_FLIGHT_ISSUE, flightIssueReq.toString().toString(), request.getRemoteAddr());

        if( userId == 0L){
            return new ResponseEntity(authService.respAuthFailed(),HttpStatus.UNAUTHORIZED);
        }

        FlightIssueResponse response = flightIssueService.issueFlight(userId, flightIssueReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }

}
