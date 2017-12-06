package com.mauwahid.tm.travelmgt.controller.api.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelBookReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelBookResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.HotelBookService;
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
@Api(tags = "Hotel Book",  description="Operations for Hotel's booking")
public class HotelBookController {


    @Autowired
    private HotelBookService hotelBookService;

    @Autowired
    private AuthService authService;



    @ApiOperation(value = "Hotel Book", response = HotelBookResponse.class)
    @PostMapping("/book")
    public ResponseEntity bookHotel(HttpServletRequest request,
                                    @RequestHeader(name = "api-key") String apiKey,
                                    @RequestBody HotelBookReq hotelBookReq){

        long userId = authService.authKey(apiKey, ApiStatic.API_HOTEL, ApiStatic.API_HOTEL_DETAIL, hotelBookReq.toString(), request.getRemoteAddr());

        if( userId == 0L){
            return new ResponseEntity(authService.respAuthFailed(),HttpStatus.UNAUTHORIZED);
        }

        HotelBookResponse response = hotelBookService.bookHotel(userId, hotelBookReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}