package com.mauwahid.tm.travelmgt.repository.api.interfaces;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightTravel;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface FlightSearchInterface {


     CompletableFuture<Set<FlightTravel>> departTravel(FlightSearchReq flightSearchReq);

     CompletableFuture<Set<FlightTravel>> returnTravel(FlightSearchReq flightSearchReq);
}
