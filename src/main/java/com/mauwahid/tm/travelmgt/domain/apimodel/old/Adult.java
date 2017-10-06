package com.mauwahid.tm.travelmgt.domain.apimodel.old;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Adult {

    private String pax;
    private String basic;
    private String tax;
    private String iwjr;
    private String service;
    private String margin;

    @JsonProperty("ticket_price")
    private String ticketPrice;

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getIwjr() {
        return iwjr;
    }

    public void setIwjr(String iwjr) {
        this.iwjr = iwjr;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
