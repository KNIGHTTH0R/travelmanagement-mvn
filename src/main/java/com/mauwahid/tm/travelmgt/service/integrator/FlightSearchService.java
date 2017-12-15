package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightTravel;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightSearchResponse;
import com.mauwahid.tm.travelmgt.entity.log.LogFlightSearch;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightSearchInterface;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.OpsigoFlightSearch;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightSearch;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoFlightSearch;
import com.mauwahid.tm.travelmgt.repository.database.log.LogFlightSearchRepository;
import com.mauwahid.tm.travelmgt.utils.Common;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class FlightSearchService {


    private FlightSearchInterface flightSearchInterface;

    @Autowired
    private LogFlightSearchRepository logFlightSearchRepository;

    @Autowired
    private ApplicationContext context;


    public FlightSearchResponse searchFlight(long userId, FlightSearchReq flightSearchReq){

        Set<FlightTravel> departs = null;
        departs = travelSearch(flightSearchReq);

        Set<FlightTravel> returns = null;
        if(flightSearchReq.getRoundtrip().equalsIgnoreCase("1")){
            flightSearchReq = reverseFlightSchedule(flightSearchReq);
            returns = travelSearch(flightSearchReq);
        }


        FlightSearchResponse response = translateResponse(departs,returns);

        saveToLog(userId,response);

        return response;

    }

    private Set<FlightTravel> travelSearch(FlightSearchReq flightSearchReq){

        long start = System.currentTimeMillis();

        //api pointer
        Set<FlightTravel> flightTravels = new HashSet<>();

        Set<String> apis = new HashSet<>(Arrays.asList(flightSearchReq.getApiSource()));

        Set<CompletableFuture<Set<FlightTravel>>> completableFutures = new HashSet<>();


        if(apis.contains(Common.API_POINTER)) {
           // flightSearchInterface = new PointerFlightSearch();
            flightSearchInterface = context.getBean(PointerFlightSearch.class);
            CompletableFuture<Set<FlightTravel>>  data = flightSearchInterface.departTravel(flightSearchReq);
            completableFutures.add(data);
        }

        if(apis.contains(Common.API_TREVOHUB)) {
            flightSearchInterface = context.getBean(TrevoFlightSearch.class);
            CompletableFuture<Set<FlightTravel>>  data = flightSearchInterface.departTravel(flightSearchReq);
            completableFutures.add(data);
        }

        if(apis.contains(Common.API_OPSIGO)) {
            flightSearchInterface = context.getBean(OpsigoFlightSearch.class);
            CompletableFuture<Set<FlightTravel>>  data = flightSearchInterface.departTravel(flightSearchReq);
            completableFutures.add(data);
        }



        CompletableFuture.allOf(completableFutures.toArray(
                new CompletableFuture[completableFutures.size()]
        )).join();


        completableFutures.forEach(cf ->
        {
            try{
                flightTravels.addAll(cf.get());

                //flightTravels.add(cf.get());

                log.debug("CF ");

            }catch (Exception ex){
                log.error("ex "+ex.toString());
            }
        });


       // log.info("Elapsed time w CF: " + (System.currentTimeMillis() - start));

        return flightTravels;

    }

    private FlightSearchReq reverseFlightSchedule(FlightSearchReq flightSearchReq){

        String to = flightSearchReq.getFrom();
        String from = flightSearchReq.getTo();
        String departDate = flightSearchReq.getReturnDate();
        String returnDate = flightSearchReq.getDepartDate();

        flightSearchReq.setTo(to);
        flightSearchReq.setFrom(from);
        flightSearchReq.setDepartDate(departDate);
        flightSearchReq.setReturnDate(returnDate);

        return flightSearchReq;


    }

    private FlightSearchResponse translateResponse(Set<FlightTravel> departTravel, Set<FlightTravel> returnTravel){
        FlightSearchResponse flightSearchResponse = new FlightSearchResponse();
        flightSearchResponse.setSessionKey(Common.generateSessionID());
        flightSearchResponse.setDepartTravel(departTravel);
        flightSearchResponse.setReturnTravel(returnTravel);
        flightSearchResponse.setStatus(StatusCode.SUCCESS);
        flightSearchResponse.setMessage(StatusCode.S_SUCCESS);

        return flightSearchResponse;
    }

    private void saveToLog(long userId, FlightSearchResponse flightSearchResponse){

        //todo : log_save -> user_id, api_date, statusCode, message, jsonOf HotelSearchResponse

        String jsonData = Common.generateJSONFromObject(flightSearchResponse);

        LogFlightSearch logData = new LogFlightSearch();
        logData.setUserId(userId);
       // logData.setJsonData(LogErrorHelper.convertStringToBlob(jsonData));
        logData.setJsonData(jsonData);
        logData.setMessage(flightSearchResponse.getMessage());
        logData.setStatusCode(flightSearchResponse.getStatus());
        logData.setApiSessionKey(flightSearchResponse.getSessionKey());

        logFlightSearchRepository.save(logData);
    }
}
