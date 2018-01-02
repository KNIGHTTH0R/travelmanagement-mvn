package com.mauwahid.tm.travelmgt.repository.api.opsigo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightIssue;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightIssueReq;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightReservationStatusReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightReservationStatusResponse;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightIssueInterface;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.json.FlightIssueOpsReq;
import com.mauwahid.tm.travelmgt.utils.Common;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class OpsigoFlightIssue implements FlightIssueInterface {

    private String url;

    private OpsigoApiCaller opsigoApiCaller;

    public FlightIssue issueFlight(Map params) {

        url = OpsigoApiCaller.uri;
        url = url+"/apiv3/IssueRsvFlight";

        FlightIssueOpsReq flightIssueOpsReq = translateToParam(params);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonParam = "";


        boolean reservedStatus = isReserved(flightIssueOpsReq.getPnrId());

        if(!reservedStatus){
            FlightIssue flightIssue = new FlightIssue();
            flightIssue.setStatusCode(StatusCode.NOT_IMPLEMENTED);
            flightIssue.setStatusDesc("PNR Not yet reserved");

            return flightIssue;
        }
        /*else{
            FlightIssue flightIssue = new FlightIssue();
            flightIssue.setStatusCode(StatusCode.SUCCESS);
            flightIssue.setStatusDesc(StatusCode.S_SUCCESS);

            return flightIssue;

        }*/


        try{
            jsonParam = objectMapper.writeValueAsString(flightIssueOpsReq);
        }catch (Exception ex){
            jsonParam = "";
            log.error("ex "+ex.toString());
            return exceptionHandling(ex, params.toString(), params);
        }

        Map responseMap = new HashMap();

        try{
            opsigoApiCaller = new OpsigoApiCaller();

            log.debug("JSON Req "+jsonParam);
            responseMap = opsigoApiCaller.callApiPostJsonMap(url,jsonParam);
            log.debug("JSON RES : "+responseMap.toString());
        }catch (IOException ex){
            log.error("searchTravel : "+ex.toString());
            return exceptionHandling(ex, params.toString(), responseMap);
        }

        try{
            return translateToObject(responseMap);
        }catch (Exception ex) {
            log.error("flightIssue translateToObj : "+ex.toString());

            return exceptionHandling(ex, params.toString(), responseMap);
        }

    }

    private FlightIssue translateToObject(Map map) throws JSONException {

        FlightIssue flightIssue = new FlightIssue();

        flightIssue.setStatusCode(StatusCode.NOT_IMPLEMENTED);
        flightIssue.setStatusDesc(StatusCode.S_NOT_IMPLEMENTED);

        if(map.get("status").toString().equalsIgnoreCase(StatusCode.SUCCESS+"")){
            flightIssue.setStatusCode(StatusCode.SUCCESS);
            flightIssue.setStatusDesc(StatusCode.S_SUCCESS);
          //  flightIssue.setBookingCode(map.get("pnrId").toString());
        }else{
            flightIssue.setStatusCode(StatusCode.ERROR_API);
            flightIssue.setStatusDesc(StatusCode.S_ERROR_API);
        }

        return flightIssue;
    }


    private FlightIssue exceptionHandling(Exception ex, String params, Map response){


        FlightIssue flightIssue = new FlightIssue();
        flightIssue.setStatusCode(StatusCode.SERVER_ERROR);
        flightIssue.setStatusDesc(StatusCode.S_SERVER_ERROR);

        return flightIssue;
    }


    //static method
    public static FlightIssueOpsReq translateToParam(Map map){

        FlightIssueOpsReq flightIssueOpsReq = new FlightIssueOpsReq();
      //  flightIssueOpsReq.setCallbackUri(Common.CALLBACK_ISSUE);
        flightIssueOpsReq.setCallbackUri(map.get("callbackUri").toString());
        flightIssueOpsReq.setPnrId(map.get("pnrId").toString());

        return flightIssueOpsReq;
    }


    public static Map translateToParam(FlightIssueReq flightIssueReq){
        Map param = new HashMap();

        param.put("pnrId",flightIssueReq.getBookingCode());
        param.put("callbackUri", flightIssueReq.getCallbackUri());

        return param;
    }

    public boolean isReserved(String pnrId){
        FlightReservationStatusReq flightReservationStatusReq = new FlightReservationStatusReq();
        flightReservationStatusReq.setBookingCode(pnrId);

        try{
            Map param = OpsigoFlightReservationStatus.translateToParam(flightReservationStatusReq);

            OpsigoFlightReservationStatus opsigoFlightReservationStatus = new OpsigoFlightReservationStatus();
            FlightReservationStatusResponse flightReservationStatusResponse = opsigoFlightReservationStatus.cekStatus(param);

            if(flightReservationStatusResponse.getStatusReservation().trim().equalsIgnoreCase("reserved")){
                return true;
            }

        }catch (NullPointerException ex){
            log.error("error "+ex.toString());
            return false;
        }


        return false;

    }

}
