package com.mauwahid.tm.travelmgt.domain.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightPrice {

    private String fare;

    @JsonProperty("total_price")
    private String totalPrice;
    private String tax;

    @JsonProperty("fare_adult_pax")
    private String fareAdultPax;

    @JsonProperty("fare_child_pax")
    private String fareChildPax;

    @JsonProperty("fare_infant_pax")
    private String fareInfantPax;

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getFareAdultPax() {
        return fareAdultPax;
    }

    public void setFareAdultPax(String fareAdultPax) {
        this.fareAdultPax = fareAdultPax;
    }

    public String getFareChildPax() {
        return fareChildPax;
    }

    public void setFareChildPax(String fareChildPax) {
        this.fareChildPax = fareChildPax;
    }

    public String getFareInfantPax() {
        return fareInfantPax;
    }

    public void setFareInfantPax(String fareInfantPax) {
        this.fareInfantPax = fareInfantPax;
    }
}
