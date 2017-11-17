package com.mauwahid.tm.travelmgt.controller.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelSearchResponse;
import com.mauwahid.tm.travelmgt.service.integrator.HotelSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
@Api(tags = "Hotel Search",  description="Operations for searching hotel")
public class HotelSearchController {


    @Autowired
    private HotelSearchService hotelSearchService;


    @ApiOperation(value = "Hotel Search", response = HotelSearchResponse.class)
    @PostMapping("/search")
    public ResponseEntity search(@RequestHeader(name = "api-key") String apiKey,
                                       @RequestBody HotelSearchReq hotelSearchReq){

        HotelSearchResponse response = hotelSearchService.searchHotel(hotelSearchReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
