package com.mauwahid.tm.travelmgt.service.agent.trevohub;

import com.mauwahid.tm.travelmgt.domain.apimodel.Airport;
import com.mauwahid.tm.travelmgt.domain.api.response.IData;
import com.mauwahid.tm.travelmgt.domain.api.response.trevohub.TrevoHubTemplateWithArray;
import com.mauwahid.tm.travelmgt.service.agent.IHttpRequester;
import com.mauwahid.tm.travelmgt.service.agent.PostStdRequester;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Component
public class ApiGetAirports extends TrevoHubRequester {

    private Map<String,String> params;

    @Autowired
    TrevoHubTemplateWithArray dTemplate;

    Airport airport;

    private static final Logger logger = LoggerFactory.getLogger(ApiGetAirports.class);

    private String reqDomesticAirports(String departure) throws IOException {
        String jsonResponse = "--";

        params = getMapParams();

        params.put("app","domestic_airline");
        params.put("action","get_airports");

        if(!departure.equalsIgnoreCase(""))
            params.put("departure",departure);


        logger.debug("URL "+url+"; map : "+params.toString());

        IHttpRequester requester = new PostStdRequester();
        jsonResponse = requester.sendRequest(url,params);
        return jsonResponse;
    }


    private String reqInternationalAirports(String departure) throws IOException{
        String jsonResponse = "{}";

        params = getMapParams();

        params.put("app","international_airline");
        params.put("action","get_airports");

        if(!departure.equalsIgnoreCase(""))
            params.put("departure",departure);


        logger.debug("URL "+url+"; map : "+params.toString());

        IHttpRequester requester = new PostStdRequester();
        jsonResponse = requester.sendRequest(url,params);
        return jsonResponse;
    }


    public TrevoHubTemplateWithArray getDomestics(String departure){
        try{
            return jsonToAirports(reqDomesticAirports(departure));
        }catch (IOException ex){
            dTemplate.setErrorCode(StatusCode.API_TIME_OUT+"");
            dTemplate.setErrorDesc("RTO");

            return dTemplate;
        }

    }

    public TrevoHubTemplateWithArray getInternational(String departure){
        try{
            return jsonToAirports(reqInternationalAirports(departure));
        }catch (IOException ex){
            dTemplate.setErrorCode(StatusCode.API_TIME_OUT+"");
            dTemplate.setErrorDesc("RTO");

            return dTemplate;
        }

    }

    public TrevoHubTemplateWithArray getAllAirports(String departure){
        TrevoHubTemplateWithArray temp1 = getDomestics(departure);
        TrevoHubTemplateWithArray temp2 = getInternational(departure);

        ArrayList<IData> data = new ArrayList<>();
        data.addAll(temp1.getData());
        data.addAll(temp2.getData());

        if(temp1.getErrorCode().equalsIgnoreCase("0") && temp2.getErrorCode().equalsIgnoreCase("0")){

            dTemplate.setErrorCode("0");
            dTemplate.setData(data);
        }else {
            dTemplate.setErrorCode(temp1.getErrorCode()+"|"+temp2.getErrorCode());
            dTemplate.setErrorDesc(temp1.getErrorDesc()+"|"+temp2.getErrorDesc());
            dTemplate.setData(data);
        }

        return dTemplate;


    }


    private TrevoHubTemplateWithArray jsonToAirports(String jsonData){
        JSONObject jsonObject = null;
        ArrayList<Airport> airports = null;

        logger.debug("JSON data : "+jsonData);

        try{
            jsonObject = new JSONObject(jsonData);

            if(!jsonObject.getString("error_no").equalsIgnoreCase("0")){
                dTemplate.setErrorCode(jsonObject.getString("error_no"));
                dTemplate.setErrorDesc(jsonObject.getString("error_msg"));
            }else{
                dTemplate.setErrorCode(jsonObject.getString("error_no"));

                airports = new ArrayList<>();
                JSONArray jsonArray = jsonObject.getJSONArray("airports_data");

                JSONObject jsonAirline = null;
                for(int i=0;i<jsonArray.length();i++){
                    jsonAirline = jsonArray.getJSONObject(i);
                    airport = new Airport();
                    airport.setAirportCode(jsonAirline.getString("airport_code"));
                    airport.setAirportName(jsonAirline.getString("airport_name"));
                    airport.setAirportCity(jsonAirline.getString("airport_city"));
                    airport.setAirportCountry(jsonAirline.getString("airport_country"));
                    airport.setAirportDistrict(jsonAirline.getString("airport_district"));

                    airports.add(airport);
                }

                dTemplate.setData(airports);

            }

        }catch (Exception ex){
            logger.error("error : "+ex.toString());
            ex.printStackTrace();
        }
        return  dTemplate;
    }


}
