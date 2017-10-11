package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelBookReq;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelIssueReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelBookResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelIssueResponse;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelBookInfo;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelIssue;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelBook;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HotelIssueService {


    @Autowired
    private TrevoHotelIssue trevoHotelIssue;


    public HotelIssueResponse issueHotel(HotelIssueReq hotelIssueReq){

        HotelIssueResponse response = new HotelIssueResponse();
        response.setStatusCode("2");
        response.setStatusDesc("Not Implemented");

        if(hotelIssueReq.getApiSource().equalsIgnoreCase("trevohub")){
            HotelIssue hotelIssue = issueTrevo(hotelIssueReq);
            return translateResponse(hotelIssue);
        }

        return response;

    }

    private HotelIssue issueTrevo(HotelIssueReq hotelIssueReq){

        Map param = TrevoHotelIssue.translateToParam(hotelIssueReq);
        HotelIssue hotelIssue = trevoHotelIssue.issue(param);
        return hotelIssue;

    }


    private HotelIssueResponse translateResponse(HotelIssue hotelIssue){
        HotelIssueResponse response = new HotelIssueResponse();
        response.setHotelIssue(hotelIssue);
        return response;
    }
}
