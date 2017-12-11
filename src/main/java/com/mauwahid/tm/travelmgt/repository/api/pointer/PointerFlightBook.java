package com.mauwahid.tm.travelmgt.repository.api.pointer;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightBookReq2;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.*;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightBookInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class PointerFlightBook implements FlightBookInterface {

    private String url;

    private Logger logger = LoggerFactory.getLogger(PointerFlightBook.class);

   // @Autowired
    private PointerApiCaller pointerApiCaller;


    public FlightBook bookFlight(FlightBookReq2 flightBookReq) {

        Map params = translateToParam(flightBookReq);

        url = PointerApiCaller.uri;
        url = url+params.get("airline")+"/book";

        String jsonData;

        logger.debug("params : "+params);

        pointerApiCaller = new PointerApiCaller();
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
           // passenger.setFullName(objPassenger.optString("full_name"));
            String fullName = objPassenger.optString("full_name");

            if(!fullName.equalsIgnoreCase("")){

                String[] data = fullName.split(" ");

                if(data.length>1){
                    passenger.setFirstName(data[0]);

                    StringBuilder builder = new StringBuilder();

                    for(int i=1;i<data.length;i++){
                        builder.append(i);
                    }

                    passenger.setLastName(builder.toString());

                }else {
                    passenger.setFirstName(data[0]);
                }
            }

            passenger.setPassengerType(objPassenger.optString("passenger_type"));
            passengers.add(passenger);

        }


        flightBook.setPassengers(passengers);

        JSONObject objContact = objResult.optJSONObject("contact_details");
        flightContact = new FlightContact();
       // flightContact.setEmail(objContact.optString(""));
        flightContact.setPhone(objContact.optString("phone"));
        //flightContact.setFullName(objContact.optString("nama"));

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
    private static Map translateToParam(FlightBookReq2 flightBookReq){
       // Optional<FlightBookReq> opt = Optional.ofNullable(flightBookReq);

        Map param = new HashMap();

        FlightContact flightContact = flightBookReq.getFlightContact();

        FlightSegment flightSegment = flightBookReq.getFlightSegments()
                            .stream().findFirst().get();

        int idx = 1;


        String airline = flightSegment.getAirline();

        if(airline.equalsIgnoreCase("lionair")){
            airline = "lion";
        }

        param.put("airline", airline);
        param.put("flight_key", flightSegment.getFlightId());

        param.put("contact_title", flightContact.getTitle());
        param.put("contact_name", flightContact.getFirstName() + " "+flightContact.getLastName());
        param.put("contact_phone", flightContact.getPhone());


        for(FlightPassenger passenger : flightBookReq.getPassengers()){



            if(passenger.getType().equalsIgnoreCase("1")){
                param.put("adult_title_"+idx,passenger.getTitle());
                param.put("adult_name_"+idx,passenger.getFirstName()+" "+passenger.getLastName());
                param.put("adult_special_request_"+idx,passenger.getAdultAssoc());

            }else if(passenger.getType().equalsIgnoreCase("2")){
                param.put("child_title_"+idx,passenger.getTitle());
                param.put("child_name_"+idx,passenger.getFirstName()+" "+passenger.getLastName());
                param.put("child_special_request_"+idx,passenger.getAdultAssoc());

            }else{
                param.put("infant_title_"+idx,passenger.getTitle());
                param.put("infant_name_"+idx,passenger.getFirstName()+" "+passenger.getLastName());
                param.put("infant_special_request_"+idx,passenger.getAdultAssoc());
                param.put("infant_birth_date_"+idx, passenger.getBirthDate());

            }

        }

        return param;
    }

}
