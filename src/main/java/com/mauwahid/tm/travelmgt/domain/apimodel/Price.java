package com.mauwahid.tm.travelmgt.domain.apimodel;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.applet.AudioClip;

public class Price {

    @JsonProperty("b2b_commision")
    private String b2bCommision;

    @JsonProperty("agent_commision")
    private String agentCommision;

    @JsonProperty("branch_commision")
    private String branchCommision;

    @JsonProperty("ticket_price")
    private String ticketPrice;

    @JsonProperty("agent_price")
    private String agentPrice;

    private Adult adult;

    public String getB2bCommision() {
        return b2bCommision;
    }

    public void setB2bCommision(String b2bCommision) {
        this.b2bCommision = b2bCommision;
    }

    public String getAgentCommision() {
        return agentCommision;
    }

    public void setAgentCommision(String agentCommision) {
        this.agentCommision = agentCommision;
    }

    public String getBranchCommision() {
        return branchCommision;
    }

    public void setBranchCommision(String branchCommision) {
        this.branchCommision = branchCommision;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Adult getAdult() {
        return adult;
    }

    public void setAdult(Adult adult) {
        this.adult = adult;
    }

    public String getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(String agentPrice) {
        this.agentPrice = agentPrice;
    }
}
