package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain;

import lombok.Data;

import java.util.List;

@Data
public class Hotel {
    private Integer hotelId;
    private String hotelCode;
    private Integer hotelScore;
    private String hotelName;
    private String locationName;
    private String cityCode;
    private String cityName;
    private String countryCode;
    private Object mapCountryCode;
    private String countryName;
    private Integer roomCount;
    private String address1;
    private String address2;
    private String address3;
    private Object continent;
    private Object region;
    private String telephone;
    private String facsimile;
    private String email;
    private String website;
    private String description;
    private String longDescription;
    private List<Facility> facility = null;
    private Integer rating;
    private String categoryDescription;
    private String currency;
    private String marketName;
    private String internalCode;
    private List<RoomsCategory> roomsCategory = null;
    private String heroImage;
    private List<String> images = null;
    private Boolean avail;
    private String availType;
    private Double tripAdvisorRating;
    private Integer tripAdvisorReviewCount;
    private String tripAdvisorRatingUrl;
    private Double latitude;
    private Double longitude;
    private String checkInInstructions;
    private Object specialCheckInInstructions;
    private List<Promo> promos = null;
    private Integer distanceFromSearchedHotel;
    private Object specialRequest;
}
