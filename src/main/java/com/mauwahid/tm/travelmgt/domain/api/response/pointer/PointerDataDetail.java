package com.mauwahid.tm.travelmgt.domain.api.response.pointer;

import java.util.ArrayList;
import java.util.Set;

public class PointerDataDetail {

    private String airlineIcon;
    private String timeDepart;
    private String dateArrive;
    private String dateDepart;
    private String areaDepart;
    private String timeArrive;
    private String flightID;
    private String areaArrive;

    private ArrayList<Flight> flights;
    private ArrayList<Seat> seats;

    public String getAirlineIcon() {
        return airlineIcon;
    }

    public void setAirlineIcon(String airlineIcon) {
        this.airlineIcon = airlineIcon;
    }

    public String getTimeDepart() {
        return timeDepart;
    }

    public void setTimeDepart(String timeDepart) {
        this.timeDepart = timeDepart;
    }

    public String getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(String dateArrive) {
        this.dateArrive = dateArrive;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getAreaDepart() {
        return areaDepart;
    }

    public void setAreaDepart(String areaDepart) {
        this.areaDepart = areaDepart;
    }

    public String getTimeArrive() {
        return timeArrive;
    }

    public void setTimeArrive(String timeArrive) {
        this.timeArrive = timeArrive;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getAreaArrive() {
        return areaArrive;
    }

    public void setAreaArrive(String areaArrive) {
        this.areaArrive = areaArrive;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }
}
