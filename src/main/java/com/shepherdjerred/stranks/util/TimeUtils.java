package com.shepherdjerred.stranks.util;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static long calculateRemainingCooldown(long cooldownStart) {
        Calendar calendar = Calendar.getInstance();
        calendar.getTime();
        return cooldownStart - calendar.getTimeInMillis();
    }

    public static String convertTimeInMillisToReadableString(Long timeInMillis) {

        long hours = TimeUnit.HOURS.convert(timeInMillis, TimeUnit.MILLISECONDS);
        timeInMillis -= TimeUnit.HOURS.toMillis(hours);

        long minutes = TimeUnit.MINUTES.convert(timeInMillis, TimeUnit.MILLISECONDS);
        timeInMillis -= TimeUnit.MINUTES.toMillis(minutes);

        long seconds = TimeUnit.SECONDS.convert(timeInMillis, TimeUnit.MILLISECONDS);

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
