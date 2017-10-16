package com.mauwahid.tm.travelmgt.repository.api.trevohub;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelBookReq;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightFlight;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightPrice;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightSeat;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightTravel;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.*;
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
public class TrevoFlightSearch {

    private Map params = new HashMap<String,String>();

    private String url;

    private Logger logger = LoggerFactory.getLogger(TrevoFlightSearch.class);

    @Autowired
    private TrevoApiCaller trevoApiCaller;

    private Set<FlightTravel> search(Map params) {

        String jsonData;

        url = TrevoApiCaller.uri;
        logger.debug("params : "+params);

        try{
            jsonData = trevoApiCaller.callApiPost(url,params);
            logger.debug("JSON RES : "+jsonData);
        }catch (IOException ex){
            logger.error("searchTravel : "+ex.toString());
            return exceptionHandling(ex);
        }

        try{
            return translateToObject(jsonData);
        }catch (Exception ex) {
            logger.error("translateToObj : "+ex.toString());

            return exceptionHandling(ex);
        }
    }


    private Set<FlightTravel> exceptionHandling(Exception ex){

        return null;
    };

    //Translator From JSON

    private Set<FlightTravel> translateToObject(String jsonData) throws JSONException {

        Set<FlightTravel> flightTravels = new HashSet<>();
        FlightTravel flightTravel;
        FlightFlight flight;
        Set<FlightFlight> flights;

        JSONObject objData = new JSONObject(jsonData);
        JSONObject objSchedule = objData.optJSONObject("schedule");
        JSONArray objArray = objSchedule.optJSONArray("depart");

        String sessionId = objData.optString("session_id");

        JSONObject objKey = null;

        for(int i=0;i<objArray.length();i++){
            objKey = objArray.optJSONObject(i);

            flightTravel = new FlightTravel();

            flightTravel.setTravelAPI("trevohub");
           // flightTravel.setTravelKey(sessionId);
            flightTravel.setEtd(objKey.optString("etd"));
            flightTravel.setEta(objKey.optString("eta"));
            flightTravel.setTravelId(sessionId);
            flightTravel.setFlightId(objKey.optString("fno"));
            flightTravel.setEtaDate(objKey.optString("eta_date"));
            flightTravel.setEtdDate(objKey.optString("etd_date"));
            flightTravel.setDepartArea(objKey.optString("from"));
            flightTravel.setArriveArea(objKey.optString("to"));

            flights = new HashSet<>();
            flight = new FlightFlight();

            flight.setEta(objKey.optString("eta"));
            flight.setEtd(objKey.optString("etd"));
            flight.setEtaDate(objKey.optString("eta_date"));
            flight.setEtdDate(objKey.optString("etd_date"));
            flight.setDepartArea(objKey.optString("from"));
            flight.setArriveArea(objKey.optString("to"));
            flight.setFlightId(objKey.optString("fno"));
          //  flight.setCode();

            Set<FlightSeat> flightSeats = getSeats(objKey.optString("class"));


            flights.add(flight);
            flightTravel.setFlights(flights);
            flightTravel.setSeats(flightSeats);

            flightTravels.add(flightTravel);
        }


        return flightTravels;

    }

    private Set<FlightSeat> getSeats(String jsonSeat){
        JSONArray arrSeat = null;
        JSONObject objSeat = null;
        JSONObject objPrice = null;

        Set<FlightSeat> seats = new HashSet<>();
        FlightSeat flightSeat;

        FlightPrice price;


        try{
            arrSeat = new JSONArray(jsonSeat);

            for(int i=0;i<arrSeat.length();i++){
                objSeat = arrSeat.optJSONObject(i);

                flightSeat = new FlightSeat();
                flightSeat.setCode(objSeat.optString("class_name"));
                flightSeat.setSeatClass(objSeat.optString("class"));
                flightSeat.setAvailable(objSeat.optString("seat"));
                flightSeat.setFlightKey(objSeat.optString("class_id"));

                objPrice = objSeat.optJSONObject("price");

                price = new FlightPrice();
                price.setTotalPrice(objPrice.optString("ticket_price"));

                flightSeat.setFlightPrice(price);

                seats.add(flightSeat);

            }



        }catch (JSONException ex){
            logger.error("Ex "+ex.toString());
            arrSeat = new JSONArray();

            flightSeat = new FlightSeat();
            flightSeat.setCode("error");

            seats.add(flightSeat);

        }

        return seats;
    }

    //static method
    public static Map translateParamDepart(FlightSearchReq flightSearchReq){
        Map param = new HashMap();

        Optional<FlightSearchReq> optFlightSearch = Optional.ofNullable(flightSearchReq);

        String airline = optFlightSearch.get().getAirlineName();

        param.put("b2b_code",TrevoApiCaller.b2bCode);
        param.put("h2h_code", TrevoApiCaller.h2hCode);
        param.put("action", "search");


        if(optFlightSearch.get().getAirlineType().equalsIgnoreCase("international")){
            param.put("app", "international_airline");
        }else if(optFlightSearch.get().getAirlineType().equalsIgnoreCase("domestic")){
            param.put("app", "domestic_airline");
        }

        param.put("search_type","low_fare");

        if(airline.equalsIgnoreCase("lion")){
            airline = "lionair";
        }

        param.put("airline", airline);
        param.put("from", optFlightSearch.get().getFrom());
        param.put("to",optFlightSearch.get().getTo());
        param.put("depart",optFlightSearch.get().getDepartDate());
        param.put("adult", optFlightSearch.get().getAdultPax());
        param.put("child", optFlightSearch.get().getChildPax());
        param.put("infant", optFlightSearch.get().getInfantPax());
        param.put("roundtrip","oneway");
        param.put("limit","20");

        return param;
    }

    public static Map translateParamReturn(FlightSearchReq flightSearchReq){
        Map param = new HashMap();

        Optional<FlightSearchReq> optFlightSearch = Optional.ofNullable(flightSearchReq);

        String airline = optFlightSearch.get().getAirlineName();

        param.put("b2b_code",TrevoApiCaller.b2bCode);
        param.put("h2h_code", TrevoApiCaller.h2hCode);
        param.put("action", "search");


        if(optFlightSearch.get().getAirlineType().equalsIgnoreCase("international")){
            param.put("app", "international_airline");
        }else if(optFlightSearch.get().getAirlineType().equalsIgnoreCase("domestic")){
            param.put("app", "domestic_airline");
        }

        param.put("search_type","low_fare");

        if(airline.equalsIgnoreCase("lion")){
            airline = "lionair";
        }

        param.put("airline", airline);
        param.put("from", optFlightSearch.get().getTo());
        param.put("to",optFlightSearch.get().getFrom());
        param.put("roundtrip","oneway");
        param.put("depart",optFlightSearch.get().getReturnDate());
        param.put("adult", optFlightSearch.get().getAdultPax());
        param.put("child", optFlightSearch.get().getChildPax());
        param.put("infant", optFlightSearch.get().getInfantPax());
        param.put("limit","20");

        return param;
    }


    public Set<FlightTravel> departTravel(FlightSearchReq flightSearchReq){
        Map param = translateParamDepart(flightSearchReq);
        return search(param);
    }

    public Set<FlightTravel> returnTravel(FlightSearchReq flightSearchReq){
        Map param = translateParamReturn(flightSearchReq);
        return search(param);
    }

}
