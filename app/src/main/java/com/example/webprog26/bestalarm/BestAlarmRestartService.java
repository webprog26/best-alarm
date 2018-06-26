package com.example.webprog26.bestalarm;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * Created by webprog26 on 26.06.18.
 */

public class BestAlarmRestartService extends IntentService {

    private static final String TAG = "BestAlarmRestartService";

    public BestAlarmRestartService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(MainActivity.MAIN_DEBUG, "onHandleIntent");
        final List<Alarm> alarms = App.getAlarmsDatabase().getAlarmDao().getAlarmsList();
        Log.i(MainActivity.MAIN_DEBUG, "alarms " + alarms.size());
        final long systemReloadedTime = System.currentTimeMillis();
        for (final Alarm alarm : alarms) {
            alarm.setSystemTime(systemReloadedTime);
            AlarmSetter.setAlarm(alarm);
        }
    }
}
