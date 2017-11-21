package com.mauwahid.tm.travelmgt.repository.api.opsigo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightFlight;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightPrice;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightSeat;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightTravel;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightSearchInterface;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.json.flightAv.FlightAvailReq;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.json.flightAv.Route;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightSearch;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OpsigoFlightSearch implements FlightSearchInterface {

    private Map params = new HashMap<String,String>();

    private String url;

    private Logger logger = LoggerFactory.getLogger(PointerFlightSearch.class);

    @Autowired
    private OpsigoApiCaller opsigoApiCaller;


    public Set<FlightTravel> searchTravel(FlightAvailReq flightAvailReq) {

        url = OpsigoApiCaller.uri;
        url = url+"/apiv4/FlightAvailability";

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonParam = "";

        try{
            jsonParam = objectMapper.writeValueAsString(flightAvailReq);
        }catch (Exception ex){
            jsonParam = "";
            log.error("ex "+ex.toString());
        }

        String jsonData;

        try{
            jsonData = opsigoApiCaller.callApiPost(url,jsonParam);
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


    private Set<FlightTravel> exceptionHandling(Exception ex){

        return null;
    }

    //Translator From JSON

    private Set<FlightTravel> translateToObject(String jsonData) throws JSONException {
        String airline = "";

        Set<FlightTravel> flightTravels= new HashSet<>();
        //JSON Main
        JSONObject objData = new JSONObject(jsonData);

        JSONArray arrData = objData.optJSONArray("Schedules");
        JSONObject objTravel;
        JSONArray arrDetail;
        JSONObject objDetail;

        JSONArray arrSeat;
        JSONObject objSeat;

        JSONArray arrFlight;
        JSONObject objFlight;

        JSONObject objPrice;
        FlightPrice price;

        airline = objData.optString("airline");

        //FlightData
        FlightTravel flightTravel = null;
        FlightFlight flight = null;
        Set<FlightFlight> flights = null;

        FlightSeat seat = null;
        Set<FlightSeat> seats = null;

        for(int i = 0;i<arrData.length();i++){

            objTravel = arrData.optJSONObject(i);
            flightTravel = new FlightTravel();
            flightTravel.setErrorCode("0");
            flightTravel.setTravelAPI("opsigo");

          //  flightTravel.setTravelId(objTravel.optString("id_perjalanan"));
          //  flightTravel.setFlightCount(objTravel.optString("flight_count"));

            arrDetail = objTravel.optJSONArray("Flights");

            //iterate detail
            for(int j=0;j<arrDetail.length();j++){
                objDetail = arrDetail.optJSONObject(j);

                flightTravel.setTravelId(objDetail.optString("Id"));
                flightTravel.setEtaDate(objDetail.optString("ArriveDate"));
                flightTravel.setEtdDate(objDetail.optString("DepartDate"));
                flightTravel.setEta(objDetail.optString("ArriveTime"));
                flightTravel.setEtd(objDetail.optString("DepartTime"));
                flightTravel.setFlightId(objDetail.optString("Id"));
                flightTravel.setArriveArea(objDetail.optString("area_arrive"));
                flightTravel.setDepartArea(objDetail.optString("area_depart"));

                int transit = objDetail.optInt("TotalTransit");

                flights = new HashSet<>();

                if(transit == 0){
                    flight = new FlightFlight();
                    flight.setFlightId(objDetail.optString("Id"));
                    flight.setCode(objDetail.optString("Number"));
                    flight.setEtd(objDetail.optString("DepartTime"));
                    flight.setEta(objDetail.optString("ArriveTime"));
                    flight.setEtaDate(objDetail.optString("ArriveDate"));
                    flight.setEtdDate(objDetail.optString("DepartDate"));
                    flight.setArriveArea(objDetail.optString("Destination"));
                    flight.setDepartArea(objDetail.optString("Origin"));

                    flights.add(flight);

                    flightTravel.setFlights(flights);

                    JSONArray arrClassObj = objDetail.optJSONArray("ClassObject");
                    JSONObject objClass;

                    Set<FlightSeat> flightSeats = new HashSet<>();

                    for(int x=0;x<arrClassObj.length();x++){
                        objClass = arrClassObj.getJSONObject(x);

                        FlightSeat flightSeat = new FlightSeat();
                        FlightPrice flightPrice = new FlightPrice();

                        flightSeat.setAvailable(objClass.optString("Seat"));
                        flightPrice.setFare(objClass.optString("Fare"));
                        flightPrice.setTax(objClass.optString("Tax"));


                        flightSeat.setFlightPrice(flightPrice);
                        flightSeat.setCode(objClass.optString("Code"));
                        flightSeat.setSeatClass(objClass.optString("Category"));



                        flightSeats.add(flightSeat);
                    }

                    flightTravel.setSeats(flightSeats);

                }else{ //if there is transit



                }


                arrFlight = objDetail.optJSONArray("flight_list");

                 for (int k=0;k<arrFlight.length();k++){
                    objFlight = arrFlight.optJSONObject(k);
                    flight = new FlightFlight();
                    flight.setCode(objFlight.optString("code"));
                    flight.setEtd(objFlight.optString("time_depart"));
                    flight.setEta(objFlight.optString("time_arrive"));
                    flight.setEtaDate(objFlight.optString("date_arrive"));
                    flight.setEtdDate(objFlight.optString("date_depart"));
                    flight.setArriveArea(objFlight.optString("area_arrive"));
                    flight.setDepartArea(objFlight.optString("area_depart"));
                    flight.setFlightId(objFlight.optString("flight_id"));

                    flights.add(flight);
                }

                flightTravel.setFlights(flights);

                arrSeat = objDetail.optJSONArray("seat");

                seats = new HashSet<>();

                for(int m=0;m<arrSeat.length();m++){
                    objSeat = arrSeat.optJSONObject(m);
                    seat = new FlightSeat();

                    seat.setAvailable(objSeat.optString("available"));
                    seat.setCode(objSeat.optString("code"));
                    seat.setFlightKey(objSeat.optString("flight_key"));
                    seat.setSeatClass(objSeat.optString("class"));


                    objPrice = objSeat.optJSONObject("best_price");
                    price = new FlightPrice();
                    price.setFare(objPrice.optString("fare"));
                    price.setFareAdultPax(objPrice.optString("fare_adult_pax"));
                    price.setFareChildPax(objPrice.optString("fare_child_pax"));
                    price.setFareInfantPax(objPrice.optString("fare_infant_pax"));
                    price.setTax(objPrice.optString("tax"));
                    price.setTotalPrice(objPrice.optString("total_price"));

                    seat.setFlightPrice(price);
                    seats.add(seat);



                }

                flightTravel.setSeats(seats);
            }

            flightTravels.add(flightTravel);
        }
        return flightTravels;
    }


    private FlightAvailReq translateParamDepart(FlightSearchReq flightSearchReq){

        Route route = new Route();
        route.setDepartDate(flightSearchReq.getDepartDate());
        route.setDestination(flightSearchReq.getTo());
        route.setOrigin(flightSearchReq.getFrom());

        List<Route> routes = new ArrayList();
        routes.add(route);

        FlightAvailReq flightAvailReq = new FlightAvailReq();
        flightAvailReq.setRoutes(routes);
        try{
            flightAvailReq.setAdult(Integer.parseInt(flightSearchReq.getAdultPax()));
        }catch (Exception ex){
            flightAvailReq.setAdult(0);
        }

        try{
            flightAvailReq.setChild(Integer.parseInt(flightSearchReq.getChildPax()));
        }catch (Exception ex){
            flightAvailReq.setChild(0);
        }

        try{
            flightAvailReq.setInfant(Integer.parseInt(flightSearchReq.getInfantPax()));
        }catch (Exception ex){
            flightAvailReq.setInfant(0);
        }


        return flightAvailReq;
    }

    public static FlightAvailReq translateParamReturn(FlightSearchReq flightSearchReq){

        Route route = new Route();
        route.setDepartDate(flightSearchReq.getReturnDate());
        route.setDestination(flightSearchReq.getFrom());
        route.setOrigin(flightSearchReq.getTo());

        List<Route> routes = new ArrayList();
        routes.add(route);

        FlightAvailReq flightAvailReq = new FlightAvailReq();
        flightAvailReq.setRoutes(routes);
        try{
            flightAvailReq.setAdult(Integer.parseInt(flightSearchReq.getAdultPax()));
        }catch (Exception ex){
            flightAvailReq.setAdult(0);
        }

        try{
            flightAvailReq.setChild(Integer.parseInt(flightSearchReq.getChildPax()));
        }catch (Exception ex){
            flightAvailReq.setChild(0);
        }

        try{
            flightAvailReq.setInfant(Integer.parseInt(flightSearchReq.getInfantPax()));
        }catch (Exception ex){
            flightAvailReq.setInfant(0);
        }


        return flightAvailReq;
    }

    public Set<FlightTravel> departTravel(FlightSearchReq flightSearchReq){
        FlightAvailReq param = translateParamDepart(flightSearchReq);
        return searchTravel(param);
    }

    public Set<FlightTravel> returnTravel(FlightSearchReq flightSearchReq){
        FlightAvailReq param = translateParamReturn(flightSearchReq);
        return searchTravel(param);
    }




}
