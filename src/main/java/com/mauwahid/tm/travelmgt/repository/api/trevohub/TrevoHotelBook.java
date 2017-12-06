package com.mauwahid.tm.travelmgt.repository.api.trevohub;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.*;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelBookReq;
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
public class TrevoHotelBook{

    private Map params = new HashMap<String,String>();

    private String url;

    private Logger logger = LoggerFactory.getLogger(TrevoHotelSearch.class);

    @Autowired
    private TrevoApiCaller trevoApiCaller;

    public HotelBookInfo bookHotel(Map params) {

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


    private HotelBookInfo exceptionHandling(Exception ex){

        return null;
    };

    //Translator From JSON

    private HotelBookInfo translateToObject(String jsonData) throws JSONException {

        HotelHotel hotel = translateToHotel(jsonData);
        HotelBookResultOld bookResult = translateToResultInfo(jsonData);
        HotelBookInfo hotelBookInfo = new HotelBookInfo();

        hotelBookInfo.setBookResult(bookResult);
        hotelBookInfo.setHotelHotel(hotel);

        JSONObject objData = new JSONObject(jsonData);

        hotelBookInfo.setHotelDataId(objData.optString("hotel_data_id"));

        return hotelBookInfo;

    }


    private HotelHotel translateToHotel(String jsonData) throws JSONException{

        JSONObject objData = new JSONObject(jsonData);
        JSONObject objHotel = objData.optJSONObject("hotel_info");

        HotelHotel hotel = new HotelHotel();
        hotel.setHotelName(objHotel.optString("hotel_name"));
        hotel.setStars(objHotel.optString("hotel_stars"));
        hotel.setAddress(objHotel.optString("hotel_address"));

        HotelImage image = new HotelImage();
      //  image.setLarge(objHotel.optString("hotel_img"));

        Set<HotelImage> images = new HashSet<>();
        images.add(image);
        hotel.setImages(images);

        JSONObject objRoom = objData.optJSONObject("room_info");
        HotelRoom hotelRoom = new HotelRoom();
        hotelRoom.setRoomName(objRoom.optString("room_name"));
        hotelRoom.setRoomCapacity(objRoom.optString("room_capacity"));
        hotelRoom.setRoomInformation(objRoom.optString("room_information"));

        Set<HotelRoom> rooms = new HashSet<>();
        rooms.add(hotelRoom);

   //     hotel.setRooms(rooms);

        JSONObject objPrice = objData.optJSONObject("price");
        HotelPricePerRoom priceRoom = new HotelPricePerRoom();
        priceRoom.setB2bCommision(objPrice.optString("b2b_commission"));
        priceRoom.setAgentCommission(objPrice.optString("agent_commission"));
        priceRoom.setBranchCommisiion(objPrice.optString("branch_commission"));
        priceRoom.setVoucherPrice(objPrice.optString("voucher_price"));
        priceRoom.setAgentPrice(objPrice.optString("agent_price"));
        priceRoom.setMargin(objPrice.optString("margin"));
        priceRoom.setCustomerPrice(objPrice.optString("customer_price"));

        Set<HotelPricePerRoom> pricePerRooms = new HashSet<>();
        pricePerRooms.add(priceRoom);

     //   hotel.setPricePerRooms(pricePerRooms);

        return hotel;
    }

    private HotelBookResultOld translateToResultInfo(String jsonData) throws JSONException{

        JSONObject objData = new JSONObject(jsonData);
        HotelBookResultOld hotelBookResultOld = new HotelBookResultOld();

        JSONObject objResult = objData.optJSONObject("result");

        hotelBookResultOld.setBookId(objResult.optString("book_id"));
        hotelBookResultOld.setHotelId(objResult.optString("hotel_id"));
        hotelBookResultOld.setRoomId(objResult.optString("room_id"));
        hotelBookResultOld.setRoomQuality(objResult.optString("room_quality"));
        hotelBookResultOld.setNight(objResult.optString("night"));
        hotelBookResultOld.setCheckIn(objResult.optString("check_in"));
        hotelBookResultOld.setCheckOut(objResult.optString("check_out"));
        hotelBookResultOld.setCurrencyCode(objResult.optString("currency_code"));
        hotelBookResultOld.setBookingDate(objResult.optString("booking_date"));
        hotelBookResultOld.setCustomerName(objResult.optString("customer_name"));
        hotelBookResultOld.setCustomerEmail(objResult.optString("customer_email"));
        hotelBookResultOld.setCustomerPhone(objResult.optString("customer_phone"));
        hotelBookResultOld.setStatus(objResult.optString("status"));
        hotelBookResultOld.setTimeLimit(objResult.optString("time_limit"));


        JSONObject objPrice = objResult.optJSONObject("price_per_room");
        HotelPricePerRoom pricePerRoom = new HotelPricePerRoom();

        pricePerRoom.setPublishRate(objPrice.optString("publish_rate"));
        pricePerRoom.setBasicPrice(objPrice.optString("basic_price"));
        pricePerRoom.setTaxService(objPrice.optString("tax_service"));
        pricePerRoom.setB2bCommision(objPrice.optString("b2b_commission"));
        pricePerRoom.setAgentCommission(objPrice.optString("agent_commission"));
        pricePerRoom.setBranchCommisiion(objPrice.optString("branch_commission"));
        pricePerRoom.setVoucherPrice(objPrice.optString("voucher_price"));
        pricePerRoom.setAgentPrice(objPrice.optString("agent_price"));

        hotelBookResultOld.setPricePerRoom(pricePerRoom);


        return hotelBookResultOld;
    }


    //static method
    public static Map translateToParam(HotelBookReq hotelBookReq){
        Map param = new HashMap();

        param.put("b2b_code",TrevoApiCaller.b2bCode);
        param.put("h2h_code", TrevoApiCaller.h2hCode);
        param.put("app","hotel");
        param.put("action", "book");
   /*     param.put("session_id", hotelBookReq.getHotelSessionId());
        param.put("room_id", hotelBookReq.getRoomId());
        param.put("bed_type_id", hotelBookReq.getBedTypeId());
        param.put("smooking_pref", hotelBookReq.getSmookingPref());
        param.put("customer[first_name]", hotelBookReq.getCustomerFirstName());
        param.put("customer[last_name]", hotelBookReq.getCustomerLastName());
        param.put("room[first_name]", hotelBookReq.getCustomerFirstName());
        param.put("room[last_name]", hotelBookReq.getCustomerLastName());
        param.put("customer[email]", hotelBookReq.getCustomerEmail());
        param.put("customer[id_number]", hotelBookReq.getCustomerIdNumber());
        param.put("external_service", hotelBookReq.getExternalService());
      */
     // param.put("margin", hotelBookReq.getMargin());

        return param;
    }
}

