package com.mauwahid.tm.travelmgt.service.agent;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.pointer.*;
import com.mauwahid.tm.travelmgt.domain.api.response.trevohub.TrevoHubTemplateWObj;
import com.mauwahid.tm.travelmgt.domain.apimodel.*;
import com.mauwahid.tm.travelmgt.service.agent.pointer.ApiPointerSearch;
import com.mauwahid.tm.travelmgt.service.agent.trevohub.ApiSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchService {


    FlightSearchReq flightSearchReq;

    @Autowired
    ApiSearch apiSearch;

    @Autowired
    ApiPointerSearch apiPointerSearch;

    Logger logger = LoggerFactory.getLogger(SearchService.class);




    private TrevoHubTemplateWObj getTrevohub(){
        if(flightSearchReq.getAirlineName().equalsIgnoreCase("lion")){
            flightSearchReq.setAirlineName("lionair");
        }
        if(flightSearchReq.getRoundtrip().equalsIgnoreCase("1")){
            flightSearchReq.setRoundtrip("return");
        }
        if(flightSearchReq.getRoundtrip().equalsIgnoreCase("0")){
            flightSearchReq.setRoundtrip("oneway");
        }
        apiSearch.setSearchParam(flightSearchReq);


        logger.debug("Airline Type : "+ flightSearchReq.getAirlineType());

        if(flightSearchReq.getAirlineType().equalsIgnoreCase("domestic")){
            return apiSearch.getDomestics();
        }else{
            return apiSearch.getInternational();
        }
    }

    private PointerTemplateWObj getPointer(){


        apiPointerSearch.setSearchParam(flightSearchReq);
        return apiPointerSearch.getData();
       // if(flightSearchReq.getAirlineType().equalsIgnoreCase("domestic")){
    //        return apiPointerSearch.getData();
       // }else{
           // return apiPointerSearch.getInternational();
      //  }
    }


    public List<SearchInfo> searchAirline(FlightSearchReq flightSearchReq){

        this.flightSearchReq = flightSearchReq;

        //Get Data TrevoHub

        SearchInfo searchInfo = (SearchInfo) getTrevohub().getData();

        SearchInfo searchInfoPointer = translateToSearchInfo(searchInfo,getPointer());
        PointerTemplateWObj pointerTemp = getPointer();
        List<SearchInfo> searchInfos = new ArrayList<>();
        searchInfos.add(searchInfo);
        searchInfos.add(searchInfoPointer);

        return searchInfos;
    }

    private SearchInfo translateToSearchInfo(SearchInfo searchInfo,PointerTemplateWObj pointerTemp){

        PointerSearch pointerSearch = null;

        if(pointerTemp==null){
            return null;
        }

        pointerSearch = (PointerSearch) pointerTemp.getData();

        PointerResult pointerResult =  pointerSearch.getPointerResult();
        PointerData pointerData = pointerResult.getDeparts().iterator().next();
        PointerDataDetail pointerDataDetail = pointerData.getDetails().iterator().next();


        Seat pointerSeat = pointerDataDetail.getSeats().get(0);
        Flight flight = pointerDataDetail.getFlights().get(0);
        Schedule schedulePointer = new Schedule();



        if(searchInfo.getSchedule()==null){

        }
        Schedule scheduleTrevo = searchInfo.getSchedule();
        Depart departTrevo = scheduleTrevo.getDepart().get(0);

        Depart departPointer = new Depart();
        Airline airlinePointer = new Airline();


        SearchInfo searchInfoPointer = new SearchInfo();
        searchInfoPointer.setAdultPax(searchInfo.getAdultPax());
        searchInfoPointer.setFrom(searchInfo.getFrom());
        searchInfoPointer.setTo(searchInfo.getTo());
        searchInfoPointer.setFromCity(searchInfo.getFromCity());
        searchInfoPointer.setToCity(searchInfo.getToCity());
        searchInfoPointer.setDepartDate(searchInfo.getDepartDate());
        searchInfoPointer.setReturnDate(searchInfo.getReturnDate());
        searchInfoPointer.setAdultPax(searchInfo.getAdultPax());
        searchInfoPointer.setChildPax(searchInfo.getChildPax());
        searchInfoPointer.setApiId("Pointer");


        searchInfoPointer.setAirline(searchInfo.getAirline());
        searchInfoPointer.setSessionId(searchInfo.getSessionId());

        departPointer.setAirlineCode(departTrevo.getAirlineCode());
        departPointer.setAirlineId(departTrevo.getAirlineId());
        departPointer.setAirlineName(departTrevo.getAirlineName());
        departPointer.setScheduleKey(pointerSeat.getFlightKey());
        departPointer.setEtd(flight.getTimeDepart());
        departPointer.setEta(flight.getAreaArrive());


        AirlineClass pointerClass = new AirlineClass();
        pointerClass.setClassId(flight.getFlightId());


        BestPrice bestPrice = pointerSeat.getBestPrice();

        Price price = new Price();
        price.setTicketPrice(bestPrice.getTotalPrice());

        ArrayList<AirlineClass> airlineClasses = new ArrayList<>();
        airlineClasses.add(pointerClass);

        pointerClass.setPrice(price);
        departPointer.setClasses(airlineClasses);

        ArrayList<Depart> departs = new ArrayList<>();
        departs.add(departPointer);

        schedulePointer.setDepart(departs);
        searchInfoPointer.setSchedule(schedulePointer);

        return null;
    }




}
