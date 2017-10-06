package com.mauwahid.tm.travelmgt.domain.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiKeyAuthDefinition;

import java.util.Set;

public class FlightIssue {

   @JsonProperty("status_code")
   private String statusCode;

   @JsonProperty("status_desc")
   private String statusDesc;

   @JsonProperty("payment_status_code")
   private String paymentStatusCode;

   @JsonProperty("payment_status")
   private String paymentStatus;

   @JsonProperty("booking_code")
   private String bookingCode;

   @JsonProperty("booking_time")
   private String bookingTime;

   @JsonProperty("api_name")
   private String apiName;

   @JsonProperty("api_id")
   private String apiId;

   @JsonProperty("time_limit")
   private String timeLimit;

   @JsonProperty("adult_pax")
   private String adultPax;

   @JsonProperty("child_pax")
   private String childPax;

   @JsonProperty("infant_pax")
   private String infantPax;

   @JsonProperty("full_name")
   private String fullName;


   private String phone;

   @JsonProperty("flight_from")
   private String flightFrom;

   @JsonProperty("flight_to")
   private String flightTo;

   private String airline;

   @JsonProperty("base_fare")
   private String baseFare;

   private String nta;

   @JsonProperty("total_price")
   private String totalPrice;
   private String tax;

   @JsonProperty("flight_list")
   private Set<FlightFlight> flights;

   @JsonProperty("passenger_list")
   private Set<FlightPassenger> passengers;

    public String getPaymentStatusCode() {
        return paymentStatusCode;
    }

    public void setPaymentStatusCode(String paymentStatusCode) {
        this.paymentStatusCode = paymentStatusCode;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getAdultPax() {
        return adultPax;
    }

    public void setAdultPax(String adultPax) {
        this.adultPax = adultPax;
    }

    public String getChildPax() {
        return childPax;
    }

    public void setChildPax(String childPax) {
        this.childPax = childPax;
    }

    public String getInfantPax() {
        return infantPax;
    }

    public void setInfantPax(String infantPax) {
        this.infantPax = infantPax;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFlightFrom() {
        return flightFrom;
    }

    public void setFlightFrom(String flightFrom) {
        this.flightFrom = flightFrom;
    }

    public String getFlightTo() {
        return flightTo;
    }

    public void setFlightTo(String flightTo) {
        this.flightTo = flightTo;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(String baseFare) {
        this.baseFare = baseFare;
    }

    public String getNta() {
        return nta;
    }

    public void setNta(String nta) {
        this.nta = nta;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public Set<FlightFlight> getFlights() {
        return flights;
    }

    public void setFlights(Set<FlightFlight> flights) {
        this.flights = flights;
    }

    public Set<FlightPassenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<FlightPassenger> passengers) {
        this.passengers = passengers;
    }

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
}
