package com.mauwahid.tm.travelmgt.service.agent.trevohub;

import com.mauwahid.tm.travelmgt.domain.api.response.*;
import com.mauwahid.tm.travelmgt.domain.api.response.trevohub.TrevoHubTemplateWithArray;
import com.mauwahid.tm.travelmgt.domain.apimodel.Airline;
import com.mauwahid.tm.travelmgt.domain.apimodel.Arrival;
import com.mauwahid.tm.travelmgt.domain.apimodel.Departure;
import com.mauwahid.tm.travelmgt.domain.apimodel.Route;
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
public class ApiGetRoutes extends TrevoHubRequester {

    private Map<String,String> params;

    @Autowired
    TrevoHubTemplateWithArray dTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ApiGetAirports.class);

    private String reqDomesticRoute(String airportCode) throws IOException {
        String jsonResponse = "{}";

        params = getMapParams();

        params.put("app","domestic_airline");
        params.put("action","get_routes");
        params.put("airport_code",airportCode);

        logger.debug("URL "+url+"; map : "+params.toString());

        IHttpRequester requester = new PostStdRequester();
        jsonResponse = requester.sendRequest(url,params);
        return jsonResponse;
    }


    private String reqInternationalRoutes(String airportCode) throws IOException{
        String jsonResponse = "{}";

        params = getMapParams();

        params.put("app","international_airline");
        params.put("action","get_routes");
        params.put("airport_code",airportCode);

        logger.debug("URL "+url+"; map : "+params.toString());

        IHttpRequester requester = new PostStdRequester();
        jsonResponse = requester.sendRequest(url,params);
        return jsonResponse;
    }


    public TrevoHubTemplateWithArray getDomestics(String airportCode){
        try{
            return jsonToAirports(reqDomesticRoute(airportCode));
        }catch (IOException ex){
            dTemplate.setErrorCode(StatusCode.API_TIME_OUT+"");
            dTemplate.setErrorDesc("RTO");

            return dTemplate;
        }

    }

    public TrevoHubTemplateWithArray getInternational(String airportCode){
        try{
            return jsonToAirports(reqInternationalRoutes(airportCode));
        }catch (IOException ex){
            dTemplate.setErrorCode(StatusCode.API_TIME_OUT+"");
            dTemplate.setErrorDesc("RTO");

            return dTemplate;
        }

    }

    public TrevoHubTemplateWithArray getAllRoutes(String airportCode){
        TrevoHubTemplateWithArray temp1 = getDomestics(airportCode);
        TrevoHubTemplateWithArray temp2 = getInternational(airportCode);

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
        ArrayList<Arrival> arrivals = null;
        ArrayList<Airline> airlines = null;

        Route route = null;
        Departure departure = null;
        Arrival arrival = null;
        Airline airline = null;

        logger.debug("JSON data : "+jsonData);

        try{
            jsonObject = new JSONObject(jsonData);

            if(!jsonObject.getString("error_no").equalsIgnoreCase("0")){
                dTemplate.setErrorCode(jsonObject.getString("error_no"));
                dTemplate.setErrorDesc(jsonObject.getString("error_msg"));
            }else{
                dTemplate.setErrorCode(jsonObject.getString("error_no"));

                arrivals = new ArrayList<>();

                JSONObject jsonDeparture = jsonObject.getJSONObject("departure");
                JSONArray jsonArrivals = jsonObject.getJSONArray("arrival");
                JSONObject jsonAirline = null;


                departure = new Departure();
                departure.setDepartureAirport(jsonDeparture.getString("departure_airport"));
                departure.setDepartureAirportName(jsonDeparture.getString("departure_airport_name"));
                departure.setDepartureCity(jsonDeparture.getString("departure_city"));
                departure.setDepartureDistrict(jsonDeparture.getString("departure_district"));
                departure.setDepartureCountry(jsonDeparture.getString("departure_country"));

                for(int i=0;i<jsonArrivals.length();i++){
                    JSONObject jsonArrival = new JSONObject(jsonArrivals.getJSONObject(i));

                    arrival = new Arrival();
                    arrival.setArrivalAirport(jsonArrival.getString("arrival_airport"));
                    arrival.setArrivalAirportName(jsonArrival.getString("arrival_airport_name"));
                    arrival.setArrivalCity(jsonArrival.getString("arrival_city"));
                    arrival.setArrivalDistrict(jsonArrival.getString("arrival_district"));
                    arrival.setArrivalCountry(jsonArrival.getString("arrival_country"));

                    JSONArray jsonArrAirline = jsonArrival.getJSONArray("available_airline");
                    for(int j=0;j<jsonArrAirline.length();j++){
                        jsonAirline = jsonArrAirline.getJSONObject(j);

                        airline = new Airline();
                        airline.setAirlinesId(jsonAirline.getString("airline_code"));
                        airline.setAirlinesName(jsonAirline.getString("airline_name"));
                    }

                }


            }

        }catch (Exception ex){
            logger.error("error : "+ex.toString());
            ex.printStackTrace();
        }
        return  dTemplate;
    }

}
