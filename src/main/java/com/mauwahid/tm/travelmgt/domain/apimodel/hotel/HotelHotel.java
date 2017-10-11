package com.mauwahid.tm.travelmgt.domain.apimodel.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class HotelHotel {

    @JsonProperty("status_code")
    private String statusCode;

    @JsonProperty("status_desc")
    private String statusDesc;

    @JsonProperty("hotel_session_id")
    private String sessionId;


    @JsonProperty("api_source")
    private String hotelAPI;

    @JsonProperty("hotel_name")
    private String hotelName;

    private String city;

    private String address;

    private String description;

    @JsonProperty("hotel_key")
    private String hotelKey;

    @JsonProperty("hotel_id")
    private String hotelId;

    @JsonProperty("price_rate")
    private String priceRate;

    private String stars;

    private String rating;

    @JsonProperty("service_id")
    private String serviceId;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("service_code")
    private String serviceCode;

    @JsonProperty("check_in")
    private String checkIn;

    @JsonProperty("check_out")
    private String checkOut;

    private Set<HotelRoom> rooms;

    private HotelLocation location;

    private Set<HotelImage> images;


    @JsonProperty("additional_info")
    private HotelAddInfo addInfo;

    public HotelAddInfo getAddInfo() {
        return addInfo;
    }

    public Set<HotelImage> getImages() {
        return images;
    }

    public void setImages(Set<HotelImage> images) {
        this.images = images;
    }

    public void setAddInfo(HotelAddInfo addInfo) {
        this.addInfo = addInfo;
    }


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("price_per_room")
    private Set<HotelPricePerRoom> pricePerRooms;

    @JsonProperty("price_total")
    private Set<HotelPriceTotal> priceTotals;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getHotelAPI() {
        return hotelAPI;
    }

    public void setHotelAPI(String hotelAPI) {
        this.hotelAPI = hotelAPI;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHotelKey() {
        return hotelKey;
    }

    public void setHotelKey(String hotelKey) {
        this.hotelKey = hotelKey;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(String priceRate) {
        this.priceRate = priceRate;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public HotelLocation getLocation() {
        return location;
    }

    public void setLocation(HotelLocation location) {
        this.location = location;
    }

    public Set<HotelPricePerRoom> getPricePerRooms() {
        return pricePerRooms;
    }

    public void setPricePerRooms(Set<HotelPricePerRoom> pricePerRooms) {
        this.pricePerRooms = pricePerRooms;
    }

    public Set<HotelPriceTotal> getPriceTotals() {
        return priceTotals;
    }

    public void setPriceTotals(Set<HotelPriceTotal> priceTotals) {
        this.priceTotals = priceTotals;
    }

    public Set<HotelRoom> getRooms() {
        return rooms;
    }

    public void setRooms(Set<HotelRoom> rooms) {
        this.rooms = rooms;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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
}
