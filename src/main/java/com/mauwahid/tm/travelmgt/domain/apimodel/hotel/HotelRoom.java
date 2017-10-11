package com.mauwahid.tm.travelmgt.domain.apimodel.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class HotelRoom {


    @JsonProperty("room_id")
    private String roomId;

    @JsonProperty("room_name")
    private String roomName;

    @JsonProperty("room_capacity")
    private String roomCapacity;

    @JsonProperty("room_information")
    private String roomInformation;

    private String description;

    private String currency;

    private Set<String> facilities;


    @JsonProperty("total_room")
    private String totalRoom;

    @JsonProperty("total_night")
    private String totalNight;

    @JsonProperty("room_type")
    private String roomType;


    @JsonProperty("price_per_rooom")
    private HotelPricePerRoom pricePerRoom;

    private HotelPriceTotal price;

    @JsonProperty("bed_types")
    private Set<HotelBedType> bedTypes;

    @JsonProperty("smooking_prefs")
    private Set<HotelSmookingPref> smookingPrefs;

    private Set<HotelImage> images;

    public Set<HotelImage> getImages() {
        return images;
    }

    public void setImages(Set<HotelImage> images) {
        this.images = images;
    }

    public HotelPricePerRoom getPricePerRoom() {
        return pricePerRoom;
    }

    public void setPricePerRoom(HotelPricePerRoom pricePerRoom) {
        this.pricePerRoom = pricePerRoom;
    }

    public HotelPriceTotal getPrice() {
        return price;
    }

    public void setPrice(HotelPriceTotal price) {
        this.price = price;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(String roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public String getRoomInformation() {
        return roomInformation;
    }

    public void setRoomInformation(String roomInformation) {
        this.roomInformation = roomInformation;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTotalRoom() {
        return totalRoom;
    }

    public void setTotalRoom(String totalRoom) {
        this.totalRoom = totalRoom;
    }

    public String getTotalNight() {
        return totalNight;
    }

    public void setTotalNight(String totalNight) {
        this.totalNight = totalNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Set<HotelBedType> getBedTypes() {
        return bedTypes;
    }

    public void setBedTypes(Set<HotelBedType> bedTypes) {
        this.bedTypes = bedTypes;
    }

    public Set<HotelSmookingPref> getSmookingPrefs() {
        return smookingPrefs;
    }

    public void setSmookingPrefs(Set<HotelSmookingPref> smookingPrefs) {
        this.smookingPrefs = smookingPrefs;
    }

    public Set<String> getFacilities() {
        return facilities;
    }

    public void setFacilities(Set<String> facilities) {
        this.facilities = facilities;
    }
}
