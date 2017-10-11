package com.mauwahid.tm.travelmgt.domain.apimodel.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelBookResult {

    @JsonProperty("hotel_id")
    private String hotelId;

    @JsonProperty("room_id")
    private String roomId;

    @JsonProperty("room_quality")
    private String roomQuality;

    private String night;

    @JsonProperty("check_in")
    private String checkIn;

    @JsonProperty("check_out")
    private String checkOut;

    @JsonProperty("currency_code")
    private String currencyCode;

    @JsonProperty("booking_date")
    private String bookingDate;

    @JsonProperty("custoemr_name")
    private String customerName;

    @JsonProperty("customer_email")
    private String customerEmail;

    @JsonProperty("customer_phone")
    private String customerPhone;

    private String status;

    @JsonProperty("book_id")
    private String bookId;

    @JsonProperty("time_limit")
    private String timeLimit;

    @JsonProperty("price_per_room")
    private HotelPricePerRoom pricePerRoom;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomQuality() {
        return roomQuality;
    }

    public void setRoomQuality(String roomQuality) {
        this.roomQuality = roomQuality;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public HotelPricePerRoom getPricePerRoom() {
        return pricePerRoom;
    }

    public void setPricePerRoom(HotelPricePerRoom pricePerRoom) {
        this.pricePerRoom = pricePerRoom;
    }
}
