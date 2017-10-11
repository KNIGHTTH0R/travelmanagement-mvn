package com.mauwahid.tm.travelmgt.controller.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelBookReq;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelIssueReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelBookResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelIssueResponse;
import com.mauwahid.tm.travelmgt.service.integrator.HotelBookService;
import com.mauwahid.tm.travelmgt.service.integrator.HotelIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelIssueController {


    @Autowired
    private HotelIssueService hotelIssueService;


    @PostMapping("/issue")
    public ResponseEntity issue(@RequestHeader(name = "api-key") String apiKey,
                                    @RequestBody HotelIssueReq hotelIssueReq){

        HotelIssueResponse response = hotelIssueService.issueHotel(hotelIssueReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}