package com.shepherdjerred.stranks.util;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TimeToString {

    public static String convertLong(Long timeInMillis) {

        Calendar calendar = Calendar.getInstance();
        calendar.getTime();
        long diffInMillis = timeInMillis - calendar.getTimeInMillis();

        long hours = TimeUnit.HOURS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        diffInMillis -= TimeUnit.HOURS.toMillis(hours);

        long minutes = TimeUnit.MINUTES.convert(diffInMillis, TimeUnit.MILLISECONDS);
        diffInMillis -= TimeUnit.MINUTES.toMillis(minutes);

        long seconds = TimeUnit.SECONDS.convert(diffInMillis, TimeUnit.MILLISECONDS);

        if (hours < 1 && minutes < 1 && seconds < 1)
            seconds = 1L;

        String string = "";

        if (hours > 0) {
            string = string.concat(String.valueOf(hours) + " hour");
            if (hours > 1)
                string = string.concat("s");
        }

        if (hours > 0 && minutes > 0)
            string = string.concat(", ");

        if (minutes > 0) {
            string = string.concat(String.valueOf(minutes) + " minute");
            if (minutes > 1)
                string = string.concat("s");
        }

        if (minutes > 0 && seconds > 0 || minutes == 0 && seconds > 0 && hours > 0)
            string = string.concat(", ");

        if (seconds > 0) {
            string = string.concat(String.valueOf(seconds) + " second");
            if (seconds > 1)
                string = string.concat("s");
        }

        return String.valueOf(string);
    }

}
