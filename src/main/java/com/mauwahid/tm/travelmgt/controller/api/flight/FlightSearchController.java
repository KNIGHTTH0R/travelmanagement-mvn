package com.mauwahid.tm.travelmgt.controller.api.flight;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightSearchResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.FlightSearchService;
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
@Api(tags = "Flight Search",  description="Operations for searching flight schedule")
public class FlightSearchController {


    @Autowired
    private FlightSearchService flightSearchService;

    @Autowired
    private AuthService authService;



    @ApiOperation(value = "Flight Search", response = FlightSearchResponse.class)
    @PostMapping("/search")
    public ResponseEntity searchFlight(HttpServletRequest request, @RequestHeader(name = "api-key") String apiKey,
                                       @RequestBody FlightSearchReq flightSearchReq){

        long userId = authService.authKey(apiKey, ApiStatic.API_FLIGHT, ApiStatic.API_FLIGHT_SEARCH, flightSearchReq.toString().toString(), request.getRemoteAddr());

        if( userId == 0L){
            return new ResponseEntity(authService.respAuthFailed(),HttpStatus.UNAUTHORIZED);
        }
        FlightSearchResponse response = flightSearchService.searchFlight(userId, flightSearchReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
