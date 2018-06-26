package com.example.webprog26.bestalarm;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by webprog26 on 03.06.18.
 */

@Dao
public interface AlarmDao {


    @Query("SELECT * from alarms")
    LiveData<List<Alarm>> getAllAlarms();

    @Query("SELECT * from alarms WHERE id = :id")
    Alarm getAlarmEntityById(final long id);

    @Insert
    long insertAlarm(final Alarm alarm);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAlarm(final Alarm alarm);

    @Delete
    void deleteAlarm(final Alarm alarm);
}
