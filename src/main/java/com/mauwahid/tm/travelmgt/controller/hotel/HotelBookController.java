package com.mauwahid.tm.travelmgt.controller.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelBookReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelBookResponse;
import com.mauwahid.tm.travelmgt.service.integrator.HotelBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
@Api(tags = "Hotel Book",  description="Operations for Hotel's booking")
public class HotelBookController {


    @Autowired
    private HotelBookService hotelBookService;


    @ApiOperation(value = "Hotel Book", response = HotelBookResponse.class)
    @PostMapping("/book")
    public ResponseEntity bookHotel(@RequestHeader(name = "api-key") String apiKey,
                                       @RequestBody HotelBookReq hotelBookReq){

        HotelBookResponse response = hotelBookService.bookHotel(hotelBookReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}