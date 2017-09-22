package com.mauwahid.tm.travelmgt.service.api.trevohub.hotel;

import com.mauwahid.tm.travelmgt.domain.api.response.CityHotel;
import com.mauwahid.tm.travelmgt.domain.api.response.IData;
import com.mauwahid.tm.travelmgt.domain.api.response.trevohub.TrevoHubTemplateWithArray;
import com.mauwahid.tm.travelmgt.domain.apimodel.Airline;
import com.mauwahid.tm.travelmgt.service.api.IHttpRequester;
import com.mauwahid.tm.travelmgt.service.api.PostStdRequester;
import com.mauwahid.tm.travelmgt.service.api.trevohub.ApiGetAirlines;
import com.mauwahid.tm.travelmgt.service.api.trevohub.TrevoHubRequester;
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
public class ApiGetCityList extends TrevoHubRequester {

    private Map<String,String> params;

    @Autowired
    TrevoHubTemplateWithArray dTemplate;

    Airline airline;

    private static final Logger logger = LoggerFactory.getLogger(ApiGetAirlines.class);

    private String callApi(String city) throws IOException {
        String jsonResponse = "--";

        params = getMapParams();

        params.put("app","hotel");
        params.put("action","city_list");
        params.put("city",city);


        logger.debug("URL "+url+"; map : "+params.toString());

        IHttpRequester requester = new PostStdRequester();
        jsonResponse = requester.sendRequest(url,params);
        return jsonResponse;
    }


    private TrevoHubTemplateWithArray jsonToAirline(String jsonData){
        JSONObject jsonObject = null;
        ArrayList<CityHotel> cityHotels = null;
        CityHotel cityHotel = null;

        logger.debug("JSON data : "+jsonData);

        try{
            jsonObject = new JSONObject(jsonData);

            if(!jsonObject.getString("error_no").equalsIgnoreCase("0")){
                dTemplate.setErrorCode(jsonObject.getString("error_no"));
                dTemplate.setErrorDesc(jsonObject.getString("error_msg"));
            }else{
                dTemplate.setErrorCode(jsonObject.getString("error_no"));

                cityHotels = new ArrayList<>();
                JSONArray jsonArray = jsonObject.getJSONArray("city_search");

                JSONObject jsonCity = null;
                for(int i=0;i<jsonArray.length();i++){
                    jsonCity = jsonArray.getJSONObject(i);
                    cityHotel = new CityHotel();
                    cityHotel.setKey(jsonCity.optString("key"));
                    cityHotel.setDestination(jsonCity.optString("destination"));
                    cityHotel.setType(jsonCity.optString("type"));

                    cityHotels.add(cityHotel);
                }

                dTemplate.setData(cityHotels);

            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  dTemplate;
    }

    public TrevoHubTemplateWithArray getCityList(String city){
        TrevoHubTemplateWithArray trevoHubTemplateWithArray = null;

        try{
            trevoHubTemplateWithArray = jsonToAirline(callApi(city));
        }catch (Exception ex){

        }

        return trevoHubTemplateWithArray;

    }
}
