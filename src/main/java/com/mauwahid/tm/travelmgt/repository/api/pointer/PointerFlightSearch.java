package com.mauwahid.tm.travelmgt.repository.api.pointer;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightFlight;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightPrice;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightSeat;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightTravel;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightSearchInterface;
import com.mauwahid.tm.travelmgt.utils.Common;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Component
public class PointerFlightSearch implements FlightSearchInterface {

    private String url;

    private Logger logger = LoggerFactory.getLogger(PointerFlightSearch.class);

    @Autowired
    private PointerApiCaller pointerApiCaller;


    private Set<FlightTravel> searchTravel(Map params) {

        url = PointerApiCaller.uri;
        url = url+params.get("airline")+"/search/best_price";

        String jsonData;

        try{
            pointerApiCaller = new PointerApiCaller();
            jsonData = pointerApiCaller.callApiGet(url,params);
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
    };

    //Translator From JSON

    private Set<FlightTravel> translateToObject(String jsonData) throws JSONException{
        String airline = "";

        Set<FlightTravel> flightTravels= new LinkedHashSet<>();
        //JSON Main
        JSONObject objData = new JSONObject(jsonData);
        JSONObject objResult = objData.optJSONObject("results");

        JSONArray arrData = objResult.optJSONArray("data");
        JSONObject objTravel;
        JSONArray arrDetail;
        JSONObject objDetail;

        JSONArray arrSeat;
        JSONObject objSeat;

        JSONArray arrFlight;
        JSONObject objFlight;

        JSONObject objPrice;
        FlightPrice price;

      //  airline = objData.optString("airline");


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
            flightTravel.setTravelAPI(Common.API_POINTER);

          //  flightTravel.setTravelId(objTravel.optString("id_perjalanan"));
            flightTravel.setFlightCount(objTravel.optString("flight_count"));

            arrDetail = objTravel.optJSONArray("detail");

            //iterate detail
            for(int j=0;j<arrDetail.length();j++){
                objDetail = arrDetail.optJSONObject(j);

                flightTravel.setEtaDate(objDetail.optString("date_arrive"));
                flightTravel.setEtdDate(objDetail.optString("date_depart"));
                flightTravel.setEta(objDetail.optString("time_arrive"));
                flightTravel.setEtd(objDetail.optString("time_depart"));
                flightTravel.setFlightNumber(objDetail.optString("flight_id"));
                flightTravel.setArriveArea(objDetail.optString("area_arrive"));
                flightTravel.setDepartArea(objDetail.optString("area_depart"));

                arrFlight = objDetail.optJSONArray("flight_list");

                if(arrFlight.length() > 1){
                    flightTravel.setTotalTransit(arrFlight.length() - 1);
                    flightTravel.setConnecting(true);
                }

                flights = new LinkedHashSet<>();

                //Normal Case, while flight is direct/only one flight
                if(arrFlight.length() == 1) {

                    for (int k = 0; k < arrFlight.length(); k++) {
                        objFlight = arrFlight.optJSONObject(k);
                        flight = new FlightFlight();
                        //   flight.setCode(objFlight.optString("code"));
                        flight.setEtd(objFlight.optString("time_depart"));
                        flight.setEta(objFlight.optString("time_arrive"));
                        flight.setEtaDate(objFlight.optString("date_arrive"));
                        flight.setEtdDate(objFlight.optString("date_depart"));
                        flight.setArriveArea(objFlight.optString("area_arrive"));
                        flight.setDepartArea(objFlight.optString("area_depart"));
                        // flight.setFlightId(objFlight.optString("flight_id"));
                        flight.setFlightId(objDetail.optString("flight_id"));

                        flights.add(flight);
                    }

                    flightTravel.setFlights(flights);

                    arrSeat = objDetail.optJSONArray("seat");

                    seats = new HashSet<>();

                    for(int m=0;m<arrSeat.length();m++){
                        objSeat = arrSeat.optJSONObject(m);
                        seat = new FlightSeat();

                        seat.setAvailable(objSeat.optString("available"));
                        // seat.setCode(objSeat.optString("code"));
                        seat.setClassKey(objSeat.optString("code"));
                        seat.setFlightKey(objSeat.optString("flight_key"));
                        seat.setSeatClass(objSeat.optString("class"));
                        seat.setCode(objSeat.optString("code"));


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
                // !-- end of one flight travel

                }else{

                    logger.debug("Leng arr "+arrFlight.length());

                    Set<FlightTravel> connectingFlightTravel = new LinkedHashSet<>();
                    FlightTravel flConnecting;

                    arrSeat = objDetail.optJSONArray("seat");

                    seats = new HashSet<>();

                    for(int m=0;m<arrSeat.length();m++){
                        objSeat = arrSeat.optJSONObject(m);
                        seat = new FlightSeat();

                        seat.setAvailable(objSeat.optString("available"));
                        // seat.setCode(objSeat.optString("code"));
                        seat.setClassKey(objSeat.optString("code"));
                        seat.setFlightKey(objSeat.optString("flight_key"));
                        seat.setSeatClass(objSeat.optString("class"));
                        seat.setCode(objSeat.optString("code"));


                        objPrice = objSeat.optJSONObject("best_price");
                        price = new FlightPrice();

                        if(j==0){
                            price.setFare(objPrice.optString("fare"));
                            price.setFareAdultPax(objPrice.optString("fare_adult_pax"));
                            price.setFareChildPax(objPrice.optString("fare_child_pax"));
                            price.setFareInfantPax(objPrice.optString("fare_infant_pax"));
                            price.setTax(objPrice.optString("tax"));
                            price.setTotalPrice(objPrice.optString("total_price"));

                        }else{
                            price.setFare("0");
                            price.setFareAdultPax("0");
                            price.setFareChildPax("0");
                            price.setFareInfantPax("0");
                            price.setTax("0");
                            price.setTotalPrice("0");

                        }

                        seat.setFlightPrice(price);


                        seats.add(seat);

                    }



                    for (int k = 0; k < arrFlight.length(); k++) {


                        objFlight = arrFlight.optJSONObject(k);

                        flConnecting = new FlightTravel();

                        flights = new LinkedHashSet<>();

                        flConnecting.setEtaDate(objFlight.optString("date_arrive"));
                        flConnecting.setEtdDate(objFlight.optString("date_depart"));
                        flConnecting.setEta(objFlight.optString("time_arrive"));
                        flConnecting.setEtd(objFlight.optString("time_depart"));
                        flConnecting.setFlightNumber(objFlight.optString("flight_id"));
                        flConnecting.setArriveArea(objFlight.optString("area_arrive"));
                        flConnecting.setDepartArea(objFlight.optString("area_depart"));

                        flight = new FlightFlight();
                        //   flight.setCode(objFlight.optString("code"));
                        flight.setEtd(objFlight.optString("time_depart"));
                        flight.setEta(objFlight.optString("time_arrive"));
                        flight.setEtaDate(objFlight.optString("date_arrive"));
                        flight.setEtdDate(objFlight.optString("date_depart"));
                        flight.setArriveArea(objFlight.optString("area_arrive"));
                        flight.setDepartArea(objFlight.optString("area_depart"));
                        // flight.setFlightId(objFlight.optString("flight_id"));
                        flight.setFlightId(objFlight.optString("flight_id"));

                        flights.add(flight);

                        flConnecting.setFlights(flights);

                        flConnecting.setSeats(seats);

                        connectingFlightTravel.add(flConnecting);


                    }

                    flightTravel.setConnectingTravel(connectingFlightTravel);

                    int totalConnecting = connectingFlightTravel.size();

                    if(totalConnecting == 2){

                        int[] arrSizeSeat = new int[2];
                        int idx = 0;
                        FlightPrice tmpPrice = new FlightPrice();
                        Iterator iterator = connectingFlightTravel.iterator();

                        while (iterator.hasNext()){

                            FlightTravel ft = (FlightTravel)iterator.next();
                            arrSizeSeat[idx] = ft.getSeats().size();
                            for(FlightSeat fs : ft.getSeats()){
                                tmpPrice = fs.getFlightPrice();
                            }
                            idx++;
                        }

                        if(arrSizeSeat[0] == 1 && arrSizeSeat[1] == 1){
                            flightTravel.setPairing(true);
                            flightTravel.setPairFlight(tmpPrice);
                        }else{
                            flightTravel.setPairing(false);
                        }


                    }


                }

            }

            flightTravels.add(flightTravel);
        }
        return flightTravels;
    }


    //static method
    private Map translateParamDepart(FlightSearchReq flightSearchReq){

        Map param = new HashMap();

        param.put("airline",flightSearchReq.getAirlineName());
        param.put("from", flightSearchReq.getFrom());
        param.put("to", flightSearchReq.getTo());
        param.put("date", flightSearchReq.getDepartDate());
        param.put("adult", flightSearchReq.getAdultPax());
        param.put("child", flightSearchReq.getChildPax());
        param.put("infant", flightSearchReq.getInfantPax());


        return param;
    }

    public static Map translateParamReturn(FlightSearchReq flightSearchReq){
        Map param = new HashMap();

        param.put("airline",flightSearchReq.getAirlineName());
        param.put("from", flightSearchReq.getTo());
        param.put("to", flightSearchReq.getFrom());
        param.put("date", flightSearchReq.getReturnDate());
        param.put("adult", flightSearchReq.getAdultPax());
        param.put("child", flightSearchReq.getChildPax());
        param.put("infant", flightSearchReq.getInfantPax());


        return param;
    }

    public Set<FlightTravel> departTravelOld(FlightSearchReq flightSearchReq){
        Map param = translateParamDepart(flightSearchReq);
        return searchTravel(param);
    }

    public Set<FlightTravel> returnTravelOld(FlightSearchReq flightSearchReq){
        Map param = translateParamReturn(flightSearchReq);
        return searchTravel(param);
    }

    @Async
    public CompletableFuture<Set<FlightTravel>> departTravel(FlightSearchReq flightSearchReq){
        Map param = translateParamDepart(flightSearchReq);

        Set<FlightTravel> flightTravels = searchTravel(param);

        return CompletableFuture.completedFuture(flightTravels);
    }

    @Async
    public CompletableFuture<Set<FlightTravel>> returnTravel(FlightSearchReq flightSearchReq){
        Map param = translateParamReturn(flightSearchReq);

        Set<FlightTravel> flightTravels = searchTravel(param);

        return CompletableFuture.completedFuture(flightTravels);
    }

}
