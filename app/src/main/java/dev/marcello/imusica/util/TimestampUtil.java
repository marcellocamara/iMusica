package dev.marcello.imusica.util;

import java.util.Calendar;

/**
 * Marcello
 * 2019
 */

public class TimestampUtil {

    public static String convertTime(long unix){
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(unix*1000);
        String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
        String month;
        int mon = (date.get(Calendar.MONTH)) +1;
        if (String.valueOf(mon).length() == 1){
            month = "0" + String.valueOf(mon);
        }else {
            month = String.valueOf(mon);
        }
        String year = String.valueOf(date.get(Calendar.YEAR));
        return day + "/" + month + "/" + year;
    }

}