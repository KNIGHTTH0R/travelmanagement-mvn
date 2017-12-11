package com.mauwahid.tm.travelmgt.repository.api.pointer;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.*;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightBookReq2;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightCancelReq;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightCancelInterface;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
public class PointerFlightCancel implements FlightCancelInterface {

    private String url;


    @Autowired
    private PointerApiCaller pointerApiCaller;


    public FlightCancel cancelFlight(FlightCancelReq flightCancelReq){

        Map params = translateToParam(flightCancelReq);

        url = PointerApiCaller.uri;
        url = url+params.get("airline")+"/cancel";

        String jsonData;

     //   log.debug("params : "+params);

        try{
            jsonData = pointerApiCaller.callApiPost(url,params);
            log.debug("JSON RES : "+jsonData);
        }catch (IOException ex){
            log.error("searchTravel : "+ex.toString());
            return exceptionHandling(ex);
        }

        try{
            return translateToObject(jsonData);
        }catch (Exception ex) {
            log.error("searchTravel translateToObj : "+ex.toString());

            return exceptionHandling(ex);
        }
    }


    private FlightCancel exceptionHandling(Exception ex){
        FlightCancel flightCancel = new FlightCancel();
        flightCancel.setStatusCode(StatusCode.SERVER_ERROR);
        flightCancel.setStatusDesc(StatusCode.S_SERVER_ERROR);

        return flightCancel;
    };

    //Translator From JSON

    private FlightCancel translateToObject(String jsonData) throws JSONException {
        FlightCancel flightCancel = new FlightCancel();

        flightCancel.setStatusCode(StatusCode.NOT_IMPLEMENTED);
        flightCancel.setStatusDesc(StatusCode.S_NOT_IMPLEMENTED);

        JSONObject jsonObject = new JSONObject(jsonData);
        String status = jsonObject.optString("code");

        if(status.equalsIgnoreCase("200")){
            flightCancel.setStatusCode(StatusCode.SUCCESS);
            flightCancel.setStatusDesc(StatusCode.S_SUCCESS);
        }else if(status.equalsIgnoreCase("204")){
            flightCancel.setStatusCode(StatusCode.NO_CONTENT);
            flightCancel.setStatusDesc(StatusCode.S_NO_CONTENT+"/ Booking tidak tersedia");

        }else if(status.equalsIgnoreCase("400")){
            flightCancel.setStatusCode(StatusCode.BAD_REQUEST);
            flightCancel.setStatusDesc("Booking Expired");

        }else{
            flightCancel.setStatusCode(StatusCode.ERROR_API);
            flightCancel.setStatusDesc(StatusCode.S_ERROR_API);

        }

        return flightCancel;
    }

    public static Map translateToParam(FlightCancelReq flightCancelReq){
        Map param = new HashMap();

        param.put("booking_code",flightCancelReq.getBookingCode());
       // param.put("callbackUri", flightCancelReq.getAirline());

        return param;
    }
}
