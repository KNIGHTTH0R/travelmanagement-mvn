package com.mauwahid.tm.travelmgt.domain.api.response.pointer;

public class BestPrice {

    private String fare;
    private String totalPrice;
    private String tax;
    private String fareAdultPax;
    private String fareChildPax;
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
