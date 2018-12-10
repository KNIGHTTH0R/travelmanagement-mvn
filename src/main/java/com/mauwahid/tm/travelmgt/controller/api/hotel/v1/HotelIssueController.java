package com.mauwahid.tm.travelmgt.controller.api.hotel.v1;

import com.mauwahid.tm.travelmgt.domain.api.request.hotel.v1.HotelIssueReq;
import com.mauwahid.tm.travelmgt.domain.api.response.hotel.v1.HotelIssueResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.hotel.v1.HotelIssueService;
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
@Api(tags = "Hotel Issue",  description="Operations for Hotel's issuing")
public class HotelIssueController {


    @Autowired
    private HotelIssueService hotelIssueService;


    @Autowired
    private AuthService authService;

    @ApiOperation(value = "Hotel Issue", response = HotelIssueResponse.class)
    @PostMapping("/issue")
    public ResponseEntity issue(HttpServletRequest request,
                                @RequestHeader(name = "api-key") String apiKey,
                                @RequestBody HotelIssueReq hotelIssueReq){

        long userId = authService.authKey(apiKey, ApiStatic.API_HOTEL, ApiStatic.API_HOTEL_ISSUE, hotelIssueReq.toString(), request.getRemoteAddr());

        if( userId == 0L){
            return new ResponseEntity(authService.respAuthFailed(),HttpStatus.UNAUTHORIZED);
        }


        HotelIssueResponse response = hotelIssueService.issueHotel(userId, hotelIssueReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}