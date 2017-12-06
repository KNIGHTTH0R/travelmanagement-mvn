package com.mauwahid.tm.travelmgt.repository.api.interfaces;

import com.mauwahid.tm.travelmgt.domain.api.response.FlightReservationStatusResponse;

import java.util.Map;

public interface FlightReservationStatusInterface {

    FlightReservationStatusResponse cekStatus(Map params);
}
