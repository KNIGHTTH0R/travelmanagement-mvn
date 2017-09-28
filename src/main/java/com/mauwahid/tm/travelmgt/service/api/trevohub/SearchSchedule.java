package com.mauwahid.tm.travelmgt.service.api.trevohub;

import com.mauwahid.tm.travelmgt.domain.api.request.ReqSearch;
import com.mauwahid.tm.travelmgt.domain.apimodel.*;
import com.mauwahid.tm.travelmgt.service.api.IHttpRequester;
import com.mauwahid.tm.travelmgt.service.api.PostStdRequester;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Service
public class SearchSchedule extends TrevoHubRequester  {

    private ReqSearch reqSearch;

    private SearchInfo searchInfo;

    private Map<String,String> params;

    @Value("${trevohub.api.id}")
    String apiId;

    private static final Logger logger = LoggerFactory.getLogger(SearchSchedule.class);


    public void setReqSearch(ReqSearch reqSearch){
        this.reqSearch = reqSearch;
    }


    public SearchInfo populateSchedules(){

        params = getMapParams();
        params.put("airline", reqSearch.getAirlineName());
        params.put("roundtrip", reqSearch.getRoundtrip());
        params.put("from", reqSearch.getFrom());
        params.put("to", reqSearch.getTo());
        params.put("depart", reqSearch.getDepartDate());
        params.put("return", reqSearch.getReturnDate());

        params.put("adult", reqSearch.getAdultPax());
        params.put("child", reqSearch.getChildPax());
        params.put("infant", reqSearch.getInfantPax());
        params.put("action","search");


        String jsonResponse = "";

        if(reqSearch.getAirlineType().equalsIgnoreCase("domestic")){
            try{

                jsonResponse = reqDomesticAirlines();

                searchInfo = translateFromJson(jsonResponse);


            }catch (Exception ex){
                logger.error("Error Exception "+ex.toString());
                searchInfo = new SearchInfo();
                searchInfo.setApiId(apiId);
                searchInfo.setStatus(StatusCode.S_API_ERROR_CALL);
            }

        }else{

            try{
                jsonResponse = reqInternationalAirlines();

                searchInfo = translateFromJson(jsonResponse);

            }catch (Exception ex){
                logger.error("Error Exception "+ex.toString());
                searchInfo = new SearchInfo();
                searchInfo.setApiId(apiId);
                searchInfo.setStatus(StatusCode.S_API_ERROR_CALL);
            }
        }

        return searchInfo;
    }

