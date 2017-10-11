package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelDetailReq;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelDetailResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelSearchResponse;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelHotel;
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


    public HotelDetailResponse getDetailHotel(HotelDetailReq hotelDetailReq){

        HotelDetailResponse response = new HotelDetailResponse();
        response.setStatusCode("2");
        response.setStatusDesc("not implemented");

        if(hotelDetailReq.getApiSource().equalsIgnoreCase("trevohub")){

            HotelHotel hotel = getTrevohub(hotelDetailReq);
            response = translateResponse(hotel);
        }


        return response;

    }

    private HotelHotel getTrevohub(HotelDetailReq hotelDetailReq){

        Map param = TrevoHotelDetail.translateToParam(hotelDetailReq);


        HotelHotel trevoHubHotel = trevoHotelDetail.getDetailHotel(param);

        return trevoHubHotel;

    }



    private HotelDetailResponse translateResponse(HotelHotel hotel){
        HotelDetailResponse hotelDetailResponse = new HotelDetailResponse();
        hotelDetailResponse.setSessionKey("abcdefghijklm");

        hotelDetailResponse.setHotelResult(hotel);

        return hotelDetailResponse;
    }
}
