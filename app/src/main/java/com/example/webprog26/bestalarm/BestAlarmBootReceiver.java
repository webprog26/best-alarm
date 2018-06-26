package com.example.webprog26.bestalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by webprog26 on 26.06.18.
 */

public class BestAlarmBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, BestAlarmRestartService.class));
    }
}
