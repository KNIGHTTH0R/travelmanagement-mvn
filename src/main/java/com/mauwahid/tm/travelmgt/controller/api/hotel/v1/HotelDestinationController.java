package com.mauwahid.tm.travelmgt.controller.api.hotel.v1;

import com.mauwahid.tm.travelmgt.domain.api.request.hotel.v1.HotelDestinationReq;
import com.mauwahid.tm.travelmgt.domain.api.response.hotel.v1.HotelDestinationResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.hotel.v1.HotelDestinationService;
import com.mauwahid.tm.travelmgt.utils.ApiStatic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/hotel")
@Api(tags = "Hotel Destination")
public class HotelDestinationController {


    @Autowired
    private HotelDestinationService hotelDestinationService;

    @Autowired
    private AuthService authService;


    @ApiOperation(value = "Hotel Destination", response = HotelDestinationResponse.class)
    @PostMapping("/destination")
    public ResponseEntity search(HttpServletRequest request, @RequestHeader(name = "api-key") String apiKey,
                                 @RequestBody HotelDestinationReq hotelDestinationReq){

        long userId = authService.authKey(apiKey, ApiStatic.API_HOTEL, ApiStatic.API_HOTEL_DETAIL, hotelDestinationReq.toString(), request.getRemoteAddr());

        if( userId == 0L){
            return new ResponseEntity(authService.respAuthFailed(),HttpStatus.UNAUTHORIZED);
        }

        HotelDestinationResponse response = hotelDestinationService.getDestination(hotelDestinationReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
