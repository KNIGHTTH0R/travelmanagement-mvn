package com.mauwahid.tm.travelmgt.controller.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelChangePriceReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelChangePriceResponse;
import com.mauwahid.tm.travelmgt.service.integrator.HotelChangePriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
@Api(tags = "Hotel Change Price",  description="")
public class HotelChangePriceController {

    @Autowired
    private HotelChangePriceService hotelChangePriceService;


    @ApiOperation(value = "Change Price", response = HotelChangePriceResponse.class)
    @PostMapping("/change_price")
    public ResponseEntity changePrice(@RequestHeader(name = "api-key") String apiKey,
                                    @RequestBody HotelChangePriceReq hotelChangePriceReq){

        HotelChangePriceResponse response = hotelChangePriceService.changePrice(hotelChangePriceReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}


