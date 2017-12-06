package com.mauwahid.tm.travelmgt.controller.api.flight;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightBookReq2;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightBookResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.FlightBookService;
import com.mauwahid.tm.travelmgt.utils.ApiStatic;
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
@Api(tags = "Flight Book",  description="Operations for flight booking")
@Slf4j
public class FlightBookController {

    @Autowired
    private FlightBookService flightBookService;

    @Autowired
    private AuthService authService;


    @ApiOperation(value = "Flight Book", response = FlightBookResponse.class)
    @PostMapping("/book")
    public ResponseEntity book(HttpServletRequest request, @RequestHeader(name = "api-key") String apiKey,
                               @RequestBody FlightBookReq2 flightBookReq){

        long userId = authService.authKey(apiKey, ApiStatic.API_FLIGHT, ApiStatic.API_FLIGHT_BOOK, flightBookReq.toString().toString(), request.getRemoteAddr());

        if( userId == 0L){
            return new ResponseEntity(authService.respAuthFailed(),HttpStatus.UNAUTHORIZED);
        }

        FlightBookResponse response = flightBookService.bookFlight(userId, flightBookReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
