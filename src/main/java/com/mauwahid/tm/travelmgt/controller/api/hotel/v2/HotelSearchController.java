package com.mauwahid.tm.travelmgt.controller.api.hotel.v2;

import com.mauwahid.tm.travelmgt.domain.api.request.hotel.v2.HotelSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.hotel.v1.HotelSearchResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.hotel.v2.HotelSearchService;
import com.mauwahid.tm.travelmgt.utils.ApiStatic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/hotel/v2")
@Api(tags = "Hotel Search",  description="Operations for searching hotel")
public class HotelSearchController {


    @Autowired
    private HotelSearchService hotelSearchService;


    @Autowired
    private AuthService authService;


    @ApiOperation(value = "Hotel Search", response = HotelSearchResponse.class)
    @PostMapping("/search")
    public ResponseEntity search(HttpServletRequest request,
                                 @RequestHeader(name = "api-key") String apiKey,
                                 @RequestBody HotelSearchReq hotelSearchReq){

        long userId = authService.authKey(apiKey, ApiStatic.API_HOTEL, ApiStatic.API_HOTEL_SEARCH, hotelSearchReq.toString(), request.getRemoteAddr());

        if( userId == 0L){
            return new ResponseEntity(authService.respAuthFailed(),HttpStatus.UNAUTHORIZED);
        }

//        HotelSearchResponse response = hotelSearchService.searchHotel(userId,hotelSearchReq);
        HotelSearchResponse response = null;

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
