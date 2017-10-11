package com.mauwahid.tm.travelmgt.repository.api.trevohub;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelDetailReq;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.*;
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
public class TrevoHotelDetail {

    private Map params = new HashMap<String,String>();

    private String url;

    private Logger logger = LoggerFactory.getLogger(TrevoHotelDetail.class);

    @Autowired
    private TrevoApiCaller trevoApiCaller;

    public HotelHotel getDetailHotel(Map params) {

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


    private HotelHotel exceptionHandling(Exception ex){

        return null;
    };

    //Translator From JSON

    private HotelHotel translateToObject(String jsonData) throws JSONException {

        JSONObject objData = new JSONObject(jsonData);
        JSONObject objDetail = objData.optJSONObject("result");

        HotelHotel hotel = new HotelHotel();
        hotel.setHotelId(objDetail.optString("hotel_id"));
        hotel.setServiceId(objDetail.optString("service_id"));
        hotel.setServiceName(objDetail.optString("service_name"));
        hotel.setHotelName(objDetail.optString("name"));
        hotel.setAddress(objDetail.optString("address"));
        hotel.setCity(objDetail.optString("city_name"));


        try{
            HotelAddInfo info = getAddInfo(objDetail.optJSONObject("hotel_info"));
            hotel.setAddInfo(info);
        }catch (Exception ex){

            HotelAddInfo info = new HotelAddInfo();
            info.setErrorCode("2");
            info.setErrorDesc(ex.toString());
            hotel.setAddInfo(info);

        }

        JSONObject objLocation = objDetail.optJSONObject("location_info");
        HotelLocation hotelLocation = new HotelLocation();
        hotelLocation.setLongitude(objLocation.optString("longitude"));
        hotelLocation.setLatitude(objLocation.optString("latitude"));
        hotelLocation.setDescription(objLocation.optString("description"));

        hotel.setLocation(hotelLocation);

        JSONArray arrImages = objDetail.optJSONArray("images");
        hotel.setImages(getHotelImage(arrImages));

        JSONArray arrRoom = objDetail.optJSONArray("rooms");
        hotel.setRooms(getHotelRoom(arrRoom));


        return hotel;
    }


    private HotelAddInfo getAddInfo(JSONObject objInfo){

        HotelAddInfo hotelAddInfo = new HotelAddInfo();
        hotelAddInfo.setRoomInfo(objInfo.optString("room"));
        hotelAddInfo.setArea(objInfo.optString("area"));
        hotelAddInfo.setProperty(objInfo.optString("property"));
        hotelAddInfo.setRoomDetail(objInfo.optString("room_detail"));
        hotelAddInfo.setCheckInInstructions(objInfo.optString("check_in_instructions"));

        JSONArray arrFacilities = objInfo.optJSONArray("facilities");

        try{
            hotelAddInfo.setFacitilies(getFacilities(arrFacilities));

        }catch (Exception ex){
            logger.error("Ex "+ex.toString());
        }

        return hotelAddInfo;

    }


    private Set<String> getFacilities(JSONArray arrFacilities){
        Set<String> setFacilities = new HashSet<>();

        for(int i=0;i<arrFacilities.length();i++){

            try{
                setFacilities.add(arrFacilities.getString(i));
            }catch (Exception e){
                logger.debug("exception "+e.toString());
            }
        }

        return setFacilities;
    }

    private Set<HotelRoom> getHotelRoom(JSONArray arrRooms){

        Set<HotelRoom> rooms = new HashSet<>();
        JSONObject objRoom;
        HotelRoom hotelRoom;

        for(int i =0;i<arrRooms.length();i++){

            try{
                objRoom = arrRooms.getJSONObject(i);
                hotelRoom = new HotelRoom();

                hotelRoom.setRoomId(objRoom.optString("room_id"));
                hotelRoom.setRoomName(objRoom.optString("name"));
                hotelRoom.setDescription(objRoom.optString("description"));
                hotelRoom.setRoomInformation(objRoom.optString("room_information"));
                hotelRoom.setCurrency(objRoom.optString("currency"));

                JSONArray arrImages = objRoom.optJSONArray("images");
                hotelRoom.setImages(getHotelImage(arrImages));

                JSONObject objPriceRoom = objRoom.optJSONObject("price_per_room");

                HotelPricePerRoom pricePerRoom = new HotelPricePerRoom();
                pricePerRoom.setBasicPrice(objPriceRoom.optString("basic_price"));
                pricePerRoom.setService(objPriceRoom.optString("service"));
                pricePerRoom.setB2bCommision(objPriceRoom.optString("b2b_commission"));
                pricePerRoom.setAgentCommission(objPriceRoom.optString("agent_commission"));
                pricePerRoom.setBranchCommisiion(objPriceRoom.optString("branch_commission"));
                pricePerRoom.setVoucherPrice(objPriceRoom.optString("voucher_price"));
                pricePerRoom.setAgentPrice(objPriceRoom.optString("agent_price"));

                hotelRoom.setPricePerRoom(pricePerRoom);

                JSONObject objPrice = objRoom.optJSONObject("price");

                HotelPriceTotal price = new HotelPriceTotal();
                price.setB2bCommision(objPrice.optString("b2b_commission"));
                price.setAgentCommission(objPrice.optString("agent_commission"));
                price.setBranchCommisiion(objPrice.optString("branch_commission"));
                price.setVoucherPrice(objPrice.optString("voucher_price"));
                price.setAgentPrice(objPrice.optString("agent_price"));

                JSONArray arrFac = objRoom.optJSONArray("facility");

                try{
                    hotelRoom.setFacilities(getFacilities(arrFac));
                }catch (Exception ex){
                    logger.error("ex "+ex.toString());
                }

                JSONArray arrSmook = objRoom.optJSONArray("smoking_pref");

                try{
                    hotelRoom.setSmookingPrefs(getSmookingPref(arrSmook));
                }catch (Exception ex){
                    logger.error("ex "+ex.toString());
                }

                JSONArray arrBedType = objRoom.optJSONArray("bed_types");

                try{
                    hotelRoom.setBedTypes(getBedTypes(arrBedType));
                }catch (Exception ex){
                    logger.error("ex "+ex.toString());
                }


                hotelRoom.setPrice(price);

                rooms.add(hotelRoom);
            }catch (Exception ex){
                logger.error("err : "+ex.toString());
            }

        }

        return rooms;


    }


    private Set<HotelImage> getHotelImage(JSONArray arrImages){
        Set<HotelImage> images = new HashSet<>();
        JSONObject objImage;
        HotelImage image;

        for(int i =0;i<arrImages.length();i++){
            try{
                objImage = arrImages.getJSONObject(i);

                image = new HotelImage();
                image.setLarge(objImage.optString("large"));
                image.setThumbnail(objImage.optString("thumbnail"));
                image.setDescription(objImage.optString("description"));

                images.add(image);
            }catch (Exception e){
                logger.error("error : "+e.toString()+ " "+e.getMessage());
            }

        }

        return images;
    }


    private Set<HotelBedType> getBedTypes(JSONArray arrBedTypes){
        HotelBedType hotelBedType = null;
        Set<HotelBedType> hotelBedTypes = new HashSet<>();

        JSONObject objBed;

        for(int i=0;i<arrBedTypes.length();i++){
            hotelBedType = new HotelBedType();
            objBed = arrBedTypes.optJSONObject(i);

            hotelBedType.setKey(objBed.optString("key"));
            hotelBedType.setDesc(objBed.optString("desc"));

            hotelBedTypes.add(hotelBedType);
        }

        return hotelBedTypes;
    }

    private Set<HotelSmookingPref> getSmookingPref(JSONArray arrBedTypes){
        HotelSmookingPref hotelSmookingPref = null;
        Set<HotelSmookingPref> hotelSmookingPrefs = new HashSet<>();

        JSONObject objBed;

        for(int i=0;i<arrBedTypes.length();i++){
            hotelSmookingPref = new HotelSmookingPref();
            objBed = arrBedTypes.optJSONObject(i);

            hotelSmookingPref.setKey(objBed.optString("key"));
            hotelSmookingPref.setDesc(objBed.optString("desc"));

            hotelSmookingPrefs.add(hotelSmookingPref);
        }

        return hotelSmookingPrefs;
    }

    //static method
    public static Map translateToParam(HotelDetailReq hotelDetailReq){
        Map param = new HashMap();

        param.put("b2b_code",TrevoApiCaller.b2bCode);
        param.put("h2h_code", TrevoApiCaller.h2hCode);
        param.put("app","hotel");
        param.put("action", "detail");
        param.put("session_id", hotelDetailReq.getSessionId());
        param.put("hotel_key", hotelDetailReq.getHotelKey());
        param.put("hotel_id", hotelDetailReq.getHotelId());
        param.put("service_id", hotelDetailReq.getServiceId());

        return param;
    }
}
