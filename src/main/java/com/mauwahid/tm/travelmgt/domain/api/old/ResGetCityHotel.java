package com.mauwahid.tm.travelmgt.domain.api.old;

import java.util.ArrayList;

public class ResGetCityHotel implements IData {

    ArrayList<CityHotel> cityHotels;

    public ResGetCityHotel(ArrayList<CityHotel> cityHotels) {
        this.cityHotels = cityHotels;
    }

    public ArrayList<CityHotel> getCityHotels() {
        return cityHotels;
    }

    public void setCityHotels(ArrayList<CityHotel> cityHotels) {
        this.cityHotels = cityHotels;
    }
}
