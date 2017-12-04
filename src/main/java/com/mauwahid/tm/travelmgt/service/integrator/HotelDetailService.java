package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelHotel;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelDetailReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelDetailResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelSearchResponse;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelDetail;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.HotelDetailInterface;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelDetail;
import com.mauwahid.tm.travelmgt.utils.Common;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HotelDetailService {

    private HotelDetailResponse hotelDetailResponse;


    private HotelDetailInterface hotelDetailInterface;


    public HotelDetailResponse getDetailHotel(int userId, HotelDetailReq hotelDetailReq){

        HotelDetailResponse response = new HotelDetailResponse();
        response.setStatus(StatusCode.NOT_IMPLEMENTED);
        response.setMessage(StatusCode.S_NOT_IMPLEMENTED);
        HotelHotel hotel;

        if(hotelDetailReq.getApiSource().equalsIgnoreCase("trevohub")){

            hotel = getTrevohub(hotelDetailReq);
            response = translateResponse(hotel);
        }

        if(hotelDetailReq.getApiSource().equalsIgnoreCase("astrindo")){
            hotel = getAstrindo(hotelDetailReq);
            response = translateResponse(hotel);
        }


        return response;

    }

    private HotelHotel getTrevohub(HotelDetailReq hotelDetailReq){

        Map param = TrevoHotelDetail.translateToParam(hotelDetailReq);

        hotelDetailInterface = new TrevoHotelDetail();

        HotelHotel trevoHubHotel = hotelDetailInterface.getDetailHotel(param);

        return trevoHubHotel;

    }

    private HotelHotel getAstrindo(HotelDetailReq hotelDetailReq){

        Map param = AstriHotelDetail.translateToParam(hotelDetailReq);

        hotelDetailInterface = new AstriHotelDetail();

        HotelHotel astriHotel = hotelDetailInterface.getDetailHotel(param);

        return astriHotel;

    }


    private HotelDetailResponse translateResponse(HotelHotel hotel){
        HotelDetailResponse hotelDetailResponse = new HotelDetailResponse();

        String sessionId = Common.generateSessionID();

        hotelDetailResponse.setSessionKey(sessionId);
        hotelDetailResponse.setHotelResult(hotel);

        return hotelDetailResponse;
    }

    private void saveToLog(int userId, HotelSearchResponse hotelSearchResponse){


        //todo : log_hotel_detail -> user_id, api_date, statusCode, message, jsonOf HotelSearchResponse

    }
}
