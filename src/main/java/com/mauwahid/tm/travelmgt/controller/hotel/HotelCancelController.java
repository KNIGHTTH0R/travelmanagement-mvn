package com.mauwahid.tm.travelmgt.controller.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelCancelReq;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelChangePriceReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelCancelResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelChangePriceResponse;
import com.mauwahid.tm.travelmgt.service.integrator.HotelCancelService;
import com.mauwahid.tm.travelmgt.service.integrator.HotelChangePriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hotel")
@Api(tags = "Hotel Cancel")
public class HotelCancelController {

    @Autowired
    private HotelCancelService hotelCancelService;


    @ApiOperation(value = "Hotel Cancel", response = HotelCancelResponse.class)
    @PostMapping("/cancel")
    public ResponseEntity cancel(@RequestHeader(name = "api-key") String apiKey,
                                      @RequestBody HotelCancelReq hotelCancelReq){

        HotelCancelResponse response = hotelCancelService.cancelHotel(hotelCancelReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
