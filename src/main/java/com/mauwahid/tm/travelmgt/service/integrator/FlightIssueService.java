package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightIssueReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightIssueResponse;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightIssue;
import com.mauwahid.tm.travelmgt.entity.log.LogFlightIssue;
import com.mauwahid.tm.travelmgt.repository.api.antavaya.AntavayaFlightIssue;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightIssueInterface;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.OpsigoFlightIssue;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightIssue;
import com.mauwahid.tm.travelmgt.repository.database.log.LogFlightIssueRepository;
import com.mauwahid.tm.travelmgt.utils.Common;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class FlightIssueService {

    @Autowired
    private LogFlightIssueRepository logFlightIssueRepository;

    private FlightIssueInterface flightIssueInterface;
    
    @Autowired
    private ApplicationContext context;


    public FlightIssueResponse issueFlight(long userId, FlightIssueReq flightIssueReq){

        FlightIssue flightIssue = issue(flightIssueReq);

        FlightIssueResponse response = translateResponse(flightIssue);

        saveToLog(userId,flightIssueReq, response);

        return response;

    }

    private FlightIssue issue(FlightIssueReq flightIssueReq){

        FlightIssue flightIssue = new FlightIssue();
        flightIssue.setStatusCode(StatusCode.NOT_IMPLEMENTED);
        flightIssue.setStatusDesc(StatusCode.S_NOT_IMPLEMENTED);


        if(flightIssueReq.getApiSource().equalsIgnoreCase(Common.API_OPSIGO)){
            Map param = OpsigoFlightIssue.translateToParam(flightIssueReq);

            flightIssueInterface = context.getBean(OpsigoFlightIssue.class);
            flightIssue = flightIssueInterface.issueFlight(param);
        }

        if(flightIssueReq.getApiSource().equalsIgnoreCase(Common.API_POINTER)){
            Map param = PointerFlightIssue.translateToParam(flightIssueReq);

            flightIssueInterface = context.getBean(PointerFlightIssue.class);
            flightIssue = flightIssueInterface.issueFlight(param);

        }

        if(flightIssueReq.getApiSource().equalsIgnoreCase(Common.API_ANTAVAYA)){
            Map param = AntavayaFlightIssue.translateToParam(flightIssueReq);

            flightIssueInterface = context.getBean(AntavayaFlightIssue.class);
            flightIssue = flightIssueInterface.issueFlight(param);

        }


        //api pointer

        log.debug("fligtIssue "+flightIssue.getFullName());
        return flightIssue;

    }



    private FlightIssueResponse translateResponse(FlightIssue flightIssue){
        FlightIssueResponse flightIssueResponse = new FlightIssueResponse();
        flightIssueResponse.setSessionKey(Common.generateSessionID());

        if(flightIssue.getStatusCode() == StatusCode.SUCCESS){
            flightIssueResponse.setStatus(StatusCode.SUCCESS);
            flightIssueResponse.setMessage(StatusCode.S_SUCCESS);

        }else {

            flightIssueResponse.setStatus(flightIssue.getStatusCode());
            flightIssueResponse.setMessage(flightIssue.getStatusDesc());

        }

        if(flightIssue!=null){
            flightIssueResponse.setFlightIssue(flightIssue);


            if(flightIssue.getStatusCode()==StatusCode.ERROR_API){
                flightIssueResponse.setStatus(StatusCode.NO_CONTENT);
                flightIssueResponse.setMessage(StatusCode.S_NO_CONTENT + ": "+flightIssue.getStatusDesc());
            }

            if(flightIssue.getStatusCode() == StatusCode.SERVER_ERROR){
                flightIssueResponse.setStatus(StatusCode.SERVER_ERROR);
                flightIssueResponse.setMessage(StatusCode.S_SERVER_ERROR + ": "+flightIssue.getStatusDesc());

            }
        }

        return flightIssueResponse;
    }

    private void saveToLog(long userId,FlightIssueReq flightIssueReq, FlightIssueResponse flightIssueResponse){

        //todo : log_save -> user_id, api_date, statusCode, message, jsonOf HotelSearchResponse

        String jsonData = Common.generateJSONFromObject(flightIssueResponse);

        LogFlightIssue logData = new LogFlightIssue();
        logData.setUserId(userId);
   //     logData.setJsonData(LogErrorHelper.convertStringToBlob(jsonData));
        logData.setJsonData(jsonData);
        logData.setMessage(flightIssueResponse.getMessage());
        logData.setStatusCode(flightIssueResponse.getStatus());
        logData.setApiSessionKey(flightIssueResponse.getSessionKey());
        logData.setCallBackUri(flightIssueReq.getCallbackUri());
        logData.setPnrId(flightIssueReq.getBookingCode());


        logFlightIssueRepository.save(logData);
    }
}
