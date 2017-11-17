package com.mauwahid.tm.travelmgt.repository.api.astrindo;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelSearchReq;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.*;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.HotelSearchInterface;
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
@Qualifier("astri_hotel_search")
public class AstriHotelAvailability implements HotelSearchInterface {

    private Map params = new HashMap<String,String>();

    private String url;


    @Autowired
    private AstriApiCaller astriApiCaller;

  //TODO : Should be async task
  //  @Async
    public Set<HotelHotel> searchHotel(Map params) {

        String jsonData;

        url = AstriApiCaller.uri + "HotelAvailability.aspx";
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
            log.error("translateToObj : "+ex.getMessage());

            return exceptionHandling(ex);
        }
    }


    private Set<HotelHotel> exceptionHandling(Exception ex){

        return null;
    }


    private Set<HotelHotel> translateToObject(String jsonData) throws JSONException {
        HotelHotel hotel;
        Set<HotelHotel> hotels = new HashSet<>();

        JSONObject objData = new JSONObject(jsonData);
        JSONArray arrHotel = objData.optJSONArray("HotelList");
        JSONObject objHotel;

        HotelFacility hotelFacility = null;
        Set<HotelFacility> facilities = new HashSet<>();

        HotelLocation location;

        for(int i=0;i<arrHotel.length();i++){
            objHotel = arrHotel.optJSONObject(i);
            hotel = new HotelHotel();

            log.debug("Hotel "+i);
            hotel.setSessionId(objData.optString("SessionID"));
            hotel.setHotelKey(objHotel.optString("HotelKey"));
            hotel.setHotelId(objHotel.optString("HotelID"));
            hotel.setHotelName(objHotel.optString("HotelName"));
            hotel.setStars(objHotel.optString("StarCategory"));
            hotel.setDescription(objHotel.optString("Description"));
            hotel.setAddDescription(objHotel.optString("FacilityDescription"));
            hotel.setAddress(objHotel.optString("Address"));
            hotel.setPostalCode(objHotel.optString("PostalCode"));
            hotel.setTelephone(objHotel.optString("Telephone"));
            hotel.setFax(objHotel.optString("Fax"));
            hotel.setEmail(objHotel.optString("Email"));
            hotel.setWebsite(objHotel.optString("Website"));

            location = new HotelLocation();
            location.setLatitude(objHotel.optString("Latititude"));
            location.setLongitude(objHotel.optString("Longitude"));


            JSONArray arrFacilities = objHotel.optJSONArray("HotelFacilityList");
            JSONObject objFacility = null;

            log.debug("try to open facility");
            for(int x=0;x<arrFacilities.length();x++){
                    objFacility = arrFacilities.getJSONObject(x);
                    hotelFacility = new HotelFacility();

                //    log.debug("Hotel Facility "+x);

                    hotelFacility.setFacilityName(objFacility.optString("FacilityName"));
                    hotelFacility.setFee(objFacility.optString("Fee"));
                    hotelFacility.setText(objFacility.optString("Text"));

                    facilities.add(hotelFacility);
            }

            hotel.setHotelFacilities(facilities);

            JSONArray arrAvailableRooms = objHotel.optJSONArray("AvailableRoomList");
            JSONObject objAvailableRoom = null;

            Set<HotelAvailableRoom> hotelAvailableRooms = new HashSet<>();
            HotelAvailableRoom hotelAvailableRoom = null;


            for(int y=0;y<arrAvailableRooms.length();y++){
                objAvailableRoom = arrAvailableRooms.getJSONObject(y);

                hotelAvailableRoom = new HotelAvailableRoom();
            //    log.debug("available room "+y);
                hotelAvailableRoom.setBoardName(objAvailableRoom.optString("BoardName"));
                hotelAvailableRoom.setDiscountValue(objAvailableRoom.optString("DiscountValue"));
                hotelAvailableRoom.setIsPromo(objAvailableRoom.optString("IsPromo"));
                hotelAvailableRoom.setRoomId(objAvailableRoom.optString("RoomId"));
                hotelAvailableRoom.setSupplierCode(objAvailableRoom.optString("SupplierCode"));
                hotelAvailableRoom.setSuppHotelCode(objAvailableRoom.optString("SuppHotelCode"));
                hotelAvailableRoom.setRoomTypeCode(objAvailableRoom.optString("RoomTypeCode"));
                hotelAvailableRoom.setRoomTypeName(objAvailableRoom.optString("RoomTypeName"));
                hotelAvailableRoom.setTotalRoom(objAvailableRoom.optString("TotalRoom"));
                hotelAvailableRoom.setOriCurrCode(objAvailableRoom.optString("OriginalCurrCode"));
                hotelAvailableRoom.setOriginalPrice(objAvailableRoom.optString("OriginalPrice"));
                hotelAvailableRoom.setSellCurrCode(objAvailableRoom.optString("SellCurrCode"));
                hotelAvailableRoom.setPriceConverted(objAvailableRoom.optString("PriceConverted"));
                hotelAvailableRoom.setDiscountValue(objAvailableRoom.optString("DiscountValue"));
                hotelAvailableRoom.setServiceFee(objAvailableRoom.optString("ServiceFee"));
                hotelAvailableRoom.setIsPromo(objAvailableRoom.optString("IsPromo"));
                hotelAvailableRoom.setVoucherCode(objAvailableRoom.optString("VoucherCode"));
                hotelAvailableRoom.setVoucherValue(objAvailableRoom.optString("VoucherValue"));
                hotelAvailableRoom.setNightPrice(objAvailableRoom.optString("NightPrice"));
                hotelAvailableRoom.setNightPriceDiscounted(objAvailableRoom.optString("NightPriceDiscounted"));
                hotelAvailableRoom.setTotalPrice(objAvailableRoom.optString("TotalPrice"));
                hotelAvailableRoom.setTotalPriceDiscounted(objAvailableRoom.optString("TotalPriceDiscounted"));

                hotelAvailableRooms.add(hotelAvailableRoom);
            }

            hotel.setRooms(hotelAvailableRooms);

            Set<HotelImage> hotelImages = new HashSet<>();
            HotelImage hotelImage = null;

            JSONArray arrHotelImages = objHotel.optJSONArray("HotelImageList");
            JSONObject objHotelImage = null;

            for(int z=0;z<arrHotelImages.length();z++){
                objHotelImage = arrHotelImages.optJSONObject(z);
                hotelImage = new HotelImage();

              //  log.debug("hotel image "+z);

                hotelImage.setHotelId(objHotelImage.optString("HotelID"));
                hotelImage.setImageCode(objHotelImage.optString("ImageCode"));
                hotelImage.setImageURL(objHotelImage.optString("ImageURL"));

                hotelImages.add(hotelImage);

            }

            hotel.setImages(hotelImages);


            JSONArray arrMgRooms = objHotel.optJSONArray("MGRoomList");
            JSONObject objMgRoom = null;

            MGRoom mgRoom = null;
            Set<MGRoom> mgRooms = new HashSet<>();

            for(int w=0;w<arrMgRooms.length();w++){
                objMgRoom = arrMgRooms.optJSONObject(w);

                mgRoom = new MGRoom();
                mgRoom.setRoomId(objMgRoom.optString("RoomId"));
                mgRoom.setPaxPassport(objMgRoom.optString("PaxPassport"));
                mgRoom.setCancelPolicyId(objMgRoom.optString("CancelPolicyID"));
                mgRoom.setInternalCode(objMgRoom.optString("InternalCode"));
                mgRoom.setCategoryCode(objMgRoom.optString("CategoryCode"));
                mgRoom.setCategoryName(objMgRoom.optString("CategoryName"));
                mgRoom.setTypeName(objMgRoom.optString("TypeName"));
                mgRoom.setPricePerNight(objMgRoom.optString("PricePerNight"));
                mgRoom.setTotalPrice(objMgRoom.optString("TotalPrice"));
                mgRoom.setNumRooms(objMgRoom.optString("NumRooms"));
                mgRoom.setBfType(objMgRoom.optString("BFType"));

                mgRooms.add(mgRoom);

            }

            hotel.setMgRooms(mgRooms);

            hotels.add(hotel);

        }


        return hotels;

    }


    //static method
    public static Map translateToParam(HotelSearchReq hotelSearchReq){
        Map param = new HashMap();

        param.put("username",AstriApiCaller.USERNAME);
        param.put("password", AstriApiCaller.PASSWORD);
        param.put("orgCode", AstriApiCaller.ORGCODE);
        param.put("merchantID", AstriApiCaller.MERCHANTID);
        param.put("sessionID", AstriApiCaller.generateSessionID());


        param.put("nationalityCode",hotelSearchReq.getBookerNationalityCode());
        param.put("destCountryCode", hotelSearchReq.getCountryCode());
        param.put("destCityCode", hotelSearchReq.getCity());
        param.put("checkInDate", hotelSearchReq.getCheckIn());
        param.put("checkOutDate", hotelSearchReq.getCheckOut());
        param.put("singleRoom", hotelSearchReq.getSingleRoom());
        param.put("doubleRoom", hotelSearchReq.getDoubleRoom());
        param.put("twinRoom", hotelSearchReq.getTwinRoom());
        param.put("tripleRoom", hotelSearchReq.getTripleRoom());
        param.put("quadRoom", hotelSearchReq.getQuadRoom());

        return param;
    }

}
