package com.mauwahid.tm.travelmgt.repository.api.pointer;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightCancel;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightFlight;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightIssue;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightPassenger;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightIssueReq;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightIssueInterface;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class PointerFlightIssue implements FlightIssueInterface {

    private String url;

    @Autowired
    private PointerApiCaller pointerApiCaller;


    public FlightIssue issueFlight(Map params) {

        url = PointerApiCaller.uri;
        url = url+params.get("airline")+"/pay";

        String jsonData;


        try{
            jsonData = pointerApiCaller.callApiPost(url,params);
            log.debug("JSON RES : "+jsonData);
        }catch (IOException ex){
            log.error("searchTravel : "+ex.toString());
            return exceptionHandling(ex);
        }

        try{
            log.debug("try to run translate");
            return translateToObject(jsonData);
        }catch (Exception ex) {
            log.error(" translateToObj : "+ex.toString());

            return exceptionHandling(ex);
        }
    }


    private FlightIssue exceptionHandling(Exception ex){

        return null;
    };

    //Translator From JSON

    private FlightIssue translateToObject(String jsonData) throws JSONException {
        FlightIssue flightIss = new FlightIssue();

        flightIss.setStatusCode(StatusCode.NOT_IMPLEMENTED);
        flightIss.setStatusDesc(StatusCode.S_NOT_IMPLEMENTED);

        JSONObject jsonObject = new JSONObject(jsonData);
        String status = jsonObject.optString("code");

        if(status.equalsIgnoreCase("200")){
            flightIss.setStatusCode(StatusCode.SUCCESS);
            flightIss.setStatusDesc(StatusCode.S_SUCCESS);
        }else if(status.equalsIgnoreCase("204")){
            flightIss.setStatusCode(StatusCode.NO_CONTENT);
            flightIss.setStatusDesc(StatusCode.S_NO_CONTENT+"/ Booking tidak tersedia");

        }else if(status.equalsIgnoreCase("400")){
            flightIss.setStatusCode(StatusCode.BAD_REQUEST);
            flightIss.setStatusDesc("Booking Expired");

        }else{
            flightIss.setStatusCode(StatusCode.ERROR_API);
            flightIss.setStatusDesc(StatusCode.S_ERROR_API);

        }

        return flightIss;

    }


    //static method
    public static Map translateToParam(FlightIssueReq flightIssueReq){
        Map param = new HashMap();

        param.put("booking_code",flightIssueReq.getBookingCode());
        param.put("airline", flightIssueReq.getAirline());

        return param;
    }
}
