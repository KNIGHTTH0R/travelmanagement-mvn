package com.mauwahid.tm.travelmgt.domain.api.response;

import com.mauwahid.tm.travelmgt.domain.apimodel.Airline;

import java.util.ArrayList;


public class ResGetAirlines implements IData {

    ArrayList<Airline> airlines;

    public ResGetAirlines(ArrayList<Airline> airlines){
        this.airlines = airlines;
    }

    public ArrayList<Airline> getAirlines() {
        return airlines;
    }

    public void setAirlines(ArrayList<Airline> airlines) {
        this.airlines = airlines;
    }
}
