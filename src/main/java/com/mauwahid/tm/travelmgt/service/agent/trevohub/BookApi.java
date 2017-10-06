package com.mauwahid.tm.travelmgt.service.agent.trevohub;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightBookReqOld;
import com.mauwahid.tm.travelmgt.domain.api.old.ResSearchDetail;
import com.mauwahid.tm.travelmgt.domain.apimodel.old.*;
import com.mauwahid.tm.travelmgt.repository.api.resthub.IHttpRequester;
import com.mauwahid.tm.travelmgt.repository.api.resthub.PostStdRequester;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class BookApi extends TrevoHubRequester {


    @Value("${trevohub.agent.id}")
    String apiId;

    private static final Logger logger = LoggerFactory.getLogger(BookApi.class);

    private FlightBookReqOld flightBookReq;

    public void setReqSearch(FlightBookReqOld flightBookReq){
        this.flightBookReq = flightBookReq;
    }

    private Map<String,String> params;


    public ResSearchDetail book(){

        ResSearchDetail resSearchDetail;

        params = getMapParams();



       // params.put("session_id",flightBookReq.get)

        String jsonResponse = "";


        return null;
    }

    private String reqDomesticAirlines() throws IOException {
        String jsonResponse = "";


        params.put("app","domestic_airline");

        IHttpRequester requester = new PostStdRequester();
        jsonResponse = requester.sendRequest(url,params);

        logger.debug("JSON RESPONSE : "+jsonResponse);
        return jsonResponse;
    }


    private String reqInternationalAirlines() throws IOException{
        String jsonResponse = "";

        params = getMapParams();

        params.put("app","international_airline");

        logger.debug("URL "+url+"; map : "+params.toString());

        IHttpRequester requester = new PostStdRequester();
        jsonResponse = requester.sendRequest(url,params);
        return jsonResponse;
    }


    private ResSearchDetail translateFromJson(String apiResponse){

        ResSearchDetail resSearchDetail = new ResSearchDetail();
        DepartDetail departDetail = new DepartDetail();

        ArrayList<ConnectingFlight> connectingFlights = new ArrayList<>();

        ConnectingFlight connectingFlight;
        Price price = new Price();
        Adult adult = new Adult();

        ReturnDetail returnDetail = new ReturnDetail();

        JSONObject objSearchDetail;
        JSONObject objSearchInfo;
        JSONObject objDepartDetail;

        JSONArray arrConnectingFlight;
        JSONObject objConnectingFlight;

        JSONObject objPrice;
        JSONObject objAdult;

        JSONObject objReturnDetail;
        JSONArray  arrRetConnectingFlight;
        JSONObject objRetConnectingFlight;

        JSONObject objRetPrice;
        JSONObject objRetAdult;

        try{
            objSearchDetail = new JSONObject(apiResponse);
            objSearchInfo =  objSearchDetail.optJSONObject("search_info");

            resSearchDetail.setRoundTrip(objSearchInfo.optString("round_trip"));
            resSearchDetail.setFrom(objSearchInfo.optString("from"));
            resSearchDetail.setTo(objSearchInfo.optString("to"));
            resSearchDetail.setToCity(objSearchInfo.optString("to_city"));
            resSearchDetail.setDepartDate(objSearchInfo.optString("depart"));
            resSearchDetail.setReturnDate(objSearchInfo.optString("return"));
            resSearchDetail.setAdult(objSearchInfo.optString("adult"));
            resSearchDetail.setChild(objSearchInfo.optString("child"));
            resSearchDetail.setInfant(objSearchInfo.optString("infant"));
            resSearchDetail.setSessionId(objSearchDetail.optString("session_id"));


            objDepartDetail = objSearchDetail.optJSONObject("depart_detail");

            departDetail.setAirlineId(objDepartDetail.optString("airline_id"));
            departDetail.setAirlineName(objDepartDetail.optString("airline_name"));
            departDetail.setType(objDepartDetail.optString("type"));
            departDetail.setFrom(objDepartDetail.optString("from"));
            departDetail.setFromCity(objDepartDetail.optString("from_city"));
            departDetail.setTo(objDepartDetail.optString("to"));
            departDetail.setToCity(objDepartDetail.optString("to_city"));
            departDetail.setDate(objDepartDetail.optString("date"));
            departDetail.setFno(objDepartDetail.optString("fno"));
            departDetail.setEtd(objDepartDetail.optString("etd"));
            departDetail.setEta(objDepartDetail.optString("eta"));
            departDetail.setEtdDate(objDepartDetail.optString("etd_date"));
            departDetail.setEtaDate(objDepartDetail.optString("eta_date"));
            departDetail.setaClass(objDepartDetail.optString("class"));
            departDetail.setClassName(objDepartDetail.optString("class_name"));

            arrConnectingFlight = objDepartDetail.getJSONArray("connecting_flight");

            for(int i=0;i<arrConnectingFlight.length();i++){
                objConnectingFlight = arrConnectingFlight.getJSONObject(i);
                connectingFlight = new ConnectingFlight();
                connectingFlight.setAirlineCode(objConnectingFlight.optString("airline_code"));
                connectingFlight.setAirlineId(objConnectingFlight.optString("airline_id"));
                connectingFlight.setAirlineName(objConnectingFlight.optString("airline_name"));
                connectingFlight.setFno(objConnectingFlight.optString("fno"));
                connectingFlight.setFrom(objConnectingFlight.optString("from"));
                connectingFlight.setFromCity(objConnectingFlight.optString("from_city"));
                connectingFlight.setTo(objConnectingFlight.optString("to"));
                connectingFlight.setToCity(objConnectingFlight.optString("to_city"));
                connectingFlight.setDate(objConnectingFlight.optString("date"));
                connectingFlight.setEtd(objConnectingFlight.optString("etd"));
                connectingFlight.setEta(objConnectingFlight.optString("eta"));
                connectingFlight.setEtdDate(objConnectingFlight.optString("etd_date"));
                connectingFlight.setEtaDate(objConnectingFlight.optString("eta_date"));
                connectingFlight.setClassName(objConnectingFlight.optString("class_name"));
                connectingFlight.setClassType(objConnectingFlight.optString("class_type"));

                connectingFlights.add(connectingFlight);

            }

            departDetail.setConnectingFlight(connectingFlights);

            objPrice = objDepartDetail.optJSONObject("price");

            objAdult = objPrice.optJSONObject("adult");
            adult.setBasic(objAdult.optString("basic"));
            adult.setPax(objAdult.optString("pax"));
            adult.setTax(objAdult.optString("tax"));
            adult.setIwjr(objAdult.optString("iwjr"));
            adult.setService(objAdult.optString("service"));
            adult.setMargin(objAdult.optString("margin"));
            adult.setTicketPrice(objAdult.optString("ticket_price"));

            price.setB2bCommision(objPrice.optString("b2b_commission"));
            price.setAgentCommision(objPrice.optString("agent_commission"));
            price.setBranchCommision(objPrice.optString("branch_commission"));
            price.setTicketPrice(objPrice.optString("ticket_price"));
            price.setAgentPrice(objPrice.optString("agent_price"));

            price.setAdult(adult);

            departDetail.setPrice(price);

            resSearchDetail.setDepartDetail(departDetail);

            //Return Detail

            objReturnDetail = objSearchDetail.optJSONObject("return_detail");

            returnDetail.setAirlineId(objReturnDetail.optString("airline_id"));
            returnDetail.setAirlineName(objReturnDetail.optString("airline_name"));
            returnDetail.setType(objReturnDetail.optString("type"));
            returnDetail.setFrom(objReturnDetail.optString("from"));
            returnDetail.setFromCity(objReturnDetail.optString("from_city"));
            returnDetail.setTo(objReturnDetail.optString("to"));
            returnDetail.setToCity(objReturnDetail.optString("to_city"));
            returnDetail.setDate(objReturnDetail.optString("date"));
            returnDetail.setFno(objReturnDetail.optString("fno"));
            returnDetail.setEtd(objReturnDetail.optString("etd"));
            returnDetail.setEta(objReturnDetail.optString("eta"));
            returnDetail.setEtdDate(objReturnDetail.optString("etd_date"));
            returnDetail.setEtaDate(objReturnDetail.optString("eta_date"));
            returnDetail.setaClass(objReturnDetail.optString("class"));
            returnDetail.setClassName(objReturnDetail.optString("class_name"));

            arrRetConnectingFlight = objReturnDetail.getJSONArray("connecting_flight");

            for(int i=0;i<arrRetConnectingFlight.length();i++){
                objRetConnectingFlight = arrRetConnectingFlight.getJSONObject(i);
                connectingFlight = new ConnectingFlight();
                connectingFlight.setAirlineCode(objRetConnectingFlight.optString("airline_code"));
                connectingFlight.setAirlineId(objRetConnectingFlight.optString("airline_id"));
                connectingFlight.setAirlineName(objRetConnectingFlight.optString("airline_name"));
                connectingFlight.setFno(objRetConnectingFlight.optString("fno"));
                connectingFlight.setFrom(objRetConnectingFlight.optString("from"));
                connectingFlight.setFromCity(objRetConnectingFlight.optString("from_city"));
                connectingFlight.setTo(objRetConnectingFlight.optString("to"));
                connectingFlight.setToCity(objRetConnectingFlight.optString("to_city"));
                connectingFlight.setDate(objRetConnectingFlight.optString("date"));
                connectingFlight.setEtd(objRetConnectingFlight.optString("etd"));
                connectingFlight.setEta(objRetConnectingFlight.optString("eta"));
                connectingFlight.setEtdDate(objRetConnectingFlight.optString("etd_date"));
                connectingFlight.setEtaDate(objRetConnectingFlight.optString("eta_date"));
                connectingFlight.setClassName(objRetConnectingFlight.optString("class_name"));
                connectingFlight.setClassType(objRetConnectingFlight.optString("class_type"));

                connectingFlights.add(connectingFlight);

            }

            returnDetail.setConnectingFlight(connectingFlights);

            objPrice = objReturnDetail.optJSONObject("price");

            objAdult = objPrice.optJSONObject("adult");
            adult.setBasic(objAdult.optString("basic"));
            adult.setPax(objAdult.optString("pax"));
            adult.setTax(objAdult.optString("tax"));
            adult.setIwjr(objAdult.optString("iwjr"));
            adult.setService(objAdult.optString("service"));
            adult.setMargin(objAdult.optString("margin"));
            adult.setTicketPrice(objAdult.optString("ticket_price"));

            price.setB2bCommision(objPrice.optString("b2b_commission"));
            price.setAgentCommision(objPrice.optString("agent_commission"));
            price.setBranchCommision(objPrice.optString("branch_commission"));
            price.setTicketPrice(objPrice.optString("ticket_price"));
            price.setAgentPrice(objPrice.optString("agent_price"));

            price.setAdult(adult);

            returnDetail.setPrice(price);

            resSearchDetail.setReturnDetail(returnDetail);



        }catch (Exception e){
            logger.error(("l.126. err translateFromJson : "+e.toString()));

        }

        return resSearchDetail;



    }

}
