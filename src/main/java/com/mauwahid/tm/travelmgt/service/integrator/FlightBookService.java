package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightBookReq2;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightBookResponse;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightBook;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightBookInterface;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.OpsigoFlightBook;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightBook;
import com.mauwahid.tm.travelmgt.utils.Common;
import org.springframework.stereotype.Service;

@Service
public class FlightBookService {

    private FlightBookInterface flightBookInterface;



    public FlightBookResponse bookFlight(FlightBookReq2 flightBookReq){

        FlightBook flightBook = book(flightBookReq);

        FlightBookResponse response = translateResponse(flightBook);

        return response;

    }

    private FlightBook book(FlightBookReq2 flightBookReq){

        //api pointer

        FlightBook flightBook = null;
        switch (flightBookReq.getApiSource()){
            case Common.API_OPSIGO :
                flightBookInterface = new OpsigoFlightBook();
                break;
            case Common.API_POINTER :
                flightBookInterface = new PointerFlightBook();
                break;
            default:
                flightBookInterface = new PointerFlightBook();
        }

        flightBook = flightBookInterface.bookFlight(flightBookReq);
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
