package com.mauwahid.tm.travelmgt.repository.api.antavaya;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightFlight;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightPrice;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightSeat;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightTravel;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.json.FlightAvailReq;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.json.RouteOps;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightSearchInterface;
import com.mauwahid.tm.travelmgt.utils.Common;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class AntavayaFlightSearch implements FlightSearchInterface{

    private Map params = new HashMap<String,String>();

    private String url;


    private AntavayaApiCaller antavayaApiCaller;


    private Set<FlightTravel> searchTravel(Map map) {

        url = AntavayaApiCaller.uri;
        url = url+"/apiv4/FlightAvailability";

        FlightAvailReq flightAvailReq = translateToFlightRequest(map);

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
            antavayaApiCaller = new AntavayaApiCaller();

            log.debug("JSON Req "+jsonParam);
            jsonData = antavayaApiCaller.callApiPost(url,jsonParam);
            log.debug("JSON RES : "+jsonData);
        }catch (IOException ex){
            log.error("search flight : "+ex.toString());
            return exceptionHandling(ex);
        }

        try{
            return translateToObject(jsonData);
        }catch (Exception ex) {
            log.error("faild to translateToObj : "+ex.toString());

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

         // airline = objData.optString("Airline");

        //FlightData
        FlightTravel flightTravel = null;
        FlightFlight flight = null;
        Set<FlightFlight> flights = null;

        FlightSeat seat = null;
        Set<FlightSeat> seats = null;

        for(int i = 0;i<arrData.length();i++){

            log.debug("Flight travel "+i);

            objTravel = arrData.optJSONObject(i);

          //  flightTravel.setTravelId(objTravel.optString("id_perjalanan"));
          //  flightTravel.setFlightCount(objTravel.optString("flight_count"));

            arrDetail = objTravel.optJSONArray("Flights");

            //iterate detail
            for(int j=0;j<arrDetail.length();j++){

                flightTravel = new FlightTravel();
                flightTravel.setErrorCode("0");
                flightTravel.setTravelAPI("opsigo");

                objDetail = arrDetail.optJSONObject(j);

            //    flightTravel.setTravelId(objDetail.optString("Id"));
                flightTravel.setEtaDate(objDetail.optString("ArriveDate"));
                flightTravel.setEtdDate(objDetail.optString("DepartDate"));
                flightTravel.setEta(objDetail.optString("ArriveTime"));
                flightTravel.setEtd(objDetail.optString("DepartTime"));
             //   flightTravel.setFlightNumber(objDetail.optString("Id"));
                flightTravel.setArriveArea(objDetail.optString("Destination"));
                flightTravel.setDepartArea(objDetail.optString("Origin"));

                flightTravel.setConnecting(objDetail.optBoolean("IsConnecting"));
                flightTravel.setTotalTransit(objDetail.optInt("TotalTransit"));

                flightTravel.setFlightNumber(objDetail.optString("Number"));
             //   log.debug("Airline name  "+objDetail.optString("AirlineName"));


            //    int transit = objDetail.optInt("TotalTransit");

                flights = new HashSet<>();


                if(!flightTravel.isConnecting()){
                    flight = new FlightFlight();
                   // flight.setFlightId(objDetail.optString("Id"));
                   // flight.setCode(objDetail.optString("Number"));

                    //flight.setFlightId(objDetail.optString("Number"));

                    flight.setEtd(objDetail.optString("DepartTime"));
                    flight.setEta(objDetail.optString("ArriveTime"));
                    flight.setEtaDate(objDetail.optString("ArriveDate"));
                    flight.setEtdDate(objDetail.optString("DepartDate"));
                    flight.setArriveArea(objDetail.optString("Destination"));
                    flight.setDepartArea(objDetail.optString("Origin"));
                    flight.setFlightId(objDetail.optString("Number"));

                    flight.setAirlineName(objDetail.optString("AirlineName"));
                    flight.setAirlineImageUrl(objDetail.optString("AirlineImageUrl"));

                    flights.add(flight);

                    JSONArray arrClassObj = objDetail.optJSONArray("ClassObjects");
                    JSONObject objClass;

                    Set<FlightSeat> flightSeats = new HashSet<>();

                 //   log.debug("to check class obj");

                    for(int x=0;x<arrClassObj.length();x++){
                        objClass = arrClassObj.getJSONObject(x);

                        FlightSeat flightSeat = new FlightSeat();
                        FlightPrice flightPrice = new FlightPrice();

                        flightSeat.setAvailable(objClass.optString("Seat"));
                        flightSeat.setClassKey(objClass.optString("Id"));

                        flightPrice.setFare(objClass.optString("Fare"));
                        flightPrice.setTax(objClass.optString("Tax"));


                        flightSeat.setFlightPrice(flightPrice);
                        flightSeat.setCode(objClass.optString("Code"));
                        flightSeat.setSeatClass(objClass.optString("Category"));

                        flightSeat.setFlightKey(objDetail.optString("Id"));


                        flightSeats.add(flightSeat);
                    }

                    flightTravel.setSeats(flightSeats);

                    flightTravel.setFlights(flights);


                }else{ //if there is transit

                  //  flightTravel = new FlightTravel();
             //       log.debug("to check connecting flight");

                    JSONArray jsonArray = objDetail.optJSONArray("ConnectingFlights");

                    Set<FlightTravel> connectingFlights = getFlightTravel(jsonArray);

                    flightTravel.setConnectingTravel(connectingFlights);
                }

                flightTravels.add(flightTravel);

            }

        }
        return flightTravels;
    }


    private Set<FlightTravel> getFlightTravel(JSONArray arrData){

        String airline = "";

        Set<FlightTravel> flightTravels= new LinkedHashSet<>();
        //JSON Main
     //   JSONObject objData = new JSONObject(jsonData);

//        JSONArray arrData = objData.optJSONArray("Schedules");
        JSONObject objTravel;
        JSONArray arrDetail;
        JSONObject objDetail;

        // airline = objData.optString("Airline");

        //FlightData
        FlightTravel flightTravel = null;
        FlightFlight flight = null;
        Set<FlightFlight> flights = null;

        FlightSeat seat = null;
        Set<FlightSeat> seats = null;



        for(int j=0;j<arrData.length();j++){

            flightTravel = new FlightTravel();
            flightTravel.setErrorCode("0");
            flightTravel.setTravelAPI(Common.API_ANTAVAYA);

            objDetail = arrData.optJSONObject(j);

            //    flightTravel.setTravelId(objDetail.optString("Id"));
            flightTravel.setEtaDate(objDetail.optString("ArriveDate"));
            flightTravel.setEtdDate(objDetail.optString("DepartDate"));
            flightTravel.setEta(objDetail.optString("ArriveTime"));
            flightTravel.setEtd(objDetail.optString("DepartTime"));
            //   flightTravel.setFlightNumber(objDetail.optString("Id"));
            flightTravel.setArriveArea(objDetail.optString("Destination"));
            flightTravel.setDepartArea(objDetail.optString("Origin"));

            flightTravel.setConnecting(objDetail.optBoolean("IsConnecting"));
            flightTravel.setTotalTransit(objDetail.optInt("TotalTransit"));


            int transit = objDetail.optInt("TotalTransit");

            flights = new HashSet<>();

            if(!flightTravel.isConnecting()){
                flight = new FlightFlight();
                // flight.setFlightId(objDetail.optString("Id"));
                // flight.setCode(objDetail.optString("Number"));

                //flight.setFlightId(objDetail.optString("Number"));
                flightTravel.setFlightNumber(objDetail.optString("Number"));

                flight.setEtd(objDetail.optString("DepartTime"));
                flight.setEta(objDetail.optString("ArriveTime"));
                flight.setEtaDate(objDetail.optString("ArriveDate"));
                flight.setEtdDate(objDetail.optString("DepartDate"));
                flight.setArriveArea(objDetail.optString("Destination"));
                flight.setDepartArea(objDetail.optString("Origin"));
                flight.setFlightId(objDetail.optString("Number"));
             //   log.debug("Airline name "+objDetail.optString("AirlineName"));
                flight.setAirlineName(objDetail.optString("AirlineName"));
                flight.setAirlineImageUrl(objDetail.optString("AirlineImageUrl"));


                flights.add(flight);

                JSONArray arrClassObj = objDetail.optJSONArray("ClassObjects");
                JSONObject objClass;

                Set<FlightSeat> flightSeats = new HashSet<>();

                for(int x=0;x<arrClassObj.length();x++){
                    objClass = arrClassObj.optJSONObject(x);

                    FlightSeat flightSeat = new FlightSeat();
                    FlightPrice flightPrice = new FlightPrice();

                    if(objClass == null){
                        objClass = new JSONObject();
                    }

                    flightSeat.setAvailable(objClass.optString("Seat"));
                    flightSeat.setClassKey(objClass.optString("Id"));

                    flightPrice.setFare(objClass.optString("Fare"));
                    flightPrice.setTax(objClass.optString("Tax"));


                    flightSeat.setFlightPrice(flightPrice);
                    flightSeat.setCode(objClass.optString("Code"));
                    flightSeat.setSeatClass(objClass.optString("Category"));

                    flightSeat.setFlightKey(objDetail.optString("Id"));

                    flightSeats.add(flightSeat);
                }

                flightTravel.setSeats(flightSeats);

                flightTravel.setFlights(flights);


            }else{ //if there is transit

                flightTravel = new FlightTravel();

                JSONArray arrConn = objDetail.optJSONArray("ConnectingFlights");

                Set<FlightTravel> connectingFlights = getFlightTravel(arrConn);

                flightTravel.setConnectingTravel(connectingFlights);
            }


            flightTravels.add(flightTravel);
        }


        return flightTravels;
    }


    private Map translateParamDepart(FlightSearchReq flightSearchReq){

        Map param = new HashMap();

        param.put("airline", Common.opsTranslateAirline(flightSearchReq.getAirlineName()));
        param.put("from", flightSearchReq.getFrom());
        param.put("to", flightSearchReq.getTo());
        param.put("date", flightSearchReq.getDepartDate());
        param.put("adult", flightSearchReq.getAdultPax());
        param.put("child", flightSearchReq.getChildPax());
        param.put("infant", flightSearchReq.getInfantPax());


        return param;
    }

    private Map translateParamReturn(FlightSearchReq flightSearchReq){

        Map param = new HashMap();

        param.put("airline",Common.opsTranslateAirline(flightSearchReq.getAirlineName()));
        param.put("to", flightSearchReq.getFrom());
        param.put("from", flightSearchReq.getTo());
        param.put("date", flightSearchReq.getReturnDate());
        param.put("adult", flightSearchReq.getAdultPax());
        param.put("child", flightSearchReq.getChildPax());
        param.put("infant", flightSearchReq.getInfantPax());


        return param;
    }


    private FlightAvailReq translateToFlightRequest(Map map){

        RouteOps route = new RouteOps();
        route.setDepartDate(map.get("date").toString());
        route.setDestination(map.get("to").toString());
        route.setOrigin(map.get("from").toString());

        List<RouteOps> routes = new ArrayList();
        routes.add(route);

        FlightAvailReq flightAvailReq = new FlightAvailReq();
        flightAvailReq.setRoutes(routes);
        try{
            flightAvailReq.setAdult(Integer.parseInt(map.get("adult").toString()));
        }catch (Exception ex){
            flightAvailReq.setAdult(0);
        }

        try{
            flightAvailReq.setChild(Integer.parseInt(map.get("child").toString()));
        }catch (Exception ex){
            flightAvailReq.setChild(0);
        }

        try{
            flightAvailReq.setInfant(Integer.parseInt(map.get("infant").toString()));
        }catch (Exception ex){
            flightAvailReq.setInfant(0);
        }


        flightAvailReq.setFareType("Default");

        int airline = Integer.parseInt(map.get("airline").toString());

        List<Integer> prefAirlines = new ArrayList<>();
        prefAirlines.add(airline);

        flightAvailReq.setPreferredAirlines(prefAirlines);


        return flightAvailReq;
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
