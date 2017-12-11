package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelIssue;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelIssueResult;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelIssueReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelIssueResponse;
import com.mauwahid.tm.travelmgt.entity.log.LogHotelIssue;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelIssue;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.HotelIssueInterface;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelIssue;
import com.mauwahid.tm.travelmgt.repository.database.log.LogHotelIssueRepository;
import com.mauwahid.tm.travelmgt.utils.Common;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HotelIssueService {


    private HotelIssueInterface hotelIssueInterface;


    @Autowired
    private LogHotelIssueRepository logHotelIssueRepository;


    public HotelIssueResponse issueHotel(long userId, HotelIssueReq hotelIssueReq){

        HotelIssueResponse response = new HotelIssueResponse();
        response.setStatus(StatusCode.NOT_IMPLEMENTED);
        response.setMessage(StatusCode.S_NOT_IMPLEMENTED);

        if(hotelIssueReq.getApiSource().equalsIgnoreCase(Common.API_ASTRINDO)){
            HotelIssueResult hotelIssue = issueAstri(hotelIssueReq);

            response = translateResponse(response, hotelIssue);

        }

        saveToLog(userId, response);

        return response;

    }

    private HotelIssue issueTrevo(HotelIssueReq hotelIssueReq){

        Map param = TrevoHotelIssue.translateToParam(hotelIssueReq);

       // HotelIssue hotelIssue = trevoHotelIssue.issue(param);
      //  return hotelIssue;
        return null;

    }

    private HotelIssueResult issueAstri(HotelIssueReq hotelIssueReq){

        Map param = AstriHotelIssue.translateToParam(hotelIssueReq);

        hotelIssueInterface = new AstriHotelIssue();

        HotelIssueResult hotelIssueResult = hotelIssueInterface.issueHotel(param);
        return hotelIssueResult;

    }


    private HotelIssueResponse translateResponse(HotelIssueResponse response, HotelIssueResult hotelIssue){
        response.setStatus(StatusCode.SUCCESS);
        response.setMessage(StatusCode.S_SUCCESS);
        response.setHotelIssue(hotelIssue);
        return response;
    }

    private void saveToLog(long userId, HotelIssueResponse hotelIssueResponse){


        //todo : log_save -> user_id, api_date, statusCode, message, jsonOf HotelSearchResponse

        String jsonData = Common.generateJSONFromObject(hotelIssueResponse);

        LogHotelIssue logData = new LogHotelIssue();
        logData.setUserId(userId);
        logData.setJsonData(LogErrorHelper.convertStringToBlob(jsonData));
        logData.setMessage(hotelIssueResponse.getMessage());
        logData.setStatusCode(hotelIssueResponse.getStatus());
        //logData.setApiSessionKey(hotelSearchResponse.getSessionKey());

        logHotelIssueRepository.save(logData);
    }


}
