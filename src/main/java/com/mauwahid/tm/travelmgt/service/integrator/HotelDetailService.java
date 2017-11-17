package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelDetailReq;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelDetailResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelSearchResponse;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelHotel;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelDetail;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelDetail;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class HotelDetailService {

    private HotelDetailResponse hotelDetailResponse;

    @Autowired
    private TrevoHotelDetail trevoHotelDetail;

    @Autowired
    private AstriHotelDetail astriHotelDetail;


    public HotelDetailResponse getDetailHotel(HotelDetailReq hotelDetailReq){

        HotelDetailResponse response = new HotelDetailResponse();
        response.setStatusCode("2");
        response.setStatusDesc("not implemented");
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


        HotelHotel trevoHubHotel = trevoHotelDetail.getDetailHotel(param);

        return trevoHubHotel;

    }

    private HotelHotel getAstrindo(HotelDetailReq hotelDetailReq){

        Map param = AstriHotelDetail.translateToParam(hotelDetailReq);


        HotelHotel trevoHubHotel = astriHotelDetail.getDetailHotel(param);

        return trevoHubHotel;

    }



    private HotelDetailResponse translateResponse(HotelHotel hotel){
        HotelDetailResponse hotelDetailResponse = new HotelDetailResponse();
        hotelDetailResponse.setSessionKey("abcdefghijklm");

        hotelDetailResponse.setHotelResult(hotel);

        return hotelDetailResponse;
    }
}
