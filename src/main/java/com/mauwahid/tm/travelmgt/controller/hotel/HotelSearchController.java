package com.mauwahid.tm.travelmgt.controller.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightSearchResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelSearchResponse;
import com.mauwahid.tm.travelmgt.service.integrator.FlightSearchService;
import com.mauwahid.tm.travelmgt.service.integrator.HotelSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelSearchController {


    @Autowired
    private HotelSearchService hotelSearchService;


    @PostMapping("/search")
    public ResponseEntity search(@RequestHeader(name = "api-key") String apiKey,
                                       @RequestBody HotelSearchReq hotelSearchReq){

        HotelSearchResponse response = hotelSearchService.searchHotel(hotelSearchReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
