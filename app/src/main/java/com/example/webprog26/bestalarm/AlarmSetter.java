package com.example.webprog26.bestalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by webprog26 on 23.06.18.
 */

public class AlarmSetter {

    public static final String ACTION_BEST_ALARM = "action_best_alarm";
    public static final String KEY_ALARM_ID = "alarm_id";

    public static void setAlarm(final Alarm alarm){
        final AlarmManager alarmManager = (AlarmManager) App.getAppContext().getSystemService(Context.ALARM_SERVICE);
        final int requestCode = alarm.getId();
        final Intent alarmIntent = new Intent();
        alarmIntent.setAction(ACTION_BEST_ALARM);
        alarmIntent.putExtra(KEY_ALARM_ID, requestCode);

        final PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(App.getAppContext(),
                requestCode,
                alarmIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        if (alarmManager != null) {

            final long triggerAtMillis = alarm.getSystemTime() + TimeUtils.getMillisToTrigger(alarm, Calendar.getInstance());
            Log.i(MainActivity.MAIN_DEBUG, "setAlarm " + triggerAtMillis);

            if (alarm.isRepeatable()) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                triggerAtMillis,
                        AlarmManager.INTERVAL_DAY, alarmPendingIntent);
                return;
            }

            alarmManager.setExact(AlarmManager.RTC_WAKEUP,
            triggerAtMillis, alarmPendingIntent);
        }
    }
}
