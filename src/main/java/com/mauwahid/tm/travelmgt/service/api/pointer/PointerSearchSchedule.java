package com.mauwahid.tm.travelmgt.service.api.pointer;

import com.mauwahid.tm.travelmgt.domain.api.request.ReqSearch;
import com.mauwahid.tm.travelmgt.domain.api.response.pointer.*;
import com.mauwahid.tm.travelmgt.domain.apimodel.*;
import com.mauwahid.tm.travelmgt.service.api.GetWithHeaderRequester;
import com.mauwahid.tm.travelmgt.utils.Common;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class PointerSearchSchedule extends PointerRequester {

    private Map<String, String> params;

    private String subUri = "/#airline/search/best_price";

    private ReqSearch reqSearch;

    Logger logger = LoggerFactory.getLogger(PointerSearchSchedule.class);

    @Value("${pointer.api.id}")
    String apiId;

    public void setReqSearch(ReqSearch reqSearch) {
        this.reqSearch = reqSearch;
    }

    public PointerSearch getPointerSearch(){

        PointerSearch pointerSearch = null;
        params = getMapParams();
        params.put("airline", reqSearch.getAirlineName());
        params.put("search_type", reqSearch.getRoundtrip());
        params.put("from", reqSearch.getFrom());

        String departDate = "";
        String returnDate = "";

        try {
            departDate = Common.toPointerStandar(reqSearch.getDepartDate());
            returnDate = Common.toPointerStandar(reqSearch.getReturnDate());
        } catch (ParseException ex) {
            logger.error("ParseException depart date : " + departDate + ", returnDte : " + returnDate);
        }

        params.put("to", reqSearch.getTo());
        params.put("date", departDate);
        params.put("ret_date", returnDate);
        params.put("adult", reqSearch.getAdultPax());
        params.put("child", reqSearch.getChildPax());
        params.put("infant", reqSearch.getInfantPax());

        try{

            pointerSearch = translateToPointerTemp(callApi());
        }catch (Exception e){
            logger.debug("Ex "+e.toString());
            pointerSearch = new PointerSearch();
        }

        return pointerSearch;
    }

    public SearchInfo populateSchedule() {

        SearchInfo searchInfo = new SearchInfo();

        params = getMapParams();
        params.put("airline", reqSearch.getAirlineName());
        params.put("search_type", reqSearch.getRoundtrip());
        params.put("from", reqSearch.getFrom());

        String departDate = "";
        String returnDate = "";

        try {
            departDate = Common.toPointerStandar(reqSearch.getDepartDate());
            returnDate = Common.toPointerStandar(reqSearch.getReturnDate());
        } catch (ParseException ex) {
            logger.error("ParseException depart date : " + departDate + ", returnDte : " + returnDate);
        }

        params.put("to", reqSearch.getTo());
        params.put("date", departDate);
        params.put("ret_date", returnDate);
        params.put("adult", reqSearch.getAdultPax());
        params.put("child", reqSearch.getChildPax());
        params.put("infant", reqSearch.getInfantPax());

        String jsonData ="";

        logger.debug("masuk ke pointer");

        if(reqSearch.getAirlineType().equalsIgnoreCase("domestic")){

            logger.debug("domestic pointer");

            try{
                jsonData = callApi();

                logger.debug("error json : "+jsonData);
                PointerSearch pointerSearch = translateToPointerTemp(jsonData);
                searchInfo = translateToSearchInfo(pointerSearch);

            }catch (Exception ex){
                searchInfo.setStatus("Error get data from pointer "+ex.toString());
            }


        }else{
            searchInfo.setStatus("Error|Api not provide international flight");

        }

        return searchInfo;

    }

    private String callApi() throws Exception {
        String jsonResponse = "";

        logger.debug("uri : "+uri);
        uri = uri+"#airline/search/best_price";

        params.put("app", "domestic_airline");

        uri = uri.replace("#airline", reqSearch.getAirlineName());


        GetWithHeaderRequester requester = new GetWithHeaderRequester();
        jsonResponse = requester.sendRequest(uri, getHeaderParams(), params);

        return jsonResponse;
    }


    private PointerSearch translateToPointerTemp(String jsonData) {

        logger.debug("translate : "+jsonData);

        PointerSearch pointerSearch = new PointerSearch();

        JSONObject jsonPointerSearch = null;

        try {
            jsonPointerSearch = new JSONObject(jsonData);

        } catch (Exception ex) {
            logger.error("EXCEPTION JSon : " + ex.toString());
            pointerSearch.setCode("500");
        }

        String code = jsonPointerSearch.optString("code");

        if (!code.equalsIgnoreCase("200")) {
            pointerSearch.setCode(code);
            return pointerSearch;
        }

        JSONObject jsonResult = jsonPointerSearch.optJSONObject("results");
        JSONArray jsonDeparts = jsonResult.optJSONArray("data");

        PointerResult result = new PointerResult();
        result.setAirline(jsonResult.optString("airline"));

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


        for (int i = 0; i < jsonDeparts.length(); i++) {
            jsonDepart = jsonDeparts.optJSONObject(i);
            depart = new PointerData();

            depart.setFlightCount(jsonDepart.optInt("flight_count") + "");
            depart.setIdPerjalanan(jsonDepart.optString("id_perjalanan"));

            jsonDetails = jsonDepart.optJSONArray("detail");
            for (int j = 0; j < jsonDetails.length(); j++) {
                jsonPointerDataDetail = jsonDetails.optJSONObject(j);
                pointerDataDetail = new PointerDataDetail();
                pointerDataDetail.setAirlineIcon(jsonPointerDataDetail.optString("airline_icon"));
                pointerDataDetail.setTimeDepart(jsonPointerDataDetail.optString("time_depart"));
                pointerDataDetail.setDateArrive(jsonPointerDataDetail.optString("date_arrive"));
                pointerDataDetail.setDateDepart(jsonPointerDataDetail.optString("date_depart"));
                pointerDataDetail.setAreaDepart(jsonPointerDataDetail.optString("area_depart"));
                pointerDataDetail.setAreaArrive(jsonPointerDataDetail.optString("area_arrive"));
                pointerDataDetail.setFlightID(jsonPointerDataDetail.optString("flight_id"));

                flights = new ArrayList<>();
                jsonArrFlight = jsonPointerDataDetail.optJSONArray("flight_list");

                for (int x = 0; x < jsonArrFlight.length(); x++) {
                    jsonFlight = jsonArrFlight.optJSONObject(x);
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
                jsonArrSeat = jsonPointerDataDetail.optJSONArray("seat");

                for (int y = 0; y < jsonArrSeat.length(); y++) {
                    jsonSeat = jsonArrSeat.optJSONObject(y);
                    seat = new Seat();

                    seat.setAvailable(jsonSeat.optString("available"));
                    seat.setCode(jsonSeat.optString("code"));
                    seat.setFlightKey(jsonSeat.optString("flight_key"));
                    seat.setSeatClass(jsonSeat.optString("class"));

                    jsonBestPrice = jsonSeat.optJSONObject("best_price");

                    bestPrice = new BestPrice();
                    bestPrice.setFare(jsonBestPrice.optString("fare"));
                    bestPrice.setFareAdultPax(jsonBestPrice.optString("fare_adultpax"));
                    bestPrice.setFareChildPax(jsonBestPrice.optString("fare_childpax"));
                    bestPrice.setFareInfantPax(jsonBestPrice.optString("fare_infantpax"));
                    bestPrice.setTotalPrice(jsonBestPrice.optString("total_price"));


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
        pointerSearch.setCode(jsonPointerSearch.optString("code"));
        pointerSearch.setPointerResult(result);
        return pointerSearch;
    }


    private SearchInfo translateToSearchInfo(PointerSearch pointerSearch){
     //  SearchInfo searchInfo = new SearchInfo();


        PointerResult pointerResult =  pointerSearch.getPointerResult();
        PointerData pointerData = pointerResult.getDeparts().iterator().next();
        PointerDataDetail pointerDataDetail = pointerData.getDetails().iterator().next();


        Seat pointerSeat = pointerDataDetail.getSeats().get(0);
        Flight flight = pointerDataDetail.getFlights().get(0);
        Schedule schedulePointer = new Schedule();

        Depart departPointer = new Depart();


        SearchInfo searchInfoPointer = new SearchInfo();
        searchInfoPointer.setAdultPax(reqSearch.getAdultPax());
        searchInfoPointer.setFrom(reqSearch.getFrom());
        searchInfoPointer.setTo(reqSearch.getTo());
       // searchInfoPointer.setFromCity("");
      //  searchInfoPointer.setToCity(searchInfo.getToCity());
        searchInfoPointer.setDepartDate(reqSearch.getDepartDate());
        searchInfoPointer.setReturnDate(reqSearch.getReturnDate());
       searchInfoPointer.setChildPax(reqSearch.getAdultPax());
        searchInfoPointer.setApiId("Pointer");


      //  searchInfoPointer.setAirline(searchInfo.getAirline());
      //  searchInfoPointer.setSessionId(searchInfo.getSessionId());

     //   departPointer.setAirlineCode(departTrevo.getAirlineCode());
     //   departPointer.setAirlineId(departTrevo.getAirlineId());
     //   departPointer.setAirlineName(departTrevo.getAirlineName());
        departPointer.setScheduleKey(pointerSeat.getFlightKey());
        departPointer.setEtd(flight.getTimeDepart());
        departPointer.setEta(flight.getTimeArrive());


        AirlineClass pointerClass = new AirlineClass();
        pointerClass.setClassId(flight.getFlightId());


        BestPrice bestPrice = pointerSeat.getBestPrice();

        Price price = new Price();
        price.setTicketPrice(bestPrice.getTotalPrice());

        ArrayList<AirlineClass> airlineClasses = new ArrayList<>();
        airlineClasses.add(pointerClass);

        pointerClass.setPrice(price);
        departPointer.setClasses(airlineClasses);

        ArrayList<Depart> departs = new ArrayList<>();
        departs.add(departPointer);

        schedulePointer.setDepart(departs);
        searchInfoPointer.setSchedule(schedulePointer);

        return searchInfoPointer;

    }



}
