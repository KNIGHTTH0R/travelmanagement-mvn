package com.mauwahid.tm.travelmgt.repository.api.opsigo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightBookReq2;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightBook;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightContact;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightPassenger;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.FlightSegment;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.FlightBookInterface;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.json.flightAv.Contact;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.json.flightAv.FlightBookOpsReq;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.json.flightAv.PassengerOps;
import com.mauwahid.tm.travelmgt.repository.api.opsigo.json.flightAv.SegmentOps;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightBook;
import com.mauwahid.tm.travelmgt.utils.Common;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class OpsigoFlightBook implements FlightBookInterface {

    private String url;

    private Logger logger = LoggerFactory.getLogger(PointerFlightBook.class);

    private OpsigoApiCaller opsigoApiCaller;

    public FlightBook bookFlight(FlightBookReq2 flightBookReq) {

        url = OpsigoApiCaller.uri;
        url = url+"/apiv3/RsvFlight";

        FlightBookOpsReq flightBookOpsReq = translateToParam(flightBookReq);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonParam = "";

        try{
            jsonParam = objectMapper.writeValueAsString(flightBookOpsReq);
        }catch (Exception ex){
            jsonParam = "";
            log.error("ex "+ex.toString());
        }

        String jsonData;

        try{
            opsigoApiCaller = new OpsigoApiCaller();

            logger.debug("JSON Req "+jsonParam);
            jsonData = opsigoApiCaller.callApiPost(url,jsonParam);
            logger.debug("JSON RES : "+jsonData);
        }catch (IOException ex){
            logger.error("searchTravel : "+ex.toString());
            return exceptionHandling(ex);
        }

        try{
            return translateToObject(jsonData);
        }catch (Exception ex) {
            logger.error("searchTravel translateToObj : "+ex.toString());

            return exceptionHandling(ex);
        }
    }


    private FlightBook exceptionHandling(Exception ex){


        return null;
    };

    //Translator From JSON

    private FlightBook translateToObject(String jsonData) throws JSONException {

        JSONObject objData = null;

        FlightBook flightBook = new FlightBook();

        flightBook.setStatusCode("999");
        flightBook.setStatusDesc("Not Implemented");

        try{
            objData = new JSONObject(jsonData);



            flightBook.setBookingCode(objData.optString("PnrId"));
            flightBook.setStatusCode("01");
            flightBook.setStatusDesc("On Progress");

        }catch (Exception ex){

            return exceptionHandling(ex);
        }

        return flightBook;
    }


    //static method
    public static FlightBookOpsReq translateToParam(FlightBookReq2 flightBookReq){

        FlightBookOpsReq flightBookOpsReq = new FlightBookOpsReq();

        flightBookOpsReq.setCallBackUri(flightBookReq.getCallBackUri());
        flightBookOpsReq.setFlightType(flightBookOpsReq.getFlightType());

        FlightContact flightContact = flightBookReq.getFlightContact();

        //todo : create contact
        Contact contact = new Contact();

        contact.setEmail(flightContact.getEmail());
        contact.setFirstName(flightContact.getFirstName());
        contact.setLastName(flightContact.getLastName());
        contact.setHomePhone(flightContact.getHomePhone());
        contact.setMobilePhone(flightContact.getMobilePhone());
        contact.setTitle(flightContact.getTitle());

        flightBookOpsReq.setContact(contact);

        //todo : set passenger
        PassengerOps passengerOps;
        Set<PassengerOps> passengerOpsSet = new HashSet<>();

        int idx = 0;
        for(FlightPassenger passenger : flightBookReq.getPassengers()){

            passengerOps = new PassengerOps();
            passengerOps.setAdultAssoc(passenger.getAdultAssoc());
            passengerOps.setBirthDate(passenger.getBirthDate());
            passengerOps.setEmail(passenger.getEmail());
            passengerOps.setFirstName(passenger.getFirstName());
            passengerOps.setHomePhone(passenger.getHomePhone());
            passengerOps.setIdNumber(passenger.getIdNumber());
            passengerOps.setIndex(idx);
            passengerOps.setLastName(passenger.getLastName());
            passengerOps.setMobilePhone(passenger.getMobilePhone());
            passengerOps.setNationality(passenger.getNationality());
            passengerOps.setOtherPhone(passenger.getOtherPhone());
            passengerOps.setPassportNumber(passenger.getPassportNumber());
            passengerOps.setPassportExpired(passenger.getPassportExpired());
            passengerOps.setPassportOrigin(passenger.getPassportOrigin());
            passengerOps.setTitle(passenger.getTitle());
            passengerOps.setType(passenger.getType());

            passengerOpsSet.add(passengerOps);
            idx++;
        }

        flightBookOpsReq.setPassengers(passengerOpsSet);

        //todo : add segment
        SegmentOps segmentOps;
        Set<SegmentOps> segmentOpsSet = new HashSet<>();

        for(FlightSegment flightSegment : flightBookReq.getFlightSegments()){
            segmentOps = new SegmentOps();

            segmentOps.setAirline(Common.opsTranslateAirline(flightSegment.getAirline()));
            segmentOps.setClassId(flightSegment.getClassId());
            segmentOps.setFlightNumber(flightSegment.getFlightNumber());
            segmentOps.setOrigin(flightSegment.getOrigin());
            segmentOps.setDepartDate(flightSegment.getDepartDate());
            segmentOps.setDepartTime(flightSegment.getDepartTime());
            segmentOps.setDestination(flightSegment.getDestination());
            segmentOps.setArriveDate(flightSegment.getArriveDate());
            segmentOps.setArriveTime(flightSegment.getArriveTime());
            segmentOps.setClassCode(flightSegment.getClassCode());
            segmentOps.setFlightId(flightSegment.getFlightId());
            segmentOps.setNum(0);
            segmentOps.setSeq(0);

            segmentOpsSet.add(segmentOps);
        }

        flightBookOpsReq.setSegments(segmentOpsSet);

        return flightBookOpsReq;
    }

}
