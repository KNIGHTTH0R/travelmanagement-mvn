package com.mauwahid.tm.travelmgt.repository.api.pointer;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightBookReq;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightBook;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightContact;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightFlight;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightPassenger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class PointerFlightBook {

    private Map params = new HashMap<String,String>();

    private String url;

    private Logger logger = LoggerFactory.getLogger(PointerFlightBook.class);

    @Autowired
    private PointerApiCaller pointerApiCaller;


    public FlightBook bookFlight(Map params) {

        url = PointerApiCaller.uri;
        url = url+params.get("airline")+"/book";

        String jsonData;

        logger.debug("params : "+params);

        try{
            jsonData = pointerApiCaller.callApiPost(url,params);
            logger.debug("JSON RES : "+jsonData);
        }catch (IOException ex){
            logger.error("searchTravel : "+ex.toString());
            return exceptionHandling(ex);
        }

        try{
            return translateToObject(jsonData);
        }catch (Exception ex) {
            logger.error("searchTravel translateToObj : "+ex.toString());

            return exceptionHandling(ex);
        }
    }


    private FlightBook exceptionHandling(Exception ex){

        return null;
    };

    //Translator From JSON

    private FlightBook translateToObject(String jsonData) throws JSONException {
        FlightBook flightBook = null;

        Set<FlightPassenger> passengers = null;
        FlightPassenger passenger = null;

        FlightContact flightContact = null;
        FlightFlight flight = null;
        Set<FlightFlight> flightList = null;

        JSONObject objData = new JSONObject(jsonData);
        JSONObject objResult = objData.optJSONObject("results");

        flightBook = new FlightBook();
        flightBook.setStatusCode(objData.optString("code"));

        if(!flightBook.getStatusCode().equalsIgnoreCase("200")){
            return flightBook;
        }

        flightBook.setTimeLimit(objResult.optString("time_limit"));
        flightBook.setMaskapai(objResult.optString("maskapai"));
        flightBook.setBookingCode(objResult.optString("booking_code"));

        JSONArray arrPassenger = objResult.optJSONArray("passenger");
        JSONObject objPassenger = null;

        passengers = new HashSet<>();

        for(int y=0;y<arrPassenger.length();y++){
            objPassenger = arrPassenger.optJSONObject(y);
            passenger = new FlightPassenger();
            passenger.setBirthDate(objPassenger.optString("birth_date"));
            passenger.setFullName(objPassenger.optString("full_name"));
            passenger.setPassengerType(objPassenger.optString("passenger_type"));
            passengers.add(passenger);

        }


        flightBook.setPassengers(passengers);

        JSONObject objContact = objResult.optJSONObject("contact_details");
        flightContact = new FlightContact();
       // flightContact.setEmail(objContact.optString(""));
        flightContact.setPhone(objContact.optString("phone"));
        flightContact.setFullName(objContact.optString("nama"));

        flightBook.setFlightContact(flightContact);

        JSONArray arrFlight = objResult.optJSONArray("flight_details");

        flightList = new HashSet<>();
        JSONObject objFlight = null;
        for(int i = 0;i < arrFlight.length();i++){
            objFlight = arrFlight.optJSONObject(i);
            flight = new FlightFlight();
            flight.setArriveArea(objFlight.optString("area_arrive"));
            flight.setDepartArea(objFlight.optString("area_depart"));
            flight.setEta(objFlight.optString("time_arrive"));
            flight.setEtd(objFlight.optString("time_depart"));
            flight.setEtdDate(objFlight.optString("flight_date"));

            flightList.add(flight);
        }


        flightBook.setFlights(flightList);

        return flightBook;

    }


    //static method
    public static Map translateToParam(FlightBookReq flightBookReq){
        Optional<FlightBookReq> opt = Optional.ofNullable(flightBookReq);

        Map param = new HashMap();

      /*  param.put("airline",flightBookReq.getAirlineId());
        param.put("adult_title_1", flightBookReq.getAdultTitle1());
        param.put("adult_name_1", flightBookReq.getAdultName1());
        param.put("contact_title", flightBookReq.getContactTitle()!=null?);
        param.put("contact_name",flightBookReq.getContactName());
        param.put("contact_phone", flightBookReq.getContactPhone());
        param.put("adult_title_2", flightBookReq.getAdultTitle2());
        param.put("adult_name_2", flightBookReq.getAdultName2());
        param.put("adult_special_request_1", flightBookReq.getAdultSpecialRequest1());
        param.put("child_title_1", flightBookReq.getChildTitle1());
        param.put("child_name_1", flightBookReq.getChildName1());
        param.put("child_special_req1", flightBookReq.getChildSpecialRequest1());
        param.put("infant_title_1", flightBookReq.getInfantTitle1());
        param.put("infant_name_1", flightBookReq.getInfantName1());
        param.put("infant_birth_date_1", flightBookReq.getInfantBirthDate1());
        param.put("infant_id_1", flightBookReq.getInfantId1());
        param.put("infant_special_request_1", flightBookReq.getInfantSpecialRequest1());
        */

        param.put("airline",opt.get().getAirlineId());
        param.put("adult_title_1", opt.get().getAdultTitle1());
        param.put("adult_name_1", opt.get().getAdultName1());
        param.put("contact_title", opt.get().getContactTitle());
        param.put("contact_name",opt.get().getContactName());
        param.put("contact_phone", opt.get().getContactPhone());
        param.put("adult_title_2", opt.get().getAdultTitle2());
        param.put("adult_name_2", opt.get().getAdultName2());
        param.put("adult_special_request_1", opt.get().getAdultSpecialRequest1());
        param.put("child_title_1", opt.get().getChildTitle1());
        param.put("child_name_1", opt.get().getChildName1());
        param.put("child_special_req1", opt.get().getChildSpecialRequest1());
        param.put("infant_title_1", opt.get().getInfantTitle1());
        param.put("infant_name_1", opt.get().getInfantName1());
        param.put("infant_birth_date_1", opt.get().getInfantBirthDate1());
        param.put("infant_id_1", opt.get().getInfantId1());
        param.put("infant_special_request_1", opt.get().getInfantSpecialRequest1());


        return param;
    }

}
