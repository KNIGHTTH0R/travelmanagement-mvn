package com.mauwahid.tm.travelmgt.controller.api.flight;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightCancelReq;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightIssueReq;
import com.mauwahid.tm.travelmgt.domain.api.response.DefaultResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightCancelResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightIssueResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.FlightCancelService;
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
@Api(tags = "Flight Cancel",  description="Operations for flight canceling")
public class FlightCancelController {

    @Autowired
    private FlightCancelService flightCancelService;

    @Autowired
    private AuthService authService;


    @ApiOperation(value = "Flight Cancel", response = DefaultResponse.class)
    @PostMapping("/cancel")
    public ResponseEntity book(HttpServletRequest request, @RequestHeader(name = "api-key") String apiKey,
                               @RequestBody FlightCancelReq flightCancelReq){

        long userId = authService.authKey(apiKey, ApiStatic.API_FLIGHT, ApiStatic.API_FLIGHT_ISSUE, flightCancelReq.toString().toString(), request.getRemoteAddr());

        if( userId == 0L){
            return new ResponseEntity(authService.respAuthFailed(), HttpStatus.UNAUTHORIZED);
        }

        FlightCancelResponse response = flightCancelService.cancelFlight(userId, flightCancelReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
