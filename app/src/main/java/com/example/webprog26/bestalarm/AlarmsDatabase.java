package com.example.webprog26.bestalarm;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by webprog26 on 03.06.18.
 */
@Database(entities = {Alarm.class}, version = 1, exportSchema = false)
public abstract class AlarmsDatabase extends RoomDatabase{

    public abstract AlarmDao getAlarmDao();
}