    private String reqDomesticAirlines() throws IOException {
        String jsonResponse = "";


        params.put("app","domestic_airline");
        logger.debug("URL "+url+"; map : "+params.toString());

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


    private SearchInfo translateFromJson(String apiResponse){

        JSONObject jsonObject = null;
        SearchInfo searchInfo = null;
        Airline airline = null;
        String sessionId = "";
        Schedule schedule = null;
        ArrayList<Depart> departs = null;
        ArrayList<Return> returns = null;

        logger.debug("JSON data : "+apiResponse);

        try{
            jsonObject = new JSONObject(apiResponse);

            if(!jsonObject.optString("error_no").equalsIgnoreCase("0")){
                searchInfo.setStatus(jsonObject.optString("error_no")+"|"+jsonObject.optString("error_msg"));
            }else{

                JSONObject jsonSearch = jsonObject.getJSONObject("search_info");
                JSONObject jsonAirline = jsonObject.getJSONObject("airlines_detail");

                sessionId = jsonObject.optString("session_id");
                searchInfo = new SearchInfo();

                searchInfo.setApiId("Trevohub");
                searchInfo.setSessionId(sessionId);

                searchInfo.setAdultPax(jsonSearch.optString("adult"));
                searchInfo.setChildPax(jsonSearch.optString("child"));
                searchInfo.setInfantPax(jsonSearch.optString("infant"));
                searchInfo.setDepartDate(jsonSearch.optString("depart"));
                searchInfo.setReturnDate(jsonSearch.optString("return"));
                searchInfo.setFrom(jsonSearch.optString("from"));
                searchInfo.setTo(jsonSearch.optString("to"));
                searchInfo.setFromCity(jsonSearch.optString("from_city"));
                searchInfo.setToCity(jsonSearch.optString("to_city"));

                airline = new Airline();
                airline.setAirlinesName(jsonAirline.optString("airline_name"));
                airline.setAirlinesId(jsonAirline.optString("airline_id"));
                airline.setAirlinesCode(jsonAirline.optString("airline_code"));

                JSONObject jsonSchedule = jsonObject.getJSONObject("schedule");
                schedule = new Schedule();

                departs = new ArrayList<>();
                returns = new ArrayList<>();

                Depart depart = null;
                Return aReturn = null;
                JSONObject objDepart = null;
                JSONObject objReturn = null;


                JSONArray jsonDeparts = jsonSchedule.optJSONArray("depart");

                JSONArray jsonReturns = jsonSchedule.optJSONArray("return");

                ArrayList<AirlineClass> airClassListTemp = null;
                AirlineClass airClassTemp = null;
                Price priceTemp = null;

                //fill depart
                for(int i=0;i<jsonDeparts.length();i++){
                    depart = new Depart();
                    objDepart = jsonDeparts.getJSONObject(i);

                    depart.setAirlineCode(objDepart.optString("schedule_key"));
                    depart.setAirlineId(objDepart.optString("airline_id"));
                    depart.setAirlineName(objDepart.optString("airline_name"));
                    depart.setFrom(objDepart.optString("from"));
                    depart.setTo(objDepart.optString("to"));
                    depart.setFromCity(objDepart.optString("from_city"));
                    depart.setToCity(objDepart.optString("to_city"));
                    depart.setDate(objDepart.optString("date"));
                    depart.setVia(objDepart.optString("via"));
                    depart.setFno(objDepart.optString("fno"));
                    depart.setEtd(objDepart.optString("etd"));
                    depart.setEta(objDepart.optString("eta"));
                    depart.setEtdDate(objDepart.optString("etd_date"));
                    depart.setEtaDate(objDepart.optString("eta_date"));
                    depart.setType(objDepart.optString("type"));
                    depart.setScheduleKey(objDepart.optString("schedule_key"));

                    airClassListTemp = new ArrayList<>();
                    JSONArray arrClassList = objDepart.getJSONArray("class");
                    JSONObject objClass = null;
                    JSONObject objPrice = null;

                    for(int idxClass=0;idxClass<arrClassList.length();idxClass++){
                        objClass = arrClassList.getJSONObject(idxClass);

                        airClassTemp = new AirlineClass();
                        airClassTemp.setClassId(objClass.optString("class_id"));
                        airClassTemp.setClassName(objClass.optString("class_name"));
                        airClassTemp.setClassType(objClass.optString("class"));
                        airClassTemp.setSeat(objClass.optString("seat"));

                        priceTemp = new Price();

                        objPrice = objClass.getJSONObject("price");
                        priceTemp.setB2bCommision(objPrice.optString("b2b_commission"));
                        priceTemp.setAgentCommision(objPrice.optString("agent_commission"));
                        priceTemp.setBranchCommision(objPrice.optString("branch_commission"));
                        priceTemp.setTicketPrice(objPrice.optString("ticket_price"));

                        airClassTemp.setPrice(priceTemp);
                        airClassListTemp.add(airClassTemp);

                    }

                    depart.setClasses(airClassListTemp);
                    departs.add(depart);

                }


                for(int j=0;j<jsonReturns.length();j++){
                    aReturn = new Return();
                    objReturn = jsonReturns.getJSONObject(j);

                    aReturn.setAirlineCode(objReturn.optString("schedule_key"));
                    aReturn.setAirlineId(objReturn.optString("airline_id"));
                    aReturn.setAirlineName(objReturn.optString("airline_name"));
                    aReturn.setFrom(objReturn.optString("from"));
                    aReturn.setTo(objReturn.optString("to"));
                    aReturn.setFromCity(objReturn.optString("from_city"));
                    aReturn.setToCity(objReturn.optString("to_city"));
                    aReturn.setDate(objReturn.optString("date"));
                    aReturn.setVia(objReturn.optString("via"));
                    aReturn.setFno(objReturn.optString("fno"));
                    aReturn.setEtd(objReturn.optString("etd"));
                    aReturn.setEta(objReturn.optString("eta"));
                    aReturn.setEtdDate(objReturn.optString("etd_date"));
                    aReturn.setEtaDate(objReturn.optString("eta_date"));
                    aReturn.setType(objReturn.optString("type"));
                    aReturn.setScheduleKey(objReturn.optString("schedule_key"));

                    airClassListTemp = new ArrayList<>();
                    JSONArray arrClassList = objReturn.getJSONArray("class");
                    JSONObject objClass = null;
                    JSONObject objPrice = null;

                    for(int idxClass=0;idxClass<arrClassList.length();idxClass++){
                        objClass = arrClassList.getJSONObject(idxClass);

                        airClassTemp = new AirlineClass();
                        airClassTemp.setClassId(objClass.optString("class_id"));
                        airClassTemp.setClassName(objClass.optString("class_name"));
                        airClassTemp.setClassType(objClass.optString("class"));
                        airClassTemp.setSeat(objClass.optString("seat"));

                        priceTemp = new Price();

                        objPrice = objClass.getJSONObject("price");
                        priceTemp.setB2bCommision(objPrice.optString("b2b_commission"));
                        priceTemp.setAgentCommision(objPrice.optString("agent_commission"));
                        priceTemp.setBranchCommision(objPrice.optString("branch_commission"));
                        priceTemp.setTicketPrice(objPrice.optString("ticket_price"));

                        airClassTemp.setPrice(priceTemp);
                        airClassListTemp.add(airClassTemp);

                    }

                    aReturn.setClasses(airClassListTemp);
                    returns.add(aReturn);
                }



                schedule.setDepart(departs);
                schedule.setaReturn(returns);

                searchInfo.setSchedule(schedule);


            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  searchInfo;

    }




}
