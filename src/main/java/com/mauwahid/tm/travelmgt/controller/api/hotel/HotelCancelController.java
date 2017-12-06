package com.mauwahid.tm.travelmgt.controller.api.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelCancelReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelCancelResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.HotelCancelService;
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
@Api(tags = "Hotel Cancel")
public class HotelCancelController {

    @Autowired
    private HotelCancelService hotelCancelService;

    @Autowired
    private AuthService authService;



    @ApiOperation(value = "Hotel Cancel", response = HotelCancelResponse.class)
    @PostMapping("/cancel")
    public ResponseEntity cancel(HttpServletRequest request, @RequestHeader(name = "api-key") String apiKey,
                                 @RequestBody HotelCancelReq hotelCancelReq){


        long userId = authService.authKey(apiKey, ApiStatic.API_HOTEL, ApiStatic.API_HOTEL_DETAIL, hotelCancelReq.toString(), request.getRemoteAddr());

        if( userId == 0L){
            return new ResponseEntity(authService.respAuthFailed(),HttpStatus.UNAUTHORIZED);
        }

        HotelCancelResponse response = hotelCancelService.cancelHotel(userId,hotelCancelReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
