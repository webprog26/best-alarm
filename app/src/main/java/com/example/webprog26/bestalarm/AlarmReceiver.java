package com.example.webprog26.bestalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by webprog26 on 23.06.18.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            final String intentAction = intent.getAction();
            if (intentAction != null && intentAction.equals(AlarmSetter.ACTION_BEST_ALARM)) {
                final long alarmId = (long) intent.getIntExtra(AlarmSetter.KEY_ALARM_ID, -1);

                if (alarmId != -1) {
                    Log.i(MainActivity.MAIN_DEBUG, "Alarm fired with id " + alarmId);
                }
            }
        }
    }
}
