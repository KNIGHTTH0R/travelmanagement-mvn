package com.mauwahid.tm.travelmgt.repository.api.interfaces;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.flight.FlightIssue;

import java.util.Map;

public interface FlightIssueInterface{

    FlightIssue issueFlight(Map params);
}
