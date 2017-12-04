package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelBookReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelBookResponse;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelBookInfo;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelBookResult;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelBook;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HotelBookService {


    @Autowired
    private TrevoHotelBook trevoHotelBook;

    @Autowired
    private AstriHotelBook astriHotelBook;


    public HotelBookResponse bookHotel(HotelBookReq hotelBookReq){

        HotelBookResponse hotelBookResponse = new HotelBookResponse();
        hotelBookResponse.setStatus("2");
        hotelBookResponse.setMessage("Not Implemented");




        if(hotelBookReq.getApiSource().equalsIgnoreCase("astrindo")){
            HotelBookResult hotelBookResult = bookAstri(hotelBookReq);
            return translateResponse(hotelBookResult);

        }


        return hotelBookResponse;

    }

    private HotelBookInfo bookTrevo(HotelBookReq hotelBookReq){

        Map param = TrevoHotelBook.translateToParam(hotelBookReq);


        HotelBookInfo hotelBookInfo = trevoHotelBook.bookHotel(param);


        return hotelBookInfo;

    }

    private HotelBookResult bookAstri(HotelBookReq hotelBookReq){

        Map param = AstriHotelBook.translateToParam(hotelBookReq);


        HotelBookResult hotelBookResult = astriHotelBook.bookHotel(param);


        return hotelBookResult;

    }


    private HotelBookResponse translateResponse(HotelBookResult hotelBookResult){
        HotelBookResponse hotelBookResponse = new HotelBookResponse();

        hotelBookResponse.setBookResult(hotelBookResult);

        return hotelBookResponse;
    }
}
