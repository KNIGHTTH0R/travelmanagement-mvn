package com.mauwahid.tm.travelmgt.utils;

public class StatusCode {



    //Standar HTTP Status
    public static int SUCCESS = 200;
    public static int NO_CONTENT = 204;
    public static int FORBIDDEN = 403;
    public static int BAD_REQUEST = 400;
    public static int SERVER_ERROR = 500;
    public static int NOT_IMPLEMENTED = 501;

    //Our custom error
    public static int ERROR_API = 001;
    public static int SESSION_NOT_FOUND = 002;

    public static String S_SUCCESS = "SUCCESS";
    public static String S_FORBIDDEN = "Authentication error";
    public static String S_NO_CONTENT = "No content available, Please check request parameter";
    public static String S_BAD_REQUEST = "Data format error";
    public static String S_SERVER_ERROR = "Server error";
    public static String S_ERROR_API = "Enpoint API Error";
    public static String S_SESSION_NOT_FOUND = "Session Not found";
    public static String S_NOT_IMPLEMENTED = "Method is not implemented, please check api source";


}
