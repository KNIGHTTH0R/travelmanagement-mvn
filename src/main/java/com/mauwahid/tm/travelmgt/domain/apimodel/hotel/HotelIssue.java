package com.mauwahid.tm.travelmgt.domain.apimodel.hotel;

import com.mauwahid.tm.travelmgt.domain.apimodel.old.Customer;

public class HotelIssue {

    private HotelHotel hotel;

    private HotelCustomer customer;

    public HotelHotel getHotel() {
        return hotel;
    }

    public void setHotel(HotelHotel hotel) {
        this.hotel = hotel;
    }

    public HotelCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(HotelCustomer customer) {
        this.customer = customer;
    }
}
