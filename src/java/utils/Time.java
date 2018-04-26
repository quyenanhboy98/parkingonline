/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 *
 * @author Yuu
 */
public class Time implements Serializable {

    public static boolean compareWorkingTime(List<LocalTime> wkt) {
        LocalTime now = LocalTime.now();

        LocalTime start = wkt.get(0).minus(30, ChronoUnit.MINUTES);
        LocalTime end = wkt.get(1);

        if (start.compareTo(end) < 0) {
            return (start.compareTo(now) <= 0 && end.compareTo(now) >= 0);
        } else {
            return (start.compareTo(now) <= 0 || end.compareTo(now) >= 0);
        }
    }

    public static int getDurationTimeValue(String duration) {
        int hour = duration.indexOf("giờ");
        if (hour == -1) {
            duration = duration.replaceAll("\\D+", "");
            return Integer.parseInt(duration);
        } else {
            String hourDuration = duration.substring(0, hour);
            hourDuration = hourDuration.replaceAll("\\D+", "");

            String minsDuration = duration.substring(hour + 4);
            minsDuration = minsDuration.replaceAll("\\D+", "");

            return Integer.parseInt(hourDuration) * 60 + Integer.parseInt(minsDuration);
        }
    }
}
