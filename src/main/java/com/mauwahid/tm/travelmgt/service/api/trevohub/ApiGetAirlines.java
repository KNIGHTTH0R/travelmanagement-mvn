package com.mauwahid.tm.travelmgt.service.api.trevohub;

import com.mauwahid.tm.travelmgt.domain.apimodel.Airline;
import com.mauwahid.tm.travelmgt.domain.api.response.IData;
import com.mauwahid.tm.travelmgt.domain.api.response.trevohub.TrevoHubTemplateWithArray;
import com.mauwahid.tm.travelmgt.service.api.IHttpRequester;
import com.mauwahid.tm.travelmgt.service.api.PostStdRequester;
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
public class ApiGetAirlines extends TrevoHubRequester {

    private Map<String,String> params;

    @Autowired
    TrevoHubTemplateWithArray dTemplate;

     Airline airline;

    private static final Logger logger = LoggerFactory.getLogger(ApiGetAirlines.class);

    private String reqDomesticAirlines() throws IOException{
         String jsonResponse = "--";

         params = getMapParams();

         params.put("app","domestic_airline");
         params.put("action","get_airlines");


         logger.debug("URL "+url+"; map : "+params.toString());

         IHttpRequester requester = new PostStdRequester();
         jsonResponse = requester.sendRequest(url,params);
         return jsonResponse;
    }


    private String reqInternationalAirlines() throws IOException{
        String jsonResponse = "--";

        params = getMapParams();

        params.put("app","international_airline");
        params.put("action","get_airlines");


        logger.debug("URL "+url+"; map : "+params.toString());

        IHttpRequester requester = new PostStdRequester();
        jsonResponse = requester.sendRequest(url,params);
        return jsonResponse;
    }


    public TrevoHubTemplateWithArray getDomestics(){
        try{
            return jsonToAirline(reqDomesticAirlines());
        }catch (IOException ex){
            dTemplate.setErrorCode(StatusCode.API_TIME_OUT+"");
            dTemplate.setErrorDesc("RTO");

            return dTemplate;
        }

    }

    public TrevoHubTemplateWithArray getInternational(){
        try{
            return jsonToAirline(reqInternationalAirlines());
        }catch (IOException ex){
            dTemplate.setErrorCode(StatusCode.API_TIME_OUT+"");
            dTemplate.setErrorDesc("RTO");

            return dTemplate;
        }

    }

    public TrevoHubTemplateWithArray getAllAirlines(){
        TrevoHubTemplateWithArray temp1 = getDomestics();
        TrevoHubTemplateWithArray temp2 = getInternational();

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


    private TrevoHubTemplateWithArray jsonToAirline(String jsonData){
        JSONObject jsonObject = null;
        ArrayList<Airline> airlines = null;

        logger.debug("JSON data : "+jsonData);

        try{
            jsonObject = new JSONObject(jsonData);

            if(!jsonObject.getString("error_no").equalsIgnoreCase("0")){
                dTemplate.setErrorCode(jsonObject.getString("error_no"));
                dTemplate.setErrorDesc(jsonObject.getString("error_msg"));
            }else{
                dTemplate.setErrorCode(jsonObject.getString("error_no"));

                airlines = new ArrayList<>();
                JSONArray jsonArray = jsonObject.getJSONArray("airlines_data");

                JSONObject jsonAirline = null;
                for(int i=0;i<jsonArray.length();i++){
                    jsonAirline = jsonArray.getJSONObject(i);
                    airline = new Airline();
                    airline.setAirlinesId(jsonAirline.getString("airlines_id"));
                    airline.setAirlinesCode(jsonAirline.getString("airlines_code"));
                    airline.setAirlinesName(jsonAirline.getString("airlines_name"));
                    airline.setConnectionStatus(jsonAirline.getString("connection_status"));

                    airlines.add(airline);
                }

                dTemplate.setData(airlines);

            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  dTemplate;
    }
}
