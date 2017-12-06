package com.mauwahid.tm.travelmgt.controller.api.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelChangePriceReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelChangePriceResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.HotelChangePriceService;
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
@Api(tags = "Hotel Change Price",  description="")
public class HotelChangePriceController {

    @Autowired
    private HotelChangePriceService hotelChangePriceService;

    @Autowired
    private AuthService authService;



    @ApiOperation(value = "Change Price", response = HotelChangePriceResponse.class)
    @PostMapping("/change_price")
    public ResponseEntity changePrice(HttpServletRequest request, @RequestHeader(name = "api-key") String apiKey,
                                      @RequestBody HotelChangePriceReq hotelChangePriceReq){

        long userId = authService.authKey(apiKey, ApiStatic.API_HOTEL, ApiStatic.API_HOTEL_CHANGE_PRICE, hotelChangePriceReq.toString(), request.getRemoteAddr());

        if( userId == 0L){
            return new ResponseEntity(authService.respAuthFailed(),HttpStatus.UNAUTHORIZED);
        }

        HotelChangePriceResponse response = hotelChangePriceService.changePrice(userId, hotelChangePriceReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}


