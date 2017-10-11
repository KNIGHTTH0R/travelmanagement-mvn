package com.mauwahid.tm.travelmgt.domain.api.request;

public class HotelIssueReq {

    private String apiSource;
    private String hotelDataId;
    private String paymentType;
    private String invoiceNo = "";

    public String getApiSource() {
        return apiSource;
    }

    public void setApiSource(String apiSource) {
        this.apiSource = apiSource;
    }

    public String getHotelDataId() {
        return hotelDataId;
    }

    public void setHotelDataId(String hotelDataId) {
        this.hotelDataId = hotelDataId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
}
