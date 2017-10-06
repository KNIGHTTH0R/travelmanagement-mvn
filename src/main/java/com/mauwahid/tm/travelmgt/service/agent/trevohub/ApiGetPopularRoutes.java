package com.mauwahid.tm.travelmgt.service.agent.trevohub;

import com.mauwahid.tm.travelmgt.domain.api.old.IData;
import com.mauwahid.tm.travelmgt.domain.api.response.trevohub.TrevoHubTemplateWithArray;
import com.mauwahid.tm.travelmgt.domain.apimodel.old.PopularRoute;
import com.mauwahid.tm.travelmgt.repository.api.resthub.IHttpRequester;
import com.mauwahid.tm.travelmgt.repository.api.resthub.PostStdRequester;
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
public class ApiGetPopularRoutes extends TrevoHubRequester {

    private Map<String,String> params;

    @Autowired
    TrevoHubTemplateWithArray dTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ApiGetAirports.class);

    private String reqDomesticRoute() throws IOException {
        String jsonResponse = "{}";

        params = getMapParams();

        params.put("app","domestic_airline");
        params.put("action","get_popular_routes");

        logger.debug("URL "+url+"; map : "+params.toString());

        IHttpRequester requester = new PostStdRequester();
        jsonResponse = requester.sendRequest(url,params);
        return jsonResponse;
    }


    private String reqInternationalRoutes() throws IOException{
        String jsonResponse = "{}";

        params = getMapParams();

        params.put("app","international_airline");
        params.put("action","get_popular_routes");

        logger.debug("URL "+url+"; map : "+params.toString());

        IHttpRequester requester = new PostStdRequester();
        jsonResponse = requester.sendRequest(url,params);
        return jsonResponse;
    }


    public TrevoHubTemplateWithArray getDomestics(){
        try{
            return jsonToAirports(reqDomesticRoute());
        }catch (IOException ex){
            dTemplate.setErrorCode(StatusCode.API_TIME_OUT+"");
            dTemplate.setErrorDesc("RTO");

            return dTemplate;
        }

    }

    public TrevoHubTemplateWithArray getInternational(){
        try{
            return jsonToAirports(reqInternationalRoutes());
        }catch (IOException ex){
            dTemplate.setErrorCode(StatusCode.API_TIME_OUT+"");
            dTemplate.setErrorDesc("RTO");

            return dTemplate;
        }

    }

    public TrevoHubTemplateWithArray getAllRoutes(){
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


    private TrevoHubTemplateWithArray jsonToAirports(String jsonData){
        JSONObject jsonObject = null;
        ArrayList<PopularRoute> popularRoutes = null;

        logger.debug("JSON data : "+jsonData);

        try{
            jsonObject = new JSONObject(jsonData);

            if(!jsonObject.getString("error_no").equalsIgnoreCase("0")){
                dTemplate.setErrorCode(jsonObject.getString("error_no"));
                dTemplate.setErrorDesc(jsonObject.getString("error_msg"));
            }else{
                dTemplate.setErrorCode(jsonObject.getString("error_no"));

                popularRoutes = new ArrayList<PopularRoute>();

                JSONArray jsonPopRoutes = jsonObject.getJSONArray("popular_routes");
                PopularRoute popularRoute;


                for(int i=0;i<jsonPopRoutes.length();i++){
                    JSONObject jsonRoute = new JSONObject(jsonPopRoutes.getJSONObject(i));

                    popularRoute = new PopularRoute();
                    popularRoute.setCount(jsonRoute.getString("count"));
                    popularRoute.setFrom(jsonRoute.getString("from"));
                    popularRoute.setFromAirport(jsonRoute.getString("from_airport"));
                    popularRoute.setFromCity(jsonRoute.getString("from_city"));
                    popularRoute.setToAirport(jsonRoute.getString("to_airport"));
                    popularRoute.setToCity(jsonRoute.getString("to_city"));

                    popularRoutes.add(popularRoute);
                }

                dTemplate.setData(popularRoutes);


            }

        }catch (Exception ex){
            logger.error("error : "+ex.toString());
            ex.printStackTrace();
        }
        return  dTemplate;
    }

}
