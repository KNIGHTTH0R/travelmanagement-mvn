package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelIssueReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelIssueResponse;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelIssue;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelIssueResult;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelIssue;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HotelIssueService {


    @Autowired
    private TrevoHotelIssue trevoHotelIssue;

    @Autowired
    private AstriHotelIssue astriHotelIssue;


    public HotelIssueResponse issueHotel(HotelIssueReq hotelIssueReq){

        HotelIssueResponse response = new HotelIssueResponse();
        response.setStatusCode("2");
        response.setStatusDesc("Not Implemented");

        if(hotelIssueReq.getApiSource().equalsIgnoreCase("astrindo")){
            HotelIssueResult hotelIssue = issueAstri(hotelIssueReq);
            return translateResponse(hotelIssue);
        }

        return response;

    }

    private HotelIssue issueTrevo(HotelIssueReq hotelIssueReq){

        Map param = TrevoHotelIssue.translateToParam(hotelIssueReq);
        HotelIssue hotelIssue = trevoHotelIssue.issue(param);
        return hotelIssue;

    }

    private HotelIssueResult issueAstri(HotelIssueReq hotelIssueReq){

        Map param = AstriHotelIssue.translateToParam(hotelIssueReq);
        HotelIssueResult hotelIssueResult = astriHotelIssue.issueHotel(param);
        return hotelIssueResult;

    }


    private HotelIssueResponse translateResponse(HotelIssueResult hotelIssue){
        HotelIssueResponse response = new HotelIssueResponse();
        response.setHotelIssue(hotelIssue);
        return response;
    }

    public static class HotelAvailabilityService {
    }
}
