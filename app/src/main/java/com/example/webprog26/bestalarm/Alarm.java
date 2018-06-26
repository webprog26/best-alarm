package com.example.webprog26.bestalarm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by webprog26 on 03.06.18.
 */

@Entity(tableName = "alarms")
public class Alarm {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String label;

    private int hours;

    private int minutes;

    private boolean isVibrate;

    private boolean isRepeatable;

    private boolean isActive;

    private long systemTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public boolean isVibrate() {
        return isVibrate;
    }

    public void setVibrate(boolean vibrate) {
        isVibrate = vibrate;
    }

    public boolean isRepeatable() {
        return isRepeatable;
    }

    public void setRepeatable(boolean repeatable) {
        isRepeatable = repeatable;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(long systemTime) {
        this.systemTime = systemTime;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " with id " + getId() +
                " with label " + getLabel() +
                " at system time " + getSystemTime() +
                " with hours " + hours +
                " with minutes " + minutes +
                " is vibrate " + isVibrate +
                " is repeatable " + String.valueOf(isRepeatable) +
                " is active " + String.valueOf(isActive);
    }
}
