package com.mauwahid.tm.travelmgt.domain.api.response.pointer;

public class Seat {

    private String available;
    private String code;
    private String flightKey;
    private String seatClass;

    private BestPrice bestPrice;

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFlightKey() {
        return flightKey;
    }

    public void setFlightKey(String flightKey) {
        this.flightKey = flightKey;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public BestPrice getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(BestPrice bestPrice) {
        this.bestPrice = bestPrice;
    }
}
