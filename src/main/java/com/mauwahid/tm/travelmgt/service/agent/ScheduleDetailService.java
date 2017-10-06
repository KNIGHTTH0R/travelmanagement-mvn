package com.mauwahid.tm.travelmgt.service.agent;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchDetailReq;
import com.mauwahid.tm.travelmgt.domain.api.old.ResSearchDetail;
import com.mauwahid.tm.travelmgt.service.agent.trevohub.ScheduleDetailApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ScheduleDetailService {

    private ScheduleDetailApi scheduleDetailApiTH;


    Logger logger = LoggerFactory.getLogger(SearchScheduleService.class);



    public ResSearchDetail getScheduleDetail(FlightSearchDetailReq flightSearchDetailReq){

        ResSearchDetail resSearchDetail = new ResSearchDetail();

        if(flightSearchDetailReq.getApiName().equalsIgnoreCase("trevohub")){
            resSearchDetail = getTrevohub(flightSearchDetailReq);
        }

        return resSearchDetail;


    }



    private ResSearchDetail getTrevohub(FlightSearchDetailReq flightSearchDetailReq){


        scheduleDetailApiTH = new ScheduleDetailApi();
        scheduleDetailApiTH.setReqSearch(flightSearchDetailReq);


        return scheduleDetailApiTH.populateDetailSchedules();
    }

    private ResSearchDetail getPointer(FlightSearchReq flightSearchReq){
        return null;

    }


}
