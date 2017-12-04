package com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelPricePerRoom {

    @JsonProperty("basic_price")
    private String basicPrice;

    private String service;

    @JsonProperty("b2b_comission")
    private String b2bCommision;


    @JsonProperty("agent_commission")
    private String agentCommission;

    @JsonProperty("branch_commission")
    private String branchCommisiion;

    @JsonProperty("voucher_price")
    private String voucherPrice;

    @JsonProperty("agent_price")
    private String agentPrice;


    @JsonProperty("customer_price")
    private String customerPrice;

    @JsonProperty("publish_rate")
    private String publishRate;


    @JsonProperty("tax_service")
    private String taxService;

    private String margin;

    public String getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(String basicPrice) {
        this.basicPrice = basicPrice;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getB2bCommision() {
        return b2bCommision;
    }

    public void setB2bCommision(String b2bCommision) {
        this.b2bCommision = b2bCommision;
    }

    public String getAgentCommission() {
        return agentCommission;
    }

    public void setAgentCommission(String agentCommission) {
        this.agentCommission = agentCommission;
    }

    public String getBranchCommisiion() {
        return branchCommisiion;
    }

    public void setBranchCommisiion(String branchCommisiion) {
        this.branchCommisiion = branchCommisiion;
    }

    public String getVoucherPrice() {
        return voucherPrice;
    }

    public void setVoucherPrice(String voucherPrice) {
        this.voucherPrice = voucherPrice;
    }

    public String getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(String agentPrice) {
        this.agentPrice = agentPrice;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getCustomerPrice() {
        return customerPrice;
    }

    public void setCustomerPrice(String customerPrice) {
        this.customerPrice = customerPrice;
    }

    public String getPublishRate() {
        return publishRate;
    }

    public void setPublishRate(String publishRate) {
        this.publishRate = publishRate;
    }

    public String getTaxService() {
        return taxService;
    }

    public void setTaxService(String taxService) {
        this.taxService = taxService;
    }
}
