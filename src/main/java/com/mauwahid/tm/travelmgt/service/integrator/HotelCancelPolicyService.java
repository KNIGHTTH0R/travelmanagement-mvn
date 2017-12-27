package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelCancelPolicy;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelCancelPolicyReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelCancelPolicyResponse;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelCancelPolicy;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.HotelCancelPolicyInterface;
import com.mauwahid.tm.travelmgt.repository.database.LogHotelCancellationPolicyRepository;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class HotelCancelPolicyService implements HotelCancelPolicyInterface{

    @Autowired
    private AstriHotelCancelPolicy astriHotelCancelPolicy;

    @Autowired
    private LogHotelCancellationPolicyRepository logHotelCancellationPolicyRepository;


    public HotelCancelPolicyResponse getPolicyResponse(HotelCancelPolicyReq hotelCancelPolicyReq){

        HotelCancelPolicyResponse response = new HotelCancelPolicyResponse();
        response.setStatus(StatusCode.NOT_IMPLEMENTED);
        response.setMessage(StatusCode.S_NOT_IMPLEMENTED);

        response = translateResponse(getPolicy(hotelCancelPolicyReq));


        return response;

    }

    private HotelCancelPolicy getPolicy(HotelCancelPolicyReq hotelCancelPolicyReq){

        Map param = AstriHotelCancelPolicy.translateToParam(hotelCancelPolicyReq);

        astriHotelCancelPolicy = new AstriHotelCancelPolicy();

        if(astriHotelCancelPolicy == null){
            log.debug("Astri Hotel Cancel Policy is "+astriHotelCancelPolicy);
        }

        HotelCancelPolicy hotelCancelPolicy = astriHotelCancelPolicy.cancelPolicy(param);

        return hotelCancelPolicy;

    }



    private HotelCancelPolicyResponse translateResponse(HotelCancelPolicy hotelCancelPolicy){
        HotelCancelPolicyResponse response = new HotelCancelPolicyResponse();

        if(hotelCancelPolicy!=null){
            response.setStatus(StatusCode.SUCCESS);
            response.setMessage(StatusCode.S_SUCCESS);
        }

        response.setHotelCancelPolicy(hotelCancelPolicy);

        return response;
    }


}
