package com.mauwahid.tm.travelmgt.repository.api.trevohub;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelSearchReq;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.*;
import com.mauwahid.tm.travelmgt.repository.api.pointer.PointerFlightBook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class TrevoHotelSearch {

    private Map params = new HashMap<String,String>();

    private String url;

    private Logger logger = LoggerFactory.getLogger(TrevoHotelSearch.class);

    @Autowired
    private TrevoApiCaller trevoApiCaller;

    public Set<HotelHotel> searchHotel(Map params) {

        String jsonData;

        url = TrevoApiCaller.uri;
        logger.debug("params : "+params);

        try{
            jsonData = trevoApiCaller.callApiPost(url,params);
            logger.debug("JSON RES : "+jsonData);
        }catch (IOException ex){
            logger.error("searchTravel : "+ex.toString());
            return exceptionHandling(ex);
        }

        try{
            return translateToObject(jsonData);
        }catch (Exception ex) {
            logger.error("searchHotel translateToObj : "+ex.toString());

            return exceptionHandling(ex);
        }
    }


    private Set<HotelHotel> exceptionHandling(Exception ex){

        return null;
    };

    //Translator From JSON

    private Set<HotelHotel> translateToObject(String jsonData) throws JSONException {
        HotelHotel hotelHotel = null;
        Set<HotelHotel> hotels = new HashSet<>();

        Set<HotelPricePerRoom> pricePerRooms = null;
        HotelPricePerRoom pricePerRoom = null;

        Set<HotelPriceTotal> priceTotals = null;
        HotelPriceTotal priceTotal = null;

        JSONObject objLocation;
        HotelLocation location = null;

        JSONObject objData = new JSONObject(jsonData);
        JSONArray arrResult = objData.optJSONArray("result");

        JSONObject objTopResult = null;

        JSONArray arrHotels;
        JSONObject objHotel;

        JSONObject objPricePerRoom;
        JSONObject objPriceTotal;

        HotelImage image;
        JSONObject objImage = null;

        JSONArray arrHotelDetail = null;

        String name, city, description;

        for(int i=0;i<arrResult.length();i++){

            objTopResult = arrResult.optJSONObject(i);
            name = objTopResult.optString("name");
            city = objTopResult.optString("city_name");
            description = objTopResult.optString("description");

            arrHotels = objTopResult.optJSONArray("hotel_details");

            objLocation = objTopResult.optJSONObject("location");
            location = new HotelLocation();
            location.setLatitude(objLocation.optString("latitude"));
            location.setLongitude(objLocation.optString("longitude"));
            location.setDescription(objLocation.optString("description"));

            objImage = objTopResult.optJSONObject("image");
            image = new HotelImage();
           // image.setLarge(objImage.optString("large"));
          //  image.setThumbnail(objImage.optString("thumbnail"));

            Set<HotelImage> images = new HashSet<>();
            images.add(image);



            for(int iHotel=0;iHotel<arrHotels.length();iHotel++){
                hotelHotel = new HotelHotel();
                hotelHotel.setSessionId(objData.optString("session_id"));
                hotelHotel.setHotelName(name);
                hotelHotel.setCity(city);
                hotelHotel.setDescription(description);

                objHotel = arrHotels.optJSONObject(iHotel);
                hotelHotel.setHotelKey(objHotel.optString("hotel_key"));
                hotelHotel.setHotelId(objHotel.optString("hotel_id"));
                hotelHotel.setServiceName(objHotel.optString("service_name"));
                hotelHotel.setServiceCode(objHotel.optString("service_code"));
                hotelHotel.setServiceId(objHotel.optString("service_id"));
                hotelHotel.setStars(objHotel.optString("stars"));
                hotelHotel.setRating(objHotel.optString("rating"));

                objPricePerRoom = objHotel.optJSONObject("price_per_room");

                pricePerRoom = new HotelPricePerRoom();
                pricePerRoom.setBasicPrice(objPricePerRoom.optString("basic_price"));
                pricePerRoom.setService(objPricePerRoom.optString("service"));
                pricePerRoom.setB2bCommision(objPricePerRoom.optString("b2b_commission"));
                pricePerRoom.setAgentCommission(objPricePerRoom.optString("agent_commission"));
                pricePerRoom.setVoucherPrice(objPricePerRoom.optString("agent_price"));

                objPriceTotal = objHotel.optJSONObject("price_total");

                priceTotal = new HotelPriceTotal();
                priceTotal.setBasicPrice(objPriceTotal.optString("basic_price"));
                priceTotal.setService(objPriceTotal.optString("service"));
                priceTotal.setB2bCommision(objPriceTotal.optString("b2b_commission"));
                priceTotal.setAgentCommission(objPriceTotal.optString("agent_commission"));
                priceTotal.setVoucherPrice(objPriceTotal.optString("agent_price"));

                pricePerRooms = new HashSet<>();
                pricePerRooms.add(pricePerRoom);

                priceTotals = new HashSet<>();
                priceTotals.add(priceTotal);

                hotelHotel.setLocation(location);
                hotelHotel.setImages(images);
            //    hotelHotel.setPricePerRooms(pricePerRooms);
            //    hotelHotel.setPriceTotals(priceTotals);


                hotels.add(hotelHotel);

            }

        }
        return hotels;

    }


    //static method
    public static Map translateToParam(HotelSearchReq hotelSearchReq){
        Map param = new HashMap();

        param.put("b2b_code",TrevoApiCaller.b2bCode);
        param.put("h2h_code", TrevoApiCaller.h2hCode);
        param.put("app","hotel");
        param.put("action", "search");
        param.put("service_name","all");
      //  param.put("sort", hotelSearchReq.getSort());
        param.put("city", hotelSearchReq.getCity());
      //  param.put("destination_key", hotelSearchReq.getDestinationKey());
        param.put("country_code", hotelSearchReq.getCountryCode());
        param.put("check_in", hotelSearchReq.getCheckIn());
        param.put("check_out", hotelSearchReq.getCheckOut());
     //   param.put("room[0][total_adult]", hotelSearchReq.getTotalAdult());
     //   param.put("room[0][total_child]", hotelSearchReq.getTotalChild());
     //   param.put("limit", hotelSearchReq.getLimit());

        return param;
    }
}
