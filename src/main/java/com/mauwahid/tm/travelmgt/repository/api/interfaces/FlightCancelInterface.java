package com.mauwahid.tm.travelmgt.repository.api.interfaces;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightCancel;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightCancelReq;

import java.util.Map;

public interface FlightCancelInterface {

    FlightCancel cancelFlight(FlightCancelReq flightCancelReq);
}
