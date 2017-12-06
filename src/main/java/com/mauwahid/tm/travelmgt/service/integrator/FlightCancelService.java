package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightCancel;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightCancelReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightCancelResponse;
import com.mauwahid.tm.travelmgt.entity.log.LogFlightCancel;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightCancelInterface;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.OpsigoFlightCancel;
import com.mauwahid.tm.travelmgt.repository.database.log.LogFlightCancelRepository;
import com.mauwahid.tm.travelmgt.utils.Common;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FlightCancelService {

    @Autowired
    private LogFlightCancelRepository logFlightCancelRepository;

    private FlightCancelInterface flightCancelInterface;


    Logger logger = LoggerFactory.getLogger(FlightIssueService.class);

    public FlightCancelResponse cancelFlight(long userId, FlightCancelReq flightCancelReq){

        FlightCancel flightCancel = cancel(flightCancelReq);

        FlightCancelResponse response = translateResponse(flightCancel);

        saveToLog(userId,flightCancelReq, response);

        return response;

    }

    private FlightCancel cancel(FlightCancelReq flightCancelReq){

        FlightCancel flightCancel = new FlightCancel();
        flightCancel.setStatusCode(StatusCode.NOT_IMPLEMENTED);
        flightCancel.setStatusDesc(StatusCode.S_NOT_IMPLEMENTED);


        if(flightCancelReq.getApiSource().equalsIgnoreCase(Common.API_OPSIGO)){
           // Map param = PointerFlightIssue.translateToParam(fl);

          //  flightIssueInterface = new PointerFlightIssue();
          //  flightIssue = flightIssueInterface.issueFlight(param);
        }

        if(flightCancelReq.getApiSource().equalsIgnoreCase(Common.API_POINTER)){
            Map param = OpsigoFlightCancel.translateToParam(flightCancelReq);

            flightCancelInterface = new OpsigoFlightCancel();
            flightCancel = flightCancelInterface.cancelFlight(param);

        }


        //api pointer

        return flightCancel;

    }



    private FlightCancelResponse translateResponse(FlightCancel flightCancel){
        FlightCancelResponse flightCancelResponse = new FlightCancelResponse();

        if(flightCancel.getStatusCode() == StatusCode.NOT_IMPLEMENTED){
            flightCancelResponse.setStatus(StatusCode.NOT_IMPLEMENTED);
            flightCancelResponse.setMessage(StatusCode.S_NOT_IMPLEMENTED);

        }else {
            flightCancelResponse.setStatus(StatusCode.SUCCESS);
            flightCancelResponse.setMessage(StatusCode.S_SUCCESS);

        }


        flightCancelResponse.setFlightCancel(flightCancel);

        return flightCancelResponse;
    }

    private void saveToLog(long userId,FlightCancelReq flightCancelReq, FlightCancelResponse flightCancelResponse){

        //todo : log_save -> user_id, api_date, statusCode, message, jsonOf HotelSearchResponse

        String jsonData = Common.generateJSONFromObject(flightCancelResponse);

        LogFlightCancel logData = new LogFlightCancel();
        logData.setUserId(userId);
        logData.setJsonData(LogErrorHelper.convertStringToBlob(jsonData));
        logData.setMessage(flightCancelResponse.getMessage());
        logData.setStatusCode(flightCancelResponse.getStatus());
        logData.setApiSessionKey(flightCancelReq.getSessionKey());
        logData.setCallBackUri(flightCancelReq.getCallbackUri());
        logData.setPnrId(flightCancelReq.getBookingCode());


        logFlightCancelRepository.save(logData);
    }
}
