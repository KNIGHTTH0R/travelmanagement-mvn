package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightTravel;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightSearchResponse;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightSearchInterface;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.OpsigoFlightSearch;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightSearch;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoFlightSearch;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class FlightSearchService {


    private FlightSearchInterface flightSearchInterface;


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

        Set<String> apis = new HashSet<>(Arrays.asList(flightSearchReq.getApiSource()));

        Set<CompletableFuture<Set<FlightTravel>>> completableFutures = new HashSet<>();


        if(apis.contains("pointer")) {
            flightSearchInterface = new PointerFlightSearch();
            CompletableFuture<Set<FlightTravel>>  data = flightSearchInterface.departTravel(flightSearchReq);
            completableFutures.add(data);
        }

        if(apis.contains("trevohub")) {
            flightSearchInterface = new TrevoFlightSearch();
            CompletableFuture<Set<FlightTravel>>  data = flightSearchInterface.departTravel(flightSearchReq);
            completableFutures.add(data);
        }

        if(apis.contains("opsigo")) {
            flightSearchInterface = new OpsigoFlightSearch();
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

            }catch (Exception ex){
                log.error("ex "+ex.toString());
            }
        });


        log.info("Elapsed time w CF: " + (System.currentTimeMillis() - start));

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
            flightSearchInterface = new PointerFlightSearch();
            CompletableFuture<Set<FlightTravel>>  data = flightSearchInterface.returnTravel(flightSearchReq);
            completableFutures.add(data);
        }

        if(apis.contains("trevohub")) {
            flightSearchInterface = new TrevoFlightSearch();
            CompletableFuture<Set<FlightTravel>>  data = flightSearchInterface.returnTravel(flightSearchReq);
            completableFutures.add(data);
        }

        if(apis.contains("opsigo")) {
            flightSearchInterface = new OpsigoFlightSearch();
            CompletableFuture<Set<FlightTravel>>  data = flightSearchInterface.returnTravel(flightSearchReq);
            completableFutures.add(data);
        }



        CompletableFuture.allOf(completableFutures.toArray(
                new CompletableFuture[completableFutures.size()]
        )).join();


        completableFutures.forEach(cf ->
        {
            try{
                flightTravels.addAll(cf.get());

            }catch (Exception ex){
                log.error("ex "+ex.toString());
            }
        });


        log.info("Elapsed time w CF: " + (System.currentTimeMillis() - start));

        return flightTravels;

    }
/*
    private Set<FlightTravel> returnTravelNCF(FlightSearchReq flightSearchReq){

        //api pointer
        Set<FlightTravel> flightTravels = new HashSet<>();
        Set<FlightTravel> travelsTemp = null;

        Set<String> apis = new HashSet<>(Arrays.asList(flightSearchReq.getApiSource()));


        travelsTemp = new HashSet<>();

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
*/




    private FlightSearchResponse translateResponse(Set<FlightTravel> departTravel, Set<FlightTravel> returnTravel){
        FlightSearchResponse flightSearchResponse = new FlightSearchResponse();
        flightSearchResponse.setSessionKey("abcdefghijklm");
        flightSearchResponse.setDepartTravel(departTravel);
        flightSearchResponse.setReturnTravel(returnTravel);
        flightSearchResponse.setStatus(StatusCode.SUCCESS);
        flightSearchResponse.setMessage(StatusCode.S_SUCCESS);

        return flightSearchResponse;
    }
}
