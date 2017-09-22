package com.mauwahid.tm.travelmgt.domain.api.response;

import com.mauwahid.tm.travelmgt.domain.apimodel.Arrival;
import com.mauwahid.tm.travelmgt.domain.apimodel.Departure;

import java.util.ArrayList;

public class ResGetRoute implements IData {

    private Departure departure;
    private ArrayList<Arrival> arrivals;

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public ArrayList<Arrival> getArrivals() {
        return arrivals;
    }

    public void setArrivals(ArrayList<Arrival> arrivals) {
        this.arrivals = arrivals;
    }
}
