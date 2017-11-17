package com.mauwahid.tm.travelmgt.controller.hotel;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelCancelPolicyReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelCancelPolicyResponse;
import com.mauwahid.tm.travelmgt.service.integrator.HotelCancelPolicyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
@Api(tags = "Hotel Cancel Policy Request",  description="Operations for cancel policy information")
public class HotelCancelPolicyController {


    @Autowired
    HotelCancelPolicyService hotelCancelPolicyService;

    @ApiOperation(value = "Request Cancel Policy", response = HotelCancelPolicyResponse.class)
    @PostMapping("/cancel_policy")
    public ResponseEntity cancelPolicy(@RequestHeader(name = "api-key") String apiKey,
                                @RequestBody HotelCancelPolicyReq hotelCancelPolicyReq){

        HotelCancelPolicyResponse response = hotelCancelPolicyService.getPolicyResponse(hotelCancelPolicyReq);

        return  new ResponseEntity(response, HttpStatus.OK);
    }
}
