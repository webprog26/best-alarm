package com.example.webprog26.bestalarm;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by webprog26 on 03.06.18.
 */

public class App extends Application {

    private static AlarmsDatabase mAlarmsDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mAlarmsDatabase = Room.databaseBuilder(this, AlarmsDatabase.class, "database").build();
    }

    public static AlarmsDatabase getAlarmsDatabase() {
        return mAlarmsDatabase;
    }
}
