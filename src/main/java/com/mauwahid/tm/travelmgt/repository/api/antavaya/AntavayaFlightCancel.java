package com.mauwahid.tm.travelmgt.repository.api.antavaya;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightCancel;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightCancelReq;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.json.FlightCancelOpsReq;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightCancelInterface;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class AntavayaFlightCancel implements FlightCancelInterface{

    private String url;

    private AntavayaApiCaller opsigoApiCaller;

    public FlightCancel cancelFlight(FlightCancelReq flightCancelReq) {

        url = AntavayaApiCaller.uri;
        url = url+"/apiv3/CancelRsvFlight";

        FlightCancelOpsReq flightCancelOpsReq = translateToObjJSON(flightCancelReq);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonParam = "";

        try{
            jsonParam = objectMapper.writeValueAsString(flightCancelOpsReq);
        }catch (Exception ex){
            jsonParam = "";
            log.error("ex "+ex.toString());
            return exceptionHandling(ex, flightCancelReq.toString(), jsonParam);
        }

        Map responseMap = new HashMap();

        try{
            opsigoApiCaller = new AntavayaApiCaller();

            log.debug("JSON Req "+jsonParam);
            responseMap = opsigoApiCaller.callApiPostJsonMap(url,jsonParam);
            log.debug("JSON RES : "+responseMap.toString());
        }catch (IOException ex){
            log.error("searchTravel : "+ex.toString());
            return exceptionHandling(ex, flightCancelReq.toString(), jsonParam);
        }

        try{
            return translateToObject(responseMap);
        }catch (Exception ex) {
            log.error("searchTravel translateToObj : "+ex.toString());

            return exceptionHandling(ex, flightCancelReq.toString(), jsonParam);
        }
    }


    private FlightCancel translateToObject(Map map) throws JSONException {

        FlightCancel flightCancel = new FlightCancel();

        flightCancel.setStatusCode(StatusCode.NOT_IMPLEMENTED);
        flightCancel.setStatusDesc(StatusCode.S_NOT_IMPLEMENTED);

        if(map.get("status").toString().equalsIgnoreCase(StatusCode.SUCCESS+"")){
            flightCancel.setStatusCode(StatusCode.SUCCESS);
            flightCancel.setStatusDesc(StatusCode.S_SUCCESS);
          //  flightCancel.setBookingCode(map.get("pnrId").toString());
        }else{
            flightCancel.setStatusCode(StatusCode.ERROR_API);
            flightCancel.setStatusDesc(StatusCode.S_ERROR_API);
        }

        return flightCancel;
    }


    private FlightCancel exceptionHandling(Exception ex, String params, String response){


        FlightCancel flightCancel = new FlightCancel();
        flightCancel.setStatusCode(StatusCode.SERVER_ERROR);
        flightCancel.setStatusDesc(StatusCode.S_SERVER_ERROR);

        return flightCancel;
    }


    //static method
    public static FlightCancelOpsReq translateToObjJSON(FlightCancelReq flightCancelReq){


        FlightCancelOpsReq flightCancelOpsReq = new FlightCancelOpsReq();
       // flightCancelOpsReq.setCallbackUri(Common.CALLBACK_ISSUE);
      //  flightCancelOpsReq.setPnrId(map.get("pnrId").toString());

        flightCancelOpsReq.setCallbackUri(flightCancelReq.getCallbackUri());
        flightCancelOpsReq.setPnrId(flightCancelReq.getBookingCode());

        return flightCancelOpsReq;
    }


    public static Map translateToMap(FlightCancelReq flightCancelReq){
        Map param = new HashMap();

        param.put("pnrId",flightCancelReq.getBookingCode());
        param.put("callbackUri", flightCancelReq.getAirline());

        return param;
    }

}
