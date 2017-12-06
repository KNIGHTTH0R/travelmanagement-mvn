package com.mauwahid.tm.travelmgt.repository.api.interfaces;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightCancel;

import java.util.Map;

public interface FlightCancelInterface {

    FlightCancel cancelFlight(Map params);
}
