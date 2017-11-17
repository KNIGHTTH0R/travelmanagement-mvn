package com.mauwahid.tm.travelmgt.controller.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelDestinationReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelDestinationResponse;
import com.mauwahid.tm.travelmgt.service.integrator.HotelDestinationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
@Api(tags = "Hotel Destination")
public class HotelDestinationController {


    @Autowired
    private HotelDestinationService hotelDestinationService;


    @ApiOperation(value = "Hotel Destination", response = HotelDestinationResponse.class)
    @PostMapping("/destination")
    public ResponseEntity search(@RequestHeader(name = "api-key") String apiKey,
                                 @RequestBody HotelDestinationReq hotelDestinationReq){

        HotelDestinationResponse response = hotelDestinationService.getDestination(hotelDestinationReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
