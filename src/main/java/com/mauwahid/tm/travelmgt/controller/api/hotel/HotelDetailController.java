package com.mauwahid.tm.travelmgt.controller.api.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelDetailReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelDetailResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.HotelDetailService;
import com.mauwahid.tm.travelmgt.utils.ApiStatic;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity search(@RequestHeader(name = "api-key") String apiKey,
                                 @RequestBody HotelDetailReq hotelDetailReq){


        long userId = authService.authKey(apiKey,request.getRemoteAddr(),ApiStatic.API_HOTEL_SEARCH);

        if( userId == 0L){


            LogErrorHelper.getInstance().saveAuthError(apiKey);

            return new ResponseEntity(authService.respAuthFailed(),HttpStatus.FORBIDDEN);

        }

        HotelDetailResponse response = hotelDetailService.getDetailHotel(hotelDetailReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
