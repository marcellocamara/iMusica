package dev.marcello.imusica.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Marcello
 * 2019
 */

public class TimestampUtil {

    //Added unit test TimestampUtilTest
    public static String convertTime(long unix){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(new Date(unix*1000));
    }

}