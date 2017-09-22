package com.mauwahid.tm.travelmgt.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {



    //dd-MM-yyyy
    public static String toPointerStandar(String dateWindoFormat) throws ParseException{
        String NEW_FORMAT = "dd-MM-yyy";
        String OLD_FORMAT = "yyyy-MM-dd";

        String newDateString;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date date = null;

        date = sdf.parse(dateWindoFormat);


        sdf.applyPattern(NEW_FORMAT);
        return sdf.format(date);
    }
}
