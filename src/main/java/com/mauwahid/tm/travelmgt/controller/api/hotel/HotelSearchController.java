package com.mauwahid.tm.travelmgt.controller.api.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelSearchResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.HotelSearchService;
import com.mauwahid.tm.travelmgt.utils.ApiStatic;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/hotel")
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

        long userId = authService.authKey(apiKey,request.getRemoteAddr(),ApiStatic.API_HOTEL_SEARCH);

        if( userId == 0L){


            LogErrorHelper.getInstance().saveAuthError(apiKey);

            return new ResponseEntity(authService.respAuthFailed(),HttpStatus.FORBIDDEN);

        }

        HotelSearchResponse response = hotelSearchService.searchHotel(userId,hotelSearchReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
