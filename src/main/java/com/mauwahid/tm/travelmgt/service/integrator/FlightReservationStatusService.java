package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightIssue;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightReservationStatusReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightIssueResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightReservationStatusResponse;
import com.mauwahid.tm.travelmgt.entity.log.LogFlightReservationStatus;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightReservationStatusInterface;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.OpsigoFlightReservationStatus;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightBook;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightReservationStatus;
import com.mauwahid.tm.travelmgt.repository.database.log.LogFlightReservationStatusRepository;
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
public class FlightReservationStatusService {

    @Autowired
    private LogFlightReservationStatusRepository logFlightReservationStatusRepository;

    private FlightReservationStatusInterface flightReservationStatusInterface;

    @Autowired
    private ApplicationContext context;



    public FlightReservationStatusResponse reservationStatus(long userId, FlightReservationStatusReq flightReservationStatusReq){

        FlightReservationStatusResponse flightReservationStatusResponse = cekStatus(flightReservationStatusReq);


        saveToLog(userId, flightReservationStatusReq, flightReservationStatusResponse);

        return flightReservationStatusResponse;

    }

    private FlightReservationStatusResponse cekStatus(FlightReservationStatusReq flightReservationStatusReq){

        FlightReservationStatusResponse flightReservationStatusResponse = new FlightReservationStatusResponse();
        flightReservationStatusResponse.setStatus(StatusCode.NOT_IMPLEMENTED);
        flightReservationStatusResponse.setMessage(StatusCode.S_NOT_IMPLEMENTED);


        if(flightReservationStatusReq.getApiSource().equalsIgnoreCase(Common.API_OPSIGO)){

            Map param = OpsigoFlightReservationStatus.translateToParam(flightReservationStatusReq);

            flightReservationStatusInterface = context.getBean(OpsigoFlightReservationStatus.class);
            flightReservationStatusResponse = flightReservationStatusInterface.cekStatus(param);
        }
        if(flightReservationStatusReq.getApiSource().equalsIgnoreCase(Common.API_POINTER)){

            Map param = PointerFlightReservationStatus.translateToParam(flightReservationStatusReq);

            flightReservationStatusInterface = context.getBean(PointerFlightReservationStatus.class);
            flightReservationStatusResponse = flightReservationStatusInterface.cekStatus(param);
        }


         return flightReservationStatusResponse;

    }



    private FlightIssueResponse translateResponse(FlightIssue flightIssue){
        FlightIssueResponse flightIssueResponse = new FlightIssueResponse();
        flightIssueResponse.setSessionKey(Common.generateSessionID());

        if(flightIssue.getStatusCode() == StatusCode.NOT_IMPLEMENTED){
            flightIssueResponse.setStatus(StatusCode.NOT_IMPLEMENTED);
            flightIssueResponse.setMessage(StatusCode.S_NOT_IMPLEMENTED);

        }else {
            flightIssueResponse.setStatus(StatusCode.SUCCESS);
            flightIssueResponse.setMessage(StatusCode.S_SUCCESS);

        }


        flightIssueResponse.setFlightIssue(flightIssue);

        return flightIssueResponse;
    }

    private void saveToLog(long userId,FlightReservationStatusReq flightReservationStatusReq, FlightReservationStatusResponse flightReservationStatusResponse){

        //todo : log_save -> user_id, api_date, statusCode, message, jsonOf HotelSearchResponse

        String jsonData = Common.generateJSONFromObject(flightReservationStatusResponse);

        LogFlightReservationStatus logData = new LogFlightReservationStatus();
        logData.setUserId(userId);
      //  logData.setJsonData(LogErrorHelper.convertStringToBlob(jsonData));
        logData.setJsonData(jsonData);
        logData.setMessage(flightReservationStatusResponse.getMessage());


        logFlightReservationStatusRepository.save(logData);
    }
}
