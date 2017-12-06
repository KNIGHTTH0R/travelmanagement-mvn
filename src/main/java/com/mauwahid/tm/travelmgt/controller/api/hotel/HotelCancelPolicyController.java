package com.mauwahid.tm.travelmgt.controller.api.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelCancelPolicyReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelCancelPolicyResponse;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.integrator.HotelCancelPolicyService;
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
@Api(tags = "Hotel Cancel Policy Request",  description="Operations for cancel policy information")
public class HotelCancelPolicyController {


    @Autowired
    HotelCancelPolicyService hotelCancelPolicyService;

    @Autowired
    private AuthService authService;


    @ApiOperation(value = "Request Cancel Policy", response = HotelCancelPolicyResponse.class)
    @PostMapping("/cancel_policy")
    public ResponseEntity cancelPolicy(HttpServletRequest request, @RequestHeader(name = "api-key") String apiKey,
                                       @RequestBody HotelCancelPolicyReq hotelCancelPolicyReq){


        long userId = authService.authKey(apiKey, ApiStatic.API_HOTEL, ApiStatic.API_HOTEL_DETAIL, hotelCancelPolicyReq.toString(), request.getRemoteAddr());

        if( userId == 0L){
            return new ResponseEntity(authService.respAuthFailed(),HttpStatus.UNAUTHORIZED);
        }

        HotelCancelPolicyResponse response = hotelCancelPolicyService.getPolicyResponse(hotelCancelPolicyReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
