package com.mauwahid.tm.travelmgt.utils;

import com.mauwahid.tm.travelmgt.domain.api.response.Response;
import com.mauwahid.tm.travelmgt.domain.apimodel.Status;

public class ApiStatic {


    public static String API_FLIGHT = "API_FLIGHT";
    public static String API_HOTEL = "API_HOTEL";

    public static String FLIGHT_GET_AIRLINES = "GetAirlines";
    public static String FLIGHT_GET_AIRPORTS = "ApiGetAirports";
    public static String FLIGHT_GET_ROUTES = "GetRoutes";
    public static String FLIGHT_GET_POPULAR_ROUTES = "GetPopularRoutes";
    public static String FLIGHT_SEARCH = "Search";
    public static String FLIGHT_DETAIL = "detail";
    public static String FLIGHT_BOOK = "book";
    public static String FLIGHT_ISSUE = "issue";
    public static String FLIGHT_TRANSACTION_LIST = "transaction_list";
    public static String FLIGHT_TRANSACTION_DETAIL = "transaction_detail";
    public static String FLIGHT_TRANSACTION_STATUS = "trasaction_status";

    public static final int FLIGHT_DOMESTIC = 1;
    public static final int FLIGHT_INTERNATIONAL = 2;
    public static final int FLIGHT_ALL = 3;


    public static Response GenErrorParam(){
        Status status = new Status();

        status.setStatusId(StatusCode.API_ERROR_PARAM);
        status.setStatusDesc(StatusCode.S_API_ERROR_PARAM);

        return  new Response(status);
    }



}
