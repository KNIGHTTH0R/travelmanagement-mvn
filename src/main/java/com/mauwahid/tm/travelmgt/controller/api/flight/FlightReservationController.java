package com.mauwahid.tm.travelmgt.controller.api.flight;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightReservationStatusReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightBookResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightReservationStatusResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.FlightReservationStatusService;
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
@Api(tags = "Flight Reservation Status",  description="Operations for flight reservation")
@Slf4j
public class FlightReservationController {

    @Autowired
    private FlightReservationStatusService flightReservationStatusService;

    @Autowired
    private AuthService authService;


    @ApiOperation(value = "Flight reservation status", response = FlightBookResponse.class)
    @PostMapping("/reservation-status")
    public ResponseEntity book(HttpServletRequest request, @RequestHeader(name = "api-key") String apiKey,
                               @RequestBody FlightReservationStatusReq flightReservationStatusReq){

        long userId = authService.authKey(apiKey, ApiStatic.API_FLIGHT, ApiStatic.API_FLIGHT_TRANSACTION_DETAIL, flightReservationStatusReq.toString(), request.getRemoteAddr());

        if( userId == 0L){
            return new ResponseEntity(authService.respAuthFailed(), HttpStatus.UNAUTHORIZED);
        }

        FlightReservationStatusResponse response = flightReservationStatusService.reservationStatus(userId, flightReservationStatusReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
