package com.mauwahid.tm.travelmgt.controller.api.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelDetailReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelDetailResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.HotelDetailService;
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
@Api(tags = "Hotel Detail",  description="Operations for Hotel's detail")
public class HotelDetailController {


    @Autowired
    private HotelDetailService hotelDetailService;

    @Autowired
    private AuthService authService;


    @ApiOperation(value = "Hotel Detail", response = HotelDetailResponse.class)
    @PostMapping("/detail")
    public ResponseEntity search(HttpServletRequest request, @RequestHeader(name = "api-key") String apiKey,
                                 @RequestBody HotelDetailReq hotelDetailReq){


        long userId = authService.authKey(apiKey, ApiStatic.API_HOTEL, ApiStatic.API_HOTEL_DETAIL, hotelDetailReq.toString(), request.getRemoteAddr());

        if( userId == 0L){
            return new ResponseEntity(authService.respAuthFailed(),HttpStatus.UNAUTHORIZED);
        }

        HotelDetailResponse response = hotelDetailService.getDetailHotel(userId, hotelDetailReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
