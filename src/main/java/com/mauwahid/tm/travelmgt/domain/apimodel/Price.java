package com.mauwahid.tm.travelmgt.domain.apimodel;


public class Price {

    private String b2bCommision;
    private String agentCommision;
    private String branchCommision;
    private String ticketPrice;

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
}
