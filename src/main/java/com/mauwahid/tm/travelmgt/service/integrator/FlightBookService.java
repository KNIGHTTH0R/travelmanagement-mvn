package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightBook;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightBookReq2;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightBookResponse;
import com.mauwahid.tm.travelmgt.entity.log.LogFlightBook;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightBookInterface;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.OpsigoFlightBook;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightBook;
import com.mauwahid.tm.travelmgt.repository.database.log.LogFlightBookRepository;
import com.mauwahid.tm.travelmgt.utils.Common;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightBookService {

    private FlightBookInterface flightBookInterface;


    @Autowired
    private LogFlightBookRepository logFlightBookRepository;

    public FlightBookResponse bookFlight(long userId, FlightBookReq2 flightBookReq){

        FlightBook flightBook = book(flightBookReq);

        FlightBookResponse response = translateResponse(flightBook);

        saveToLog(userId, flightBookReq, response);

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
        flightBookResponse.setSessionKey(Common.generateSessionID());
        flightBookResponse.setStatus(StatusCode.SUCCESS);
        flightBookResponse.setMessage(StatusCode.S_SUCCESS);

        flightBookResponse.setFlightBook(flightBook);

        return flightBookResponse;
    }

    private void saveToLog(long userId, FlightBookReq2 flightBookReq2, FlightBookResponse flightBookResponse){

        //todo : log_save -> user_id, api_date, statusCode, message, jsonOf HotelSearchResponse

        String jsonData = Common.generateJSONFromObject(flightBookResponse);

        LogFlightBook logData = new LogFlightBook();
        logData.setUserId(userId);
        logData.setJsonData(LogErrorHelper.convertStringToBlob(jsonData));
        logData.setMessage(flightBookResponse.getMessage());
        logData.setStatusCode(flightBookResponse.getStatus());
        logData.setApiSessionKey(flightBookResponse.getSessionKey());
        logData.setCallBackUri(flightBookReq2.getCallBackUri());
        logData.setPnrId(flightBookResponse.getFlightBook().getBookingCode());

        logFlightBookRepository.save(logData);
    }
}
