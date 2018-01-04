package com.mauwahid.tm.travelmgt.repository.api.pointer;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightReservationStatusReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightReservationStatusResponse;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightReservationStatusInterface;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.OpsigoApiCaller;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.json.FlightPayments;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.json.PassengerOps;
import com.mauwahid.tm.travelmgt.utils.ApiStatic;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class PointerFlightReservationStatus implements FlightReservationStatusInterface {

    private String url;

    private PointerApiCaller pointerApiCaller;

    // @Autowired
    private LogErrorHelper logErrorHelper;

    public FlightReservationStatusResponse cekStatus(Map params) {

        url = PointerApiCaller.uri;
        url = url+"manage/book";

        url = url+"/"+params.get("id").toString();

        String jsonData = "";

        try{
            pointerApiCaller = new PointerApiCaller();

            jsonData = pointerApiCaller.callApiGet(url,params);
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

    private FlightReservationStatusResponse translateToObject(String json) throws JSONException {

        FlightReservationStatusResponse flightReservationStatusResponse = new FlightReservationStatusResponse();


        flightReservationStatusResponse.setStatus(StatusCode.SUCCESS);
        flightReservationStatusResponse.setMessage(StatusCode.S_SUCCESS);

        JSONObject jsonObject = new JSONObject(json);

        String code = jsonObject.optString("code");

        if(!code.equalsIgnoreCase("200")){
            flightReservationStatusResponse.setStatus(StatusCode.ERROR_API);
            flightReservationStatusResponse.setMessage(StatusCode.S_ERROR_API);
        }

        JSONObject jsonResult = jsonObject.optJSONObject("results");

        flightReservationStatusResponse.setBookingCode(jsonResult.optString("booking_code"));
        flightReservationStatusResponse.setTimeLimit(jsonResult.optString("time_limit"));

        flightReservationStatusResponse.setStatusReservation(jsonResult.optString("payment_status"));


        //todo : payment

        FlightPayments flightPayments = null;
        Set<FlightPayments> flightPaymentsSet = new HashSet<>();

        flightPayments = new FlightPayments();
        flightPayments.setAmount(jsonResult.optString("total_price"));
        flightPaymentsSet.add(flightPayments);



        JSONArray jsonPassenger = jsonResult.getJSONArray("passenger_list");
        Set<PassengerOps> passengerOpsList = new HashSet<>();

        JSONObject objPassenger = null;

        PassengerOps passengerOps = null;

        for(int x=0;x<jsonPassenger.length();x++){
            objPassenger = jsonPassenger.getJSONObject(x);
            passengerOps = new PassengerOps();
            String name = objPassenger.optString("name");

            String[] names = name.split(" ");
            String firstName = "";
            String lastName = "";

            for(int i=0;i<names.length;i++){
                if(i==0){
                    firstName = names[i];
                }else if(i==1) {
                    lastName = names[i];
                }else{
                    lastName = lastName + " " + names[i];
                }

            }

            passengerOps.setFirstName(firstName);
            passengerOps.setLastName(lastName);
            passengerOps.setBirthDate(objPassenger.optString("birth_date"));
            passengerOps.setTicketNumber(objPassenger.optString("ticket_no"));

            passengerOpsList.add(passengerOps);

        }

        flightReservationStatusResponse.setFlightPayments(flightPaymentsSet);
        flightReservationStatusResponse.setPassengerOps(passengerOpsList);

        return flightReservationStatusResponse;


    }


    //static method
    public static Map translateToParam(FlightReservationStatusReq flightReservationStatusReq){

        Map map = new HashMap();
        map.put("id", flightReservationStatusReq.getBookingCode());

        return map;

    }

}
