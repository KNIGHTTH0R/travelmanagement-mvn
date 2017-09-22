package com.mauwahid.tm.travelmgt.domain.api.response.pointer;

import java.util.ArrayList;
import java.util.Set;

public class PointerData {

    private String idPerjalanan;
    private String flightCount;
    private ArrayList<PointerDataDetail> details;

    public String getIdPerjalanan() {
        return idPerjalanan;
    }

    public void setIdPerjalanan(String idPerjalanan) {
        this.idPerjalanan = idPerjalanan;
    }

    public String getFlightCount() {
        return flightCount;
    }

    public void setFlightCount(String flightCount) {
        this.flightCount = flightCount;
    }


    public ArrayList<PointerDataDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<PointerDataDetail> details) {
        this.details = details;
    }
}
