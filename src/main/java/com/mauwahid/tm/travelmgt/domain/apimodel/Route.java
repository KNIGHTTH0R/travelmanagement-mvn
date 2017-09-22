package com.mauwahid.tm.travelmgt.domain.apimodel;

import com.mauwahid.tm.travelmgt.domain.api.response.IData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Route implements IData {

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
