package com.mauwahid.tm.travelmgt.repository.api.astrindo;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelChangePriceReq;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelChangePriceResult;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.reservation.*;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.HotelChangePriceInterface;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
@Qualifier("astri_hotel_change_price")
public class AstriHotelChangePrice implements HotelChangePriceInterface {

    private String url;


    @Autowired
    private AstriApiCaller astriApiCaller;


    public HotelChangePriceResult changePrice(Map params) {

        String jsonData;

        url = AstriApiCaller.uri;
        log.debug("params : "+params);

        try{
            jsonData = astriApiCaller.callApiGet(url,params);
            log.debug("JSON RES : "+jsonData);
        }catch (IOException ex){
            log.error("searchTravel : "+ex.toString());
            return exceptionHandling(ex);
        }

        try{
            return translateToObject(jsonData);
        }catch (Exception ex) {
            log.error("searchHotel translateToObj : "+ex.toString());

            return exceptionHandling(ex);
        }
    }


    private HotelChangePriceResult exceptionHandling(Exception ex){

        return null;
    };

    //Translator From JSON

    private HotelChangePriceResult translateToObject(String jsonData) throws JSONException {
        HotelChangePriceResult hotelChangePriceResult = new HotelChangePriceResult();

        JSONObject objData = new JSONObject(jsonData);
        JSONObject objResHotel = objData.optJSONObject("ReservationHotels");



        hotelChangePriceResult.setMessageCode(objData.optString("MessageCode"));
        hotelChangePriceResult.setMessageDesc(objData.optString("MessageDesc"));
        hotelChangePriceResult.setReservationID(objData.optString("ReservationID"));
        hotelChangePriceResult.setMerchantID(objData.optString("MerchantID"));
        hotelChangePriceResult.setReservationCode(objData.optString("ReservationCode"));
        hotelChangePriceResult.setMemberID(objData.optString("MemberID"));
        hotelChangePriceResult.setItineraryName(objData.optString("ItineraryName"));
        hotelChangePriceResult.setProductType(objData.optString("ProductType"));
        hotelChangePriceResult.setEmailAlert(objData.optString("EmailAlert"));
        hotelChangePriceResult.setStatusCode(objData.optString("StatusCode"));
        hotelChangePriceResult.setCreatedIPAddress(objData.optString("CreatedIPAddress"));
        hotelChangePriceResult.setLastUpdatedIPAddress(objData.optString("LastUpdatedIPAddress"));
        // hotelChangePriceResult.setReservationCust

        // reservation customer
        JSONObject objCustomer = objData.optJSONObject("ReservationCustomers");
        HotelReservationCustomer hotelReservationCustomer = new HotelReservationCustomer();

        hotelReservationCustomer.setActive(objCustomer.optString("Active"));
        hotelReservationCustomer.setReservationCustomerID(objCustomer.optString("ReservationCustomerID"));
        hotelReservationCustomer.setReservationID(objCustomer.optString("ReservationID"));
        hotelReservationCustomer.setTitle(objCustomer.optString("Title"));
        hotelReservationCustomer.setFirstName(objCustomer.optString("FirstName"));
        hotelReservationCustomer.setLastName(objCustomer.optString("LastName"));
        hotelReservationCustomer.setEmail(objCustomer.optString("Email"));
        hotelReservationCustomer.setContactNumber(objCustomer.optString("ContactNumber"));
        hotelReservationCustomer.setAddress(objCustomer.optString("Address"));
        hotelReservationCustomer.setCreatedBy(objCustomer.optString("CreatedBy"));
        hotelReservationCustomer.setLastUpdatedBy(objCustomer.optString("LastUpdatedBy"));
        hotelReservationCustomer.setLastUpdatedTime(objCustomer.optString("LastUpdatedTime"));
        hotelReservationCustomer.setActive(objCustomer.optString("Active"));

        hotelChangePriceResult.setHotelReservationCustomer(hotelReservationCustomer);

        //

        HotelReservationHotel reservationHotel = new HotelReservationHotel();

        reservationHotel.setReservationHotelID(objResHotel.optString("ReservationHotelID"));
        reservationHotel.setReservationID(objResHotel.optString("ReservationID"));
        reservationHotel.setHotelID(objResHotel.optString("HotelID"));
        reservationHotel.setHotelName(objResHotel.optString("HotelName"));
        reservationHotel.setHotelAddress(objResHotel.optString("HotelAddress"));
        reservationHotel.setHotelPhone(objResHotel.optString("HotelPhone"));
        reservationHotel.setSuppHotelCode(objResHotel.optString("SuppHotelCode"));
        reservationHotel.setSupplierCode(objResHotel.optString("SupplierCode"));
        reservationHotel.setCheckInDate(objResHotel.optString("CheckInDate"));
        reservationHotel.setCheckOutDate(objResHotel.optString("CheckOutDate"));
        reservationHotel.setBookingStatus(objResHotel.optString("BookingSttus"));
        reservationHotel.setTimeLimit(objResHotel.optString("TimeLimit"));
        reservationHotel.setConfirmationCode(objResHotel.optString("ConfirmationCode"));
        reservationHotel.setCancellationCode(objResHotel.optString("CancellationCode"));
        reservationHotel.setVoucherNo(objResHotel.optString("VoucherNo"));
        reservationHotel.setCreatedBy(objResHotel.optString("CreatedBy"));
        reservationHotel.setCreatedTime(objResHotel.optString("CreatedTime"));
        reservationHotel.setCreatedIPAddress(objResHotel.optString("CreatedIPAddress"));
        reservationHotel.setLastUpdatedBy(objResHotel.optString("LastUpdatedBy"));
        reservationHotel.setLastUpdatedTime(objResHotel.optString("LastUpdatedTime"));
        reservationHotel.setActive(objResHotel.optString("Active"));


        // Hotel Resrvation remarks

        JSONObject objReservationHotelRemarks = objResHotel.optJSONObject("ReservationHotelRemarks");
        HotelReservationRemarks hotelReservationRemarks = new HotelReservationRemarks();

        hotelReservationRemarks.setRemarks(objReservationHotelRemarks.optString("Remarks"));
        hotelReservationRemarks.setReservationHotelID(objReservationHotelRemarks.optString("ReservationHotelID"));
        hotelReservationRemarks.setReservationHotelRemarksID(objReservationHotelRemarks.optString("ReservationHotelRemarksID"));

        reservationHotel.setHotelReservationRemarks(hotelReservationRemarks);


        //Hotel Room List
        JSONArray arrRooms = objResHotel.optJSONArray("ReservationHotelRoomList");
        JSONObject objRoom = null;

        HotelReservationRoom hotelReservationRoom;
        Set<HotelReservationRoom> reservationRooms = new HashSet<>();


        for(int i=0;i<arrRooms.length();i++){
            objRoom = arrRooms.optJSONObject(i);
            hotelReservationRoom = new HotelReservationRoom();

            hotelReservationRoom.setReservationHotelID(objRoom.optString("ReservationHotelID"));
            hotelReservationRoom.setReservationHotelRoomID(objRoom.optString("ReservationHotelRoomID"));
            hotelReservationRoom.setRoomType(objRoom.optString("RoomType"));
            hotelReservationRoom.setRoomName(objRoom.optString("RoomName"));
            hotelReservationRoom.setBoardName(objRoom.optString("BoardName"));
            hotelReservationRoom.setSuppRoomID(objRoom.optString("SuppRoomID"));
            hotelReservationRoom.setTotalRoom(objRoom.optString("TotalRoom"));

            reservationRooms.add(hotelReservationRoom);

        }

        reservationHotel.setHotelReservationRooms(reservationRooms);

        JSONArray arrHotelFareList = objResHotel.optJSONArray("ReservationHotelFareList");
        JSONObject objHotelFare = null;
        HotelReservationFare hotelReservationFare;
        Set<HotelReservationFare> hotelReservationFareSet = new HashSet<>();

        for(int y=0;y<arrHotelFareList.length();y++){
            objHotelFare = arrHotelFareList.optJSONObject(y);

            hotelReservationFare = new HotelReservationFare();
            hotelReservationFare.setReservationHotelFareID(objHotelFare.optString("ReservationHotelFareID"));
            hotelReservationFare.setReservationHotelRoomID(objHotelFare.optString("ReservationHotelRoomID"));
            hotelReservationFare.setCurrCode(objHotelFare.optString("CurrCode"));
            hotelReservationFare.setSellCurrCode(objHotelFare.optString("SellCureCode"));
            hotelReservationFare.setPrice(objHotelFare.optString("Price"));
            hotelReservationFare.setPriceConverted(objHotelFare.optString("PriceConverted"));
            hotelReservationFare.setServiceFee(objHotelFare.optString("ServiceFee"));
            hotelReservationFare.setDiscount(objHotelFare.optString("Discount"));
            hotelReservationFare.setPaymentCurrCode(objHotelFare.optString("PaymentCurrCode"));
            hotelReservationFare.setPaymentAmount(objHotelFare.optString("PaymentAmount"));
            hotelReservationFare.setActive(objHotelFare.optString("Active"));


            hotelReservationFareSet.add(hotelReservationFare);

        }

        reservationHotel.setHotelReservationFares(hotelReservationFareSet);


        JSONArray arrCancellationPolicy = objResHotel.optJSONArray("ReservationHotelCancellationPolicyList");
        JSONObject objCancellationPolicy = null;
        HotelReservationCancelationPolicy reservationCancelationPolicy;
        Set<HotelReservationCancelationPolicy> reservationCancelationPolicies = new HashSet<>();

        for(int z=0;z<arrCancellationPolicy.length();z++){
            objCancellationPolicy = arrCancellationPolicy.getJSONObject(z);

            reservationCancelationPolicy = new HotelReservationCancelationPolicy();
            reservationCancelationPolicy.setReservationHotelCancelPolicyId(objCancellationPolicy.optString("ReservationHotelCancellationPolicyID"));
            reservationCancelationPolicy.setReservationHotelID(objCancellationPolicy.optString("ReservationHotelID"));
            reservationCancelationPolicy.setReservationHotelRoomID(objCancellationPolicy.optString("ReservationHotelRoomID"));
            reservationCancelationPolicy.setFromDate(objCancellationPolicy.optString("FromDate"));
            reservationCancelationPolicy.setToDate(objCancellationPolicy.optString("ToDate"));
            reservationCancelationPolicy.setCurrCode(objCancellationPolicy.optString("CurrCode"));
            reservationCancelationPolicy.setSellCurrCode(objCancellationPolicy.optString("SellCurrCode"));
            reservationCancelationPolicy.setChargeAmount(objCancellationPolicy.optString("ChargeAmount"));
            reservationCancelationPolicy.setChargeAmountConverted(objCancellationPolicy.optString("ChargeAmountConverted"));
            reservationCancelationPolicy.setDescription(objCancellationPolicy.optString("Description"));

            reservationCancelationPolicies.add(reservationCancelationPolicy);
        }

        reservationHotel.setHotelReservationCancelationPolicies(reservationCancelationPolicies);


        hotelChangePriceResult.setHotelReservationHotel(reservationHotel);


        return hotelChangePriceResult;

    }


    //static method
    public static Map translateToParam(HotelChangePriceReq hotelChangePriceReq){
        Map param = new HashMap();

        param.put("username",AstriApiCaller.USERNAME);
        param.put("password", AstriApiCaller.PASSWORD);
        param.put("orgCode", AstriApiCaller.ORGCODE);
        param.put("merchantID", AstriApiCaller.MERCHANTID);

        param.put("trxNo", hotelChangePriceReq.getTrxNo());


        return param;
    }
}
