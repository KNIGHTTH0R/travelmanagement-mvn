package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightBookReq;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightIssueReq;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightBookResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.FlightIssueResponse;
import com.mauwahid.tm.travelmgt.domain.apimodel.FlightBook;
import com.mauwahid.tm.travelmgt.domain.apimodel.FlightIssue;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightBook;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightIssue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FlightIssueService {

    @Autowired
    private PointerFlightIssue pointerFlightIssue;


    Logger logger = LoggerFactory.getLogger(FlightIssueService.class);

    public FlightIssueResponse issueFlight(FlightIssueReq flightIssueReq){

        FlightIssue flightIssue = issue(flightIssueReq);

        FlightIssueResponse response = translateResponse(flightIssue);

        return response;

    }

    private FlightIssue issue(FlightIssueReq flightIssueReq){

        //api pointer
        Map param = PointerFlightIssue.translateToParam(flightIssueReq);
        FlightIssue flightIssue = pointerFlightIssue.issueFlight(param);

        logger.debug("fligtIssue "+flightIssue.getFullName());
        return flightIssue;

    }



    private FlightIssueResponse translateResponse(FlightIssue flightIssue){
        FlightIssueResponse flightIssueResponse = new FlightIssueResponse();
        flightIssueResponse.setSessionKey("123134");
        flightIssueResponse.setStatusCode("200");
        flightIssueResponse.setStatusDesc("success");

        flightIssueResponse.setFlightIssue(flightIssue);

        return flightIssueResponse;
    }
}
