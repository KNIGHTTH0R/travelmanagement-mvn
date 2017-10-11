package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelBookReq;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelBookResponse;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelSearchResponse;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelBookInfo;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelHotel;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelBook;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class HotelBookService {

    private HotelBookResponse hotelBookResponse;

    @Autowired
    private TrevoHotelBook trevoHotelBook;


    public HotelBookResponse bookHotel(HotelBookReq hotelBookReq){

        HotelBookResponse hotelBookResponse = new HotelBookResponse();
        hotelBookResponse.setStatusCode("2");
        hotelBookResponse.setStatusDesc("Not Implemented");

        if(hotelBookReq.getApiSource().equalsIgnoreCase("trevohub")){
            HotelBookInfo hotelBookInfo = bookTrevo(hotelBookReq);
            return translateResponse(hotelBookInfo);

        }


        return hotelBookResponse;

    }

    private HotelBookInfo bookTrevo(HotelBookReq hotelBookReq){

        Map param = TrevoHotelBook.translateToParam(hotelBookReq);


        HotelBookInfo hotelBookInfo = trevoHotelBook.bookHotel(param);


        return hotelBookInfo;

    }


    private HotelBookResponse translateResponse(HotelBookInfo hotelBookInfo){
        HotelBookResponse hotelBookResponse = new HotelBookResponse();

        hotelBookResponse.setBookInfo(hotelBookInfo);

        return hotelBookResponse;
    }
}
