package com.mauwahid.tm.travelmgt.repository.api.interfaces;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightBookReq2;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightBook;

public interface FlightBookInterface {


    FlightBook bookFlight(FlightBookReq2 flightBookReq);

}


