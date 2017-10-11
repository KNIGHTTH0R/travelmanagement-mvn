package com.mauwahid.tm.travelmgt.controller.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelBookReq;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelBookResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelSearchResponse;
import com.mauwahid.tm.travelmgt.service.integrator.HotelBookService;
import com.mauwahid.tm.travelmgt.service.integrator.HotelSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelBookController {


    @Autowired
    private HotelBookService hotelBookService;


    @PostMapping("/book")
    public ResponseEntity bookHotel(@RequestHeader(name = "api-key") String apiKey,
                                       @RequestBody HotelBookReq hotelBookReq){

        HotelBookResponse response = hotelBookService.bookHotel(hotelBookReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}