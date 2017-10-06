package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightBookReq;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightBookResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightSearchResponse;
import com.mauwahid.tm.travelmgt.domain.apimodel.FlightBook;
import com.mauwahid.tm.travelmgt.domain.apimodel.FlightTravel;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightBook;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class FlightBookService {

    @Autowired
    private PointerFlightBook pointerFlightBook;



    public FlightBookResponse bookFlight(FlightBookReq flightBookReq){

        FlightBook flightBook = book(flightBookReq);

        FlightBookResponse response = translateResponse(flightBook);

        return response;

    }

    private FlightBook book(FlightBookReq flightBookReq){

        //api pointer
        Map param = PointerFlightBook.translateToParam(flightBookReq);
        FlightBook flightBook = pointerFlightBook.bookFlight(param);
        return flightBook;

    }



    private FlightBookResponse translateResponse(FlightBook flightBook){
        FlightBookResponse flightBookResponse = new FlightBookResponse();
        flightBookResponse.setSessionKey("123134");
        flightBookResponse.setStatusCode("200");
        flightBookResponse.setStatusDesc("success");

        flightBookResponse.setFlightBook(flightBook);

        return flightBookResponse;
    }
}
