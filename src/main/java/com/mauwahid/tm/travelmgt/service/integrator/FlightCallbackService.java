package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightCallBackReq;
import com.mauwahid.tm.travelmgt.domain.api.response.DefaultResponse;
import com.mauwahid.tm.travelmgt.entity.log.LogFlightBook;
import com.mauwahid.tm.travelmgt.repository.api.resthub.PostStdRequester;
import com.mauwahid.tm.travelmgt.repository.database.log.LogFlightBookRepository;
import com.mauwahid.tm.travelmgt.repository.database.log.LogFlightIssueRepository;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class FlightCallbackService {


    @Autowired
    private LogFlightBookRepository logFlightBookRepository;

    @Autowired
    private LogFlightIssueRepository logFlightIssueRepository;


    public DefaultResponse callbackBook(FlightCallBackReq flightCallBackReq){
        LogFlightBook logFlightBook = logFlightBookRepository.findByPnrId(flightCallBackReq.getPnrId());

        logFlightBook.setJobType(flightCallBackReq.getJobType());
        logFlightBook.setProgress(flightCallBackReq.getProgress());
        logFlightBook.setText(flightCallBackReq.getText());

        logFlightBookRepository.save(logFlightBook);

        int statusCallBack = sendCallback(logFlightBook.getCallBackUri(), flightCallBackReq);

        logFlightBook.setCallbackStatus(statusCallBack);
        logFlightBookRepository.save(logFlightBook);

        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setStatus(StatusCode.SUCCESS);

        return defaultResponse;


    }


    public int sendCallback(String callbackUri, FlightCallBackReq flightCallBackReq){

        int status = 0;

        Map params = new HashMap();
        params.put("pnr_id", flightCallBackReq.getPnrId());
        params.put("progress",flightCallBackReq.getProgress());
        params.put("text", flightCallBackReq.getText());
        params.put("job_type",flightCallBackReq.getJobType());

        PostStdRequester postStdRequester = new PostStdRequester();

        Map result = new HashMap();
        try{
           result = postStdRequester.sendRequestMap(callbackUri,params);

           String statusCode = result.get("status").toString();

           if(statusCode.equalsIgnoreCase("200")){
               status = 1;
           }else {
               status = 2;
           }

        }catch (Exception ex){
            log.debug("Ex "+ex.toString());
            status = 3;
        }


        return status;
    }





}
