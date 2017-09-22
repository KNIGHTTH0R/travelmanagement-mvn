package com.mauwahid.tm.travelmgt.service.api.pointer;

import com.mauwahid.tm.travelmgt.domain.api.request.ReqSearch;
import com.mauwahid.tm.travelmgt.domain.api.response.pointer.*;
import com.mauwahid.tm.travelmgt.domain.apimodel.*;
import com.mauwahid.tm.travelmgt.service.api.GetWithHeaderRequester;
import com.mauwahid.tm.travelmgt.utils.Common;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class ApiPointerSearch extends PointerRequester {

    private Map<String,String> params;

    private String subUri = "/#airline/search/best_price";

    PointerTemplateWObj dTemplate;

    Airline airline;

    ReqSearch reqSearch;

    Logger logger = LoggerFactory.getLogger(ApiPointerSearch.class);

    @Value("${pointer.api.id}")
    String apiId;

    public void setSearchParam(ReqSearch reqSearch){
        this.reqSearch = reqSearch;
        params = getMapParams();
        params.put("airline", reqSearch.getAirlineName());
        params.put("search_type", reqSearch.getRoundtrip());
        params.put("from", reqSearch.getFrom());

        String departDate = "";
        String returnDate = "";

        try{
            departDate = Common.toPointerStandar(reqSearch.getDepartDate());
            returnDate = Common.toPointerStandar(reqSearch.getReturnDate());
        }catch (ParseException ex){
            logger.error("ParseException depart date : "+departDate+", returnDte : "+returnDate);
        }



        params.put("to", reqSearch.getTo());
        params.put("date", departDate);
        params.put("ret_date", returnDate);
        params.put("adult", reqSearch.getAdultPax());
        params.put("child", reqSearch.getChildPax());
        params.put("infant", reqSearch.getInfantPax());


    }


    public PointerTemplateWObj getData(){
        try{
            return jsonToTemplate(searchTravel());
        }catch (Exception ex){
            logger.error("EXCEPTION "+ex.toString());
            dTemplate.setErrorCode(StatusCode.API_TIME_OUT+"");
            dTemplate.setErrorDesc("RTO");

            return dTemplate;
        }

    }
    private String searchTravel() throws IOException {
        String jsonResponse = "";

     //   uri = uri+"#airline/search/best_price";

        params.put("app","domestic_airline");

        uri = uri.replace("#airline",reqSearch.getAirlineName());

        logger.debug("URL Pointer "+uri);

        GetWithHeaderRequester requester = new GetWithHeaderRequester();
        jsonResponse = requester.sendRequest(uri,getHeaderParams(),params);

        logger.debug("JSON Pointer : "+jsonResponse);
        return jsonResponse;
    }


    private PointerTemplateWObj jsonToTemplate(String jsonData) throws Exception{
        dTemplate = new PointerTemplateWObj();

        JSONObject jsonPointerSearch = new JSONObject(jsonData);

        String code = jsonPointerSearch.optString("code");

        if(!code.equalsIgnoreCase("200")){
            return dTemplate;
        }

        JSONObject jsonResult = jsonPointerSearch.getJSONObject("results");
        JSONArray jsonDeparts = jsonResult.getJSONArray("data");

        PointerResult result = new PointerResult();
        result.setAirline(jsonResult.getString("airline"));

        Set<PointerData> departList = new HashSet<>();
        PointerData depart = null;
        JSONObject jsonDepart = null;

        ArrayList<PointerDataDetail> pointerDataDetails = new ArrayList<>();
        PointerDataDetail pointerDataDetail = null;
        JSONArray jsonDetails = null;
        JSONObject jsonPointerDataDetail = null;

        Flight flight = null;
        Seat seat = null;
        BestPrice bestPrice = null;
        ArrayList<Flight> flights;
        ArrayList<Seat> seats;

        JSONObject jsonFlight = null;
        JSONObject jsonSeat = null;
        JSONArray jsonArrFlight = null;
        JSONArray jsonArrSeat = null;

        JSONObject jsonBestPrice = null;



        for(int i=0;i<jsonDeparts.length();i++){
            jsonDepart = jsonDeparts.getJSONObject(i);
            depart = new PointerData();

            depart.setFlightCount(jsonDepart.optInt("flight_count")+"");
            depart.setIdPerjalanan(jsonDepart.optString("id_perjalanan"));

            jsonDetails = jsonDepart.getJSONArray("detail");
            for(int j=0;j<jsonDetails.length();j++){
                jsonPointerDataDetail = jsonDetails.getJSONObject(j);
                pointerDataDetail = new PointerDataDetail();
                pointerDataDetail.setAirlineIcon(jsonPointerDataDetail.getString("airline_icon"));
                pointerDataDetail.setTimeDepart(jsonPointerDataDetail.getString("time_depart"));
                pointerDataDetail.setDateArrive(jsonPointerDataDetail.getString("date_arrive"));
                pointerDataDetail.setDateDepart(jsonPointerDataDetail.getString("date_depart"));
                pointerDataDetail.setAreaDepart(jsonPointerDataDetail.getString("area_depart"));
                pointerDataDetail.setAreaArrive(jsonPointerDataDetail.getString("area_arrive"));
                pointerDataDetail.setFlightID(jsonPointerDataDetail.getString("flight_id"));

                flights = new ArrayList<>();
                jsonArrFlight = jsonPointerDataDetail.getJSONArray("flight_list");

                for(int x=0;x<jsonArrFlight.length();x++){
                    jsonFlight = jsonArrFlight.getJSONObject(x);
                    flight = new Flight();

                    flight.setCode(jsonFlight.optString("code"));
                    flight.setCode(jsonFlight.optString("time_depart"));
                    flight.setDateArrive(jsonFlight.optString("date_arrive"));
                    flight.setDateDepart(jsonFlight.optString("date_depart"));
                    flight.setAreaDepart(jsonFlight.optString("area_depart"));
                    flight.setTimeArrive(jsonFlight.optString("time_arrive"));
                    flight.setAreaArrive(jsonFlight.optString("area_arrive"));

                    flights.add(flight);


                }

                seats = new ArrayList<>();
                jsonArrSeat = jsonPointerDataDetail.getJSONArray("seat");

                for(int y=0;y<jsonArrSeat.length();y++){
                    jsonSeat = jsonArrSeat.getJSONObject(y);
                    seat = new Seat();

                    seat.setAvailable(jsonSeat.optString("available"));
                    seat.setCode(jsonSeat.optString("code"));
                    seat.setFlightKey(jsonSeat.optString("flight_key"));
                    seat.setSeatClass(jsonSeat.optString("class"));

                    jsonBestPrice = jsonSeat.getJSONObject("best_price");

                    bestPrice = new BestPrice();
                    bestPrice.setFare(jsonBestPrice.getString("fare"));
                    bestPrice.setFareAdultPax(jsonBestPrice.getString("fare_adultpax"));
                    bestPrice.setFareChildPax(jsonBestPrice.getString("fare_childpax"));
                    bestPrice.setFareInfantPax(jsonBestPrice.getString("fare_infantpax"));
                    bestPrice.setTotalPrice(jsonBestPrice.getString("total_price"));


                    seat.setBestPrice(bestPrice);
                    seats.add(seat);


                }


                pointerDataDetail.setSeats(seats);
                pointerDataDetail.setFlights(flights);

                pointerDataDetails.add(pointerDataDetail);
            }

            depart.setDetails(pointerDataDetails);
            departList.add(depart);
        }

        result.setDeparts(departList);
     //   result.setAirline("");
      //  dTemplate.setData();
        PointerSearch pointerSearch = new PointerSearch();
        pointerSearch.setCode(jsonPointerSearch.getString("code"));
        pointerSearch.setPointerResult(result);

        dTemplate.setData(pointerSearch);


        return  dTemplate;
    }


}

