package com.mauwahid.tm.travelmgt.domain.api.response.pointer;

import java.util.Set;

public class PointerResult {

    private String airline;
    private Set<PointerData> departs;
    private Set<PointerData> returns;

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Set<PointerData> getDeparts() {
        return departs;
    }

    public void setDeparts(Set<PointerData> departs) {
        this.departs = departs;
    }

    public Set<PointerData> getReturns() {
        return returns;
    }

    public void setReturns(Set<PointerData> returns) {
        this.returns = returns;
    }
}
