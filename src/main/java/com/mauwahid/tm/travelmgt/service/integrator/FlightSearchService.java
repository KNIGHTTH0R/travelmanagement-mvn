package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightSearchResponse;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightTravel;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightSearch;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoFlightSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class FlightSearchService {


    @Autowired
    private PointerFlightSearch pointerFlightSearch;


    @Autowired
    private TrevoFlightSearch trevoFlightSearch;


    Logger logger = LoggerFactory.getLogger(FlightSearchService.class);



    public FlightSearchResponse searchFlight(FlightSearchReq flightSearchReq){

        Set<FlightTravel> departs = null;
        departs = departTravel(flightSearchReq);

        Set<FlightTravel> returns = null;
        if(flightSearchReq.getRoundtrip().equalsIgnoreCase("1"))
                returns = returnTravel(flightSearchReq);

        FlightSearchResponse response = translateResponse(departs,returns);

        return response;

    }

    private Set<FlightTravel> departTravel(FlightSearchReq flightSearchReq){

        long start = System.currentTimeMillis();

        //api pointer
        Set<FlightTravel> flightTravels = new HashSet<>();
        Set<FlightTravel> travelsTemp = null;

        Set<String> apis = new HashSet<>(Arrays.asList(flightSearchReq.getApiSource()));

        Set<CompletableFuture<Set<FlightTravel>>> completableFutures = new HashSet<>();


        CompletableFuture<Set<FlightTravel>>  cfPointer = null;
        CompletableFuture<Set<FlightTravel>>  cfTrevo = null;

        if(apis.contains("pointer")) {
            cfPointer = pointerFlightSearch.departTravelCF(flightSearchReq);
            completableFutures.add(cfPointer);
        }

        if(apis.contains("trevohub")){
            cfTrevo = trevoFlightSearch.departTravelCF(flightSearchReq);
            completableFutures.add(cfTrevo);

        }

        CompletableFuture.allOf(completableFutures.toArray(
                new CompletableFuture[completableFutures.size()]
        )).join();
/*
        try{
            flightTravels.addAll(cfPointer.get());
        }catch (Exception ex){
            logger.error("pointer "+ex.toString());
        }

        try{
            flightTravels.addAll(cfTrevo.get());
        }catch (Exception ex){
            logger.error("trevo "+ex.toString());
        }
*/


       completableFutures.forEach(cf ->
        {
            try{
                flightTravels.addAll(cf.get());

            }catch (Exception ex){
                logger.error("ex "+ex.toString());
            }
        });


      //  CompletableFuture.allOf(cfPointer,cfTrevo).join();

     /*   if(apis.contains("pointer")) {
            travelsTemp = pointerFlightSearch.departTravel(flightSearchReq);
            if (travelsTemp != null)
                flightTravels.addAll(travelsTemp);
        }

        if(apis.contains("trevohub")){
            travelsTemp = trevoFlightSearch.departTravel(flightSearchReq);
            if(travelsTemp!=null)
                flightTravels.addAll(travelsTemp);

        } */

        logger.info("Elapsed time w CF: " + (System.currentTimeMillis() - start));

        return flightTravels;

    }

    private Set<FlightTravel> departTravelNCF(FlightSearchReq flightSearchReq){

        long start = System.currentTimeMillis();

        //api pointer
        Set<FlightTravel> flightTravels = new HashSet<>();
        Set<FlightTravel> travelsTemp = null;

        Set<String> apis = new HashSet<>(Arrays.asList(flightSearchReq.getApiSource()));

         if(apis.contains("pointer")) {
            travelsTemp = pointerFlightSearch.departTravel(flightSearchReq);
            if (travelsTemp != null)
                flightTravels.addAll(travelsTemp);
        }

        if(apis.contains("trevohub")){
            travelsTemp = trevoFlightSearch.departTravel(flightSearchReq);
            if(travelsTemp!=null)
                flightTravels.addAll(travelsTemp);

        }
        logger.info("Elapsed time w/o CF: " + (System.currentTimeMillis() - start));


        return flightTravels;

    }

    private Set<FlightTravel> returnTravel(FlightSearchReq flightSearchReq){

        long start = System.currentTimeMillis();

        //api pointer
        Set<FlightTravel> flightTravels = new HashSet<>();
        Set<FlightTravel> travelsTemp = null;

        Set<String> apis = new HashSet<>(Arrays.asList(flightSearchReq.getApiSource()));

        Set<CompletableFuture<Set<FlightTravel>>> completableFutures = new HashSet<>();


        if(apis.contains("pointer")) {
            CompletableFuture<Set<FlightTravel>>  cfPointer = pointerFlightSearch.returnTravelCF(flightSearchReq);
            completableFutures.add(cfPointer);
        }

        if(apis.contains("trevohub")){
            CompletableFuture<Set<FlightTravel>>  cfTrevo = trevoFlightSearch.returnTravelCF(flightSearchReq);
            completableFutures.add(cfTrevo);

        }

        CompletableFuture.allOf(completableFutures.toArray(
                new CompletableFuture[completableFutures.size()]
        )).join();


        completableFutures.forEach(cf ->
        {
            try{
                flightTravels.addAll(cf.get());

            }catch (Exception ex){
                logger.error("ex "+ex.toString());
            }
        });


        //  CompletableFuture.allOf(cfPointer,cfTrevo).join();

     /*   if(apis.contains("pointer")) {
            travelsTemp = pointerFlightSearch.departTravel(flightSearchReq);
            if (travelsTemp != null)
                flightTravels.addAll(travelsTemp);
        }

        if(apis.contains("trevohub")){
            travelsTemp = trevoFlightSearch.departTravel(flightSearchReq);
            if(travelsTemp!=null)
                flightTravels.addAll(travelsTemp);

        } */

        logger.info("Elapsed time w CF: " + (System.currentTimeMillis() - start));

        return flightTravels;

    }

    private Set<FlightTravel> returnTravelNCF(FlightSearchReq flightSearchReq){

        //api pointer
        Set<FlightTravel> flightTravels = new HashSet<>();
        Set<FlightTravel> travelsTemp = null;

        Set<String> apis = new HashSet<>(Arrays.asList(flightSearchReq.getApiSource()));


        if(apis.contains("pointer")){
            travelsTemp = pointerFlightSearch.returnTravel(flightSearchReq);
            if(travelsTemp!=null)
                flightTravels.addAll(travelsTemp);
        }

        if(apis.contains("trevohub")){
            travelsTemp = trevoFlightSearch.returnTravel(flightSearchReq);
            if(travelsTemp!=null)
                flightTravels.addAll(travelsTemp);

        }

        return flightTravels;

    }







    private FlightSearchResponse translateResponse(Set<FlightTravel> departTravel, Set<FlightTravel> returnTravel){
        FlightSearchResponse flightSearchResponse = new FlightSearchResponse();
        flightSearchResponse.setSessionKey("abcdefghijklm");
        flightSearchResponse.setDepartTravel(departTravel);
        flightSearchResponse.setReturnTravel(returnTravel);
        flightSearchResponse.setStatusCode("0");
        flightSearchResponse.setStatusDesc("success");

        return flightSearchResponse;
    }
}
