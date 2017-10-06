package com.mauwahid.tm.travelmgt.domain.apimodel.old;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Arrival {

    private String arrivalAirport;
    private String arrivalAirportName;
    private String arrivalCity;
    private String arrivalDistrict;
    private String arrivalCountry;
    private ArrayList<Airline> airlines;

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getArrivalDistrict() {
        return arrivalDistrict;
    }

    public void setArrivalDistrict(String arrivalDistrict) {
        this.arrivalDistrict = arrivalDistrict;
    }

    public String getArrivalCountry() {
        return arrivalCountry;
    }

    public void setArrivalCountry(String arrivalCountry) {
        this.arrivalCountry = arrivalCountry;
    }

    public ArrayList<Airline> getAirlines() {
        return airlines;
    }

    public void setAirlines(ArrayList<Airline> airlines) {
        this.airlines = airlines;
    }
}
