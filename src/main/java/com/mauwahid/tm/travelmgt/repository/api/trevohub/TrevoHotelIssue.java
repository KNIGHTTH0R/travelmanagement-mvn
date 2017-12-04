package com.mauwahid.tm.travelmgt.repository.api.trevohub;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelIssueReq;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.*;
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
public class TrevoHotelIssue {
    private Map params = new HashMap<String,String>();

    private String url;

    private Logger logger = LoggerFactory.getLogger(TrevoHotelIssue.class);

    @Autowired
    private TrevoApiCaller trevoApiCaller;

    public HotelIssue issue(Map params) {

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


    private HotelIssue exceptionHandling(Exception ex){

        return null;
    };

    //Translator From JSON

    private HotelIssue translateToObject(String jsonData) {

        HotelHotel hotel = translateToHotel(jsonData);
        HotelCustomer hotelCustomer = translateToCustomer(jsonData);

        HotelIssue hotelIssue = new HotelIssue();
        hotelIssue.setCustomer(hotelCustomer);
        hotelIssue.setHotel(hotel);


        return hotelIssue;

    }


    private HotelHotel translateToHotel(String jsonData) {


        JSONObject objData = null;
        try{
            objData = new JSONObject(jsonData);

        }catch (Exception ex){

        }

        JSONObject objHotel = objData.optJSONObject("hotel");

        HotelHotel hotel = new HotelHotel();
        hotel.setHotelName(objHotel.optString("name"));
        hotel.setAddress(objHotel.optString("address"));
        hotel.setCheckIn(objHotel.optString("check_in"));
        hotel.setCheckOut(objHotel.optString("check_out"));


        JSONObject objRoom = objData.optJSONObject("room");
        HotelRoom hotelRoom = new HotelRoom();
        hotelRoom.setRoomName(objRoom.optString("room"));
        hotelRoom.setRoomType(objRoom.optString("room_type"));
        hotelRoom.setTotalNight(objRoom.optString("total_night"));
        hotelRoom.setTotalRoom(objRoom.optString("total_room"));

        Set<HotelRoom> rooms = new HashSet<>();
        rooms.add(hotelRoom);

     //   hotel.setRooms(rooms);

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

    private HotelCustomer translateToCustomer(String jsonData) {
        HotelCustomer hotelCustomer = new HotelCustomer();

        JSONObject objData = null;

        try{
            objData = new JSONObject(jsonData);
        }catch (Exception ex){
            logger.error("ex "+ex.toString());
        }

        JSONObject objCustomer = objData.optJSONObject("customer");
        hotelCustomer.setCustomerEmail(objCustomer.optString("customer_email"));
        hotelCustomer.setCustomerName(objCustomer.optString("customer_name"));
        hotelCustomer.setCustomerPhone(objCustomer.optString("customer_phone"));

        return hotelCustomer;
    }

    //static method
    public static Map translateToParam(HotelIssueReq hotelIssueReq){
        Map param = new HashMap();

        param.put("b2b_code",TrevoApiCaller.b2bCode);
        param.put("h2h_code", TrevoApiCaller.h2hCode);
        param.put("app","hotel");
        param.put("action", "book");
        //param.put("hotel_data_id", hotelIssueReq.getHotelDataId());
        //param.put("payment_type", hotelIssueReq.getPaymentType());
        //param.put("invoice_no", hotelIssueReq.getInvoiceNo());

        return param;
    }
}
