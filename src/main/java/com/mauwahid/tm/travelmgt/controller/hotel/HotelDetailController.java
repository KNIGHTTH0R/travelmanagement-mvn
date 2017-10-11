package com.mauwahid.tm.travelmgt.controller.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelDetailReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelDetailResponse;
import com.mauwahid.tm.travelmgt.service.integrator.HotelDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelDetailController {


    @Autowired
    private HotelDetailService hotelDetailService;


    @PostMapping("/detail")
    public ResponseEntity search(@RequestHeader(name = "api-key") String apiKey,
                                 @RequestBody HotelDetailReq hotelDetailReq){

        HotelDetailResponse response = hotelDetailService.getDetailHotel(hotelDetailReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
