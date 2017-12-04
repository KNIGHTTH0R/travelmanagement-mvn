package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelCancelPolicyReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelCancelPolicyResponse;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelCancelPolicy;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelCancelPolicy;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.HotelCancelPolicyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HotelCancelPolicyService implements HotelCancelPolicyInterface{

    @Autowired
    private AstriHotelCancelPolicy astriHotelCancelPolicy;


    public HotelCancelPolicyResponse getPolicyResponse(HotelCancelPolicyReq hotelCancelPolicyReq){

        HotelCancelPolicyResponse response = new HotelCancelPolicyResponse();
        response.setStatus("2");
        response.setMessage("not implemented");

        response = translateResponse(getPolicy(hotelCancelPolicyReq));


        return response;

    }

    private HotelCancelPolicy getPolicy(HotelCancelPolicyReq hotelCancelPolicyReq){

        Map param = AstriHotelCancelPolicy.translateToParam(hotelCancelPolicyReq);


        HotelCancelPolicy hotelCancelPolicy = astriHotelCancelPolicy.cancelPolicy(param);

        return hotelCancelPolicy;

    }



    private HotelCancelPolicyResponse translateResponse(HotelCancelPolicy hotelCancelPolicy){
        HotelCancelPolicyResponse response = new HotelCancelPolicyResponse();

        response.setHotelCancelPolicy(hotelCancelPolicy);

        return response;
    }
}
