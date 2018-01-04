package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightBook;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightBookReq2;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightBookResponse;
import com.mauwahid.tm.travelmgt.entity.log.LogFlightBook;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightBookInterface;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.OpsigoFlightBook;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightBook;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightSearch;
import com.mauwahid.tm.travelmgt.repository.database.log.LogFlightBookRepository;
import com.mauwahid.tm.travelmgt.utils.Common;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class FlightBookService {

    private FlightBookInterface flightBookInterface;

    @Autowired
    private ApplicationContext context;

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
        if(flightBookReq.getApiSource().equalsIgnoreCase(Common.API_OPSIGO)) {
            flightBookInterface = context.getBean(OpsigoFlightBook.class);
        }

        if(flightBookReq.getApiSource().equalsIgnoreCase(Common.API_POINTER)) {
            flightBookInterface = context.getBean(PointerFlightBook.class);
        }

        try {
            flightBook = flightBookInterface.bookFlight(flightBookReq);
        }catch (Exception ex){
            flightBook = null;
        }

        return flightBook;

    }



    private FlightBookResponse translateResponse(FlightBook flightBook){
        FlightBookResponse flightBookResponse = new FlightBookResponse();
        flightBookResponse.setSessionKey(Common.generateSessionID());

        if(flightBook==null){
            flightBookResponse.setStatus(StatusCode.NOT_IMPLEMENTED);
            flightBookResponse.setMessage(StatusCode.S_NOT_IMPLEMENTED);
            return flightBookResponse;
        }

        flightBookResponse.setStatus(StatusCode.SUCCESS);
        flightBookResponse.setMessage(StatusCode.S_SUCCESS);


        if(flightBook.getBookingCode().equalsIgnoreCase("")){
            flightBookResponse.setStatus(StatusCode.NO_CONTENT);
            flightBookResponse.setMessage(StatusCode.S_NO_CONTENT);

            flightBook.setStatusCode(StatusCode.NO_CONTENT+"");
            flightBook.setStatusDesc("Failed");
        }

        flightBookResponse.setFlightBook(flightBook);

        return flightBookResponse;
    }

    private void saveToLog(long userId, FlightBookReq2 flightBookReq2, FlightBookResponse flightBookResponse){

        String jsonData = Common.generateJSONFromObject(flightBookResponse);

        LogFlightBook logData = new LogFlightBook();
        logData.setUserId(userId);
       // logData.setJsonData(LogErrorHelper.convertStringToBlob(jsonData));
        logData.setJsonData(jsonData);
        logData.setMessage(flightBookResponse.getMessage());
        logData.setStatusCode(flightBookResponse.getStatus());
        logData.setApiSessionKey(flightBookResponse.getSessionKey());
        logData.setCallBackUri(flightBookReq2.getCallBackUri());

        if(flightBookResponse.getFlightBook()!=null)
            logData.setPnrId(flightBookResponse.getFlightBook().getBookingCode());

        logFlightBookRepository.save(logData);
    }
}
