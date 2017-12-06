package com.mauwahid.tm.travelmgt.repository.api.astrindo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelBookReq;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelCancelPolicyReq;
import com.mauwahid.tm.travelmgt.domain.api.request.hotelbook.CustomerData;
import com.mauwahid.tm.travelmgt.domain.api.request.hotelbook.PaxData;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelCancelPolicyResponse;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelBookResult;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelCancelPolicy;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.reservation.*;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.HotelBookInterface;
import com.mauwahid.tm.travelmgt.service.integrator.HotelCancelPolicyService;
import com.mauwahid.tm.travelmgt.utils.ApiStatic;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
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
@Qualifier("astri_hotel_book")
public class AstriHotelBook  implements HotelBookInterface{

    private String url;

    @Autowired
    private LogErrorHelper logErrorHelper;


    @Autowired
    private AstriApiCaller astriApiCaller;

    @Autowired
    private HotelCancelPolicyService hotelCancelPolicyService;


    public HotelBookResult bookHotel(Map params) {

        String jsonData = "";


        if(!isCanbeBooked(params)){
            HotelBookResult hotelBookResult = new HotelBookResult();
            hotelBookResult.setStatusCode(StatusCode.STATUS_BOOK_ERROR_NULL_OR_FAILED_POLICY);
            hotelBookResult.setMessageDesc(StatusCode.S_STATUS_BOOK_ERROR_NULL_OR_FAILED_POLICY);

            return hotelBookResult;
        }


        url = AstriApiCaller.uri+"HotelBooking.aspx";
        log.debug("params : "+params);

        try{
            jsonData = astriApiCaller.callApiGet(url,params);
            log.debug("JSON RES : "+jsonData);
        }catch (IOException ex){
            log.error("searchTravel : "+ex.toString());
            return exceptionHandling(ex, params.toString(), jsonData);
        }

        try{
            return translateToObject(jsonData);
        }catch (Exception ex) {
            log.error("searchHotel translateToObj : "+ex.toString());

            return exceptionHandling(ex, params.toString(), jsonData);
        }
    }


    private HotelBookResult exceptionHandling(Exception ex, String params, String jsonData){

        HotelBookResult hotelBookResult = new HotelBookResult();
        hotelBookResult.setStatusCode(StatusCode.STATUS_ERROR_FROM_SERVER);
        hotelBookResult.setMessageDesc(StatusCode.S_STATUS_ERROR_FROM_SERVER);

        logErrorHelper.saveErrorExc(ApiStatic.API_HOTEL_BOOK,ex.toString(),params,jsonData);

        return hotelBookResult;
    }

    //Translator From JSON


    private boolean isCanbeBooked(Map params){
        HotelCancelPolicyReq hotelCancelPolicyReq = new HotelCancelPolicyReq();

        hotelCancelPolicyReq.setHotelKey(params.get("hotelKey").toString());
        hotelCancelPolicyReq.setSessionID(params.get("sessionID").toString());
        hotelCancelPolicyReq.setRoomID(params.get("roomID").toString());

        HotelCancelPolicyResponse response = hotelCancelPolicyService.getPolicyResponse(hotelCancelPolicyReq);

        log.debug("Response policy : "+response.toString());

        //Todo : save data for log
        // --- this code --

        HotelCancelPolicy hotelCancelPolicy = response.getHotelCancelPolicy();

        if(hotelCancelPolicy.getHotelKey()==null){
            return false;
        }else{
            return true;
        }

    }



    private HotelBookResult translateToObject(String jsonData) throws JSONException {
        HotelBookResult hotelBookResult = new HotelBookResult();

        JSONObject objData = new JSONObject(jsonData);
        JSONObject objResHotel = objData.optJSONObject("ReservationHotels");



        hotelBookResult.setMessageCode(objData.optString("MessageCode"));
        hotelBookResult.setMessageDesc(objData.optString("MessageDesc"));
        hotelBookResult.setReservationID(objData.optString("ReservationID"));
        hotelBookResult.setMerchantID(objData.optString("MerchantID"));
        hotelBookResult.setReservationCode(objData.optString("ReservationCode"));
        hotelBookResult.setMemberID(objData.optString("MemberID"));
        hotelBookResult.setItineraryName(objData.optString("ItineraryName"));
        hotelBookResult.setProductType(objData.optString("ProductType"));
        hotelBookResult.setEmailAlert(objData.optString("EmailAlert"));
        hotelBookResult.setStatusCode(objData.optString("StatusCode"));
        hotelBookResult.setCreatedIPAddress(objData.optString("CreatedIPAddress"));
        hotelBookResult.setLastUpdatedIPAddress(objData.optString("LastUpdatedIPAddress"));
       // hotelBookResult.setReservationCust

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

        hotelBookResult.setHotelReservationCustomer(hotelReservationCustomer);

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


        hotelBookResult.setHotelReservationHotel(reservationHotel);


        return hotelBookResult;

    }


    //static method
    public static Map translateToParam(HotelBookReq hotelBookReq){
        Map param = new HashMap();

        param.put("username",AstriApiCaller.USERNAME);
        param.put("password", AstriApiCaller.PASSWORD);
        param.put("orgCode", AstriApiCaller.ORGCODE);
        param.put("merchantID", AstriApiCaller.MERCHANTID);

        param.put("sessionID", hotelBookReq.getSessionId());
        param.put("hotelKey", hotelBookReq.getHotelKey());
        param.put("roomID", hotelBookReq.getRoomId());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCustomer = "";
        String jsonPaxData = "";

        HotelCustomerData hotelCustomerData = hotelBookReq.getCustomerData();
        CustomerData customerData = new CustomerData();

        customerData.setTitle(hotelCustomerData.getTitle());
        customerData.setFirstName(hotelCustomerData.getFirstName());
        customerData.setLastName(hotelCustomerData.getLastName());
        customerData.setAddress(hotelCustomerData.getAddress());
        customerData.setContactNumber(hotelCustomerData.getContactNumber());
        customerData.setEmail(hotelCustomerData.getEmail());



        try{
            jsonCustomer = objectMapper.writeValueAsString(customerData);
            log.debug("CUSTOMER DATA : "+jsonCustomer);
        }catch (Exception ex){
            log.error("ex "+ex.toString());
        }

        Set<HotelCustomerPax> listCustPax = hotelBookReq.getPaxData();
        Set<PaxData> listPaxData = new HashSet<>();

        PaxData paxData = null;

        for(HotelCustomerPax custPax : listCustPax){
            paxData = new PaxData();
            paxData.setAge(custPax.getAge());
            paxData.setNationality(custPax.getNationality());
            paxData.setPaxFirstName(custPax.getPaxFirstName());
            paxData.setPaxLastName(custPax.getPaxLastName());
            paxData.setPaxNo(custPax.getPaxNo());
            paxData.setPaxTitle(custPax.getPaxTitle());
            paxData.setPaxTypeCode(custPax.getPaxTypeCode());
            paxData.setRoomId(custPax.getRoomId());

            listPaxData.add(paxData);
        }


        try{
            jsonPaxData = objectMapper.writeValueAsString(listPaxData);
        }catch (Exception ex){
            log.error("ex "+ex.toString());
        }



        param.put("jsonCustomer",jsonCustomer);
        param.put("jsonPax", jsonPaxData);
        return param;
    }
}
