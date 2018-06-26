package com.example.webprog26.bestalarm;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by webprog26 on 23.06.18.
 */

public class TimeUtils {

    public static long getMillisToTrigger(final Alarm alarm, final Calendar calendar) {
        final Calendar alarmCalendar = Calendar.getInstance();
        alarmCalendar.set(Calendar.HOUR_OF_DAY, alarm.getHours());
        alarmCalendar.set(Calendar.MINUTE, alarm.getMinutes());
        alarmCalendar.set(Calendar.SECOND, 0);

        final long millisToTrigger = alarmCalendar.getTime().getTime() - calendar.getTime().getTime();

        if (millisToTrigger < 0) {
            return ((24 * 60 * 60) * 1000) - (millisToTrigger * -1);
        }

        return millisToTrigger;
    }
}
