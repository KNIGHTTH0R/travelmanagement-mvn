package com.mauwahid.tm.travelmgt.repository.api.opsigo;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightReservationStatusReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightReservationStatusResponse;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightReservationStatusInterface;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.json.FlightPayments;
import com.mauwahid.tm.travelmgt.utils.ApiStatic;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
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

@Slf4j
@Component
public class OpsigoFlightReservationStatus implements FlightReservationStatusInterface {


    private String url;

    private OpsigoApiCaller opsigoApiCaller;

   // @Autowired
    private LogErrorHelper logErrorHelper;

    public FlightReservationStatusResponse cekStatus(Map params) {

        url = OpsigoApiCaller.uri;
        url = url+"/apiv3/GetRsvById";


        String jsonData = "";

        try{
            opsigoApiCaller = new OpsigoApiCaller();

            jsonData = opsigoApiCaller.callApiGet(url,params);
            log.debug("JSON RES : "+jsonData);
        }catch (IOException ex){
            log.error("searchTravel : "+ex.toString());
            return exceptionHandling(ex, params.toString(), jsonData);
        }

        try{
            return translateToObject(jsonData);
        }catch (Exception ex) {
            log.error("searchTravel translateToObj : "+ex.toString());

            return exceptionHandling(ex, params.toString(), jsonData);
        }
    }


    private FlightReservationStatusResponse exceptionHandling(Exception ex, String params, String jsonData){

        FlightReservationStatusResponse flightReservationStatusResponse = new FlightReservationStatusResponse();
        flightReservationStatusResponse.setStatus(StatusCode.ERROR_API);
        flightReservationStatusResponse.setMessage(StatusCode.S_ERROR_API + " : "+ex.toString());

        //todo : log
        logErrorHelper = new LogErrorHelper();
        logErrorHelper.saveErrorExc( ApiStatic.API_FLIGHT_TRANSACTION_STATUS, ex.toString(), params, jsonData);

        return flightReservationStatusResponse;
    };

    //Translator From JSON

    private FlightReservationStatusResponse translateToObject(String jsonData) throws JSONException {

        FlightReservationStatusResponse flightReservationStatusResponse = new FlightReservationStatusResponse();

        flightReservationStatusResponse.setStatus(StatusCode.SUCCESS);
        flightReservationStatusResponse.setMessage(StatusCode.S_SUCCESS);

        JSONObject jsonObject = new JSONObject(jsonData);
        flightReservationStatusResponse.setBookingCode(jsonObject.optString("BookingCode"));
        flightReservationStatusResponse.setTimeLimit(jsonObject.optString("TimeLimit"));


        int status = 0;

        try{
            status = Integer.parseInt(jsonObject.optString("Status"));
            flightReservationStatusResponse.setStatus(status);
        }catch (Exception ex){

            log.debug("Exc "+toString());
        }

        // flightReservationStatusResponse.setStatus(jsonObject.optString("Status"));


        JSONArray jsonArray = jsonObject.getJSONArray("Payments");

        FlightPayments flightPayments = null;
        Set<FlightPayments> flightPaymentsSet = new HashSet<>();

        JSONObject objPayment = null;

        for(int i=0;i<jsonArray.length();i++){

            objPayment = jsonArray.optJSONObject(i);


            flightPayments = new FlightPayments();
            flightPayments.setCode(objPayment.optString("code"));
            flightPayments.setAmount(objPayment.optString("Amount"));
            flightPayments.setTitle(objPayment.optString("Title"));
            flightPayments.setCurrency(objPayment.optString("Currency"));
            flightPayments.setForeignAmount(objPayment.optString("ForeignAmount"));
            flightPayments.setForeignCurrency(objPayment.optString("ForeignCurrency"));

            flightPaymentsSet.add(flightPayments);
        }

        flightReservationStatusResponse.setFlightPayments(flightPaymentsSet);

        return flightReservationStatusResponse;


    }


    //static method
    public static Map translateToParam(FlightReservationStatusReq flightReservationStatusReq){

        Map map = new HashMap();
        map.put("id", flightReservationStatusReq.getBookingCode());

        return map;

    }

}
