package com.mauwahid.tm.travelmgt.domain.apimodel.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
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
}
