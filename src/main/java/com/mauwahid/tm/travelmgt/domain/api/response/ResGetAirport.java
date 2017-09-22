package com.mauwahid.tm.travelmgt.domain.api.response;

import com.mauwahid.tm.travelmgt.domain.apimodel.Airport;

import java.util.ArrayList;

public class ResGetAirport implements IData {

    private ArrayList<Airport> airports;


    public ResGetAirport(ArrayList<Airport> airports){
        this.airports = airports;
    }

}
