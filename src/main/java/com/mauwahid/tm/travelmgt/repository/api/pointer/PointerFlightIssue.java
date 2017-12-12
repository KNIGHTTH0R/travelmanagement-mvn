package com.mauwahid.tm.travelmgt.repository.api.pointer;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightFlight;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightIssue;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightPassenger;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightIssueReq;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightIssueInterface;
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
            log.error("searchTravel translateToObj : "+ex.toString());

            return exceptionHandling(ex);
        }
    }


    private FlightIssue exceptionHandling(Exception ex){

        return null;
    };

    //Translator From JSON

    private FlightIssue translateToObject(String jsonData) throws JSONException {

        FlightIssue flightIssue = new FlightIssue();

        JSONObject objData = new JSONObject(jsonData);


        int code = Integer.parseInt(objData.optString("code"));

        try{

            if(code!=200){
                return flightIssue;
            }

            flightIssue.setStatusCode(code);


        }catch (Exception ex){
            log.debug("exc "+ex.toString());
        }
        

        JSONObject objResult = objData.optJSONObject("results");
        flightIssue.setTimeLimit(objResult.optString("time_limit"));
        flightIssue.setInfantPax(objResult.optString("infant"));
        flightIssue.setTotalPrice(objResult.optString("total_price"));
        flightIssue.setFullName(objResult.optString("name"));
        flightIssue.setBaseFare(objResult.optString("base_fare"));
        flightIssue.setPaymentStatus(objResult.optString("payment_status"));
        flightIssue.setChildPax(objResult.optString("child"));
        flightIssue.setTax(objResult.optString("tax"));
        flightIssue.setPhone(objResult.optString("phone"));
        flightIssue.setPaymentStatusCode(objResult.optString("payment_status_code"));
        flightIssue.setBookingTime(objResult.optString("booking_time"));
        flightIssue.setAdultPax(objResult.optString("adult"));
        flightIssue.setFlightFrom(objResult.optString("flight_from"));
        flightIssue.setAirline(objResult.optString("airline"));
        flightIssue.setNta(objResult.optString("NTA"));
        flightIssue.setBookingCode(objResult.optString("booking_code"));
        flightIssue.setFlightTo(objResult.optString("flight_to"));
        flightIssue.setApiId(objResult.optString("id"));

        JSONArray arrPassenger  = objResult.getJSONArray("passenger_list");
        JSONObject objPassenger = null;

        Set<FlightPassenger> passengers = new HashSet<>();
        FlightPassenger passenger = null;

        for(int i = 0;i<arrPassenger.length();i++){
            objPassenger = arrPassenger.getJSONObject(i);
            passenger = new FlightPassenger();
            passenger.setPassengerType(objPassenger.optString("passenger_type"));
            passenger.setBirthDate(objPassenger.optString("birth_date"));
         //   passenger.setTicketNo(objPassenger.optString("ticket_no"));
           // passenger.setFullName(objPassenger.optString("name"));

            String fullName = objPassenger.optString("full_name");

            if(!fullName.equalsIgnoreCase("")){

                String[] data = fullName.split(" ");

                if(data.length>1){
                    passenger.setFirstName(data[0]);

                    StringBuilder builder = new StringBuilder();

                    for(int x=1;x<data.length;x++){
                        builder.append(x);
                    }

                    passenger.setLastName(builder.toString());

                }else {
                    passenger.setFirstName(data[0]);
                }
            }


            passengers.add(passenger);
        }

        flightIssue.setPassengers(passengers);


        JSONArray arrFlight = objResult.optJSONArray("flight_list");
        JSONObject objFlight = null;

        Set<FlightFlight> flights = new HashSet<>();
        FlightFlight flight = null;

        for(int j=0;j<arrFlight.length();j++){
            objFlight = arrFlight.optJSONObject(j);
            flight = new FlightFlight();

            flight.setCode(objFlight.optString("code"));
            flight.setEtd(objFlight.optString("time_depart"));
            flight.setEtaDate(objFlight.optString("date_arrive"));
            flight.setEtdDate(objFlight.optString("date_depart"));
            flight.setArriveArea(objFlight.optString("area_arrive"));
            flight.setDepartArea(objFlight.optString("area_depart"));
            flight.setEta(objFlight.optString("time_arrive"));
            flight.setFlightId(objFlight.optString("flight_id"));

            flights.add(flight);

        }

        flightIssue.setFlights(flights);


        return flightIssue;

    }


    //static method
    public static Map translateToParam(FlightIssueReq flightIssueReq){
        Map param = new HashMap();

        param.put("booking_code",flightIssueReq.getBookingCode());
        param.put("airline", flightIssueReq.getAirline());

        return param;
    }
}
