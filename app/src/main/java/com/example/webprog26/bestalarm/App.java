package com.example.webprog26.bestalarm;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by webprog26 on 03.06.18.
 */

public class App extends Application {

    private static AlarmsDatabase mAlarmsDatabase;
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
        mAlarmsDatabase = Room.databaseBuilder(this, AlarmsDatabase.class, "database").build();
    }

    public static AlarmsDatabase getAlarmsDatabase() {
        return mAlarmsDatabase;
    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
