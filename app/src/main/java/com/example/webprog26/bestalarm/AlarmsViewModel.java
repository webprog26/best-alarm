package com.example.webprog26.bestalarm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by webprog26 on 03.06.18.
 */

public class AlarmsViewModel extends AndroidViewModel {

    private final LiveData<List<Alarm>> mListAlarms;

    public AlarmsViewModel(@NonNull Application application) {
        super(application);

        mListAlarms = App.getAlarmsDatabase().getAlarmDao().getAllAlarms();
    }

    public LiveData<List<Alarm>> getListAlarms() {
        return mListAlarms;
    }

    public void addAlarm(final Alarm alarm, final OnAlarmAddedListener listener) {
        new AddAlarmTask(App.getAlarmsDatabase(), listener).execute(alarm);
    }

    public void  getAlarmById(final int alarmId, final OnAlarmFoundByIdListener listener) {
        new AlarmByIdTask(App.getAlarmsDatabase(), listener).execute(alarmId);
    }

    public void updateAlarm(final Alarm alarm) {
        new UpdateAlarmTask(App.getAlarmsDatabase()).execute(alarm);
    }

    public void deleteAlarm(final Alarm alarm, final OnAlarmDeletedListener listener){
        new DeleteAlarmTask(App.getAlarmsDatabase(), listener).execute(alarm);
    }

    private static class AddAlarmTask extends AsyncTask<Alarm, Void, Long> {

        private final AlarmsDatabase alarmsDatabase;
        private final OnAlarmAddedListener listener;

        AddAlarmTask(AlarmsDatabase alarmsDatabase, OnAlarmAddedListener listener) {
            this.alarmsDatabase = alarmsDatabase;
            this.listener = listener;
        }

        @Override
        protected Long doInBackground(Alarm... alarms) {
            final Alarm alarm = alarms[0];
            final long addedAlarmId = alarmsDatabase.getAlarmDao().insertAlarm(alarm);
            return addedAlarmId;
        }

        @Override
        protected void onPostExecute(Long addedAlarmId) {
            super.onPostExecute(addedAlarmId);
            if (listener != null) {
                listener.onAlarmAdded(addedAlarmId);
            }
        }
    }

    private static class AlarmByIdTask extends AsyncTask<Integer, Void, Alarm> {

        private final AlarmsDatabase alarmsDatabase;
        private final OnAlarmFoundByIdListener listener;

        public AlarmByIdTask(AlarmsDatabase alarmsDatabase, OnAlarmFoundByIdListener listener) {
            this.alarmsDatabase = alarmsDatabase;
            this.listener = listener;
        }

        @Override
        protected Alarm doInBackground(Integer... ids) {
            return alarmsDatabase.getAlarmDao().getAlarmEntityById(ids[0]);
        }

        @Override
        protected void onPostExecute(Alarm alarm) {
            super.onPostExecute(alarm);

            if (listener != null) {
                if (alarm != null) {
                    listener.onAlarmFound(alarm);
                } else {
                    listener.onError();
                }
            }
        }
    }

    private static class UpdateAlarmTask extends AsyncTask<Alarm, Void, Void> {

        private final AlarmsDatabase alarmsDatabase;

        public UpdateAlarmTask(AlarmsDatabase alarmsDatabase) {
            this.alarmsDatabase = alarmsDatabase;
        }

        @Override
        protected Void doInBackground(Alarm... alarms) {
            alarmsDatabase.getAlarmDao().updateAlarm(alarms[0]);
            return null;
        }
    }

    private static class DeleteAlarmTask extends AsyncTask<Alarm, Void, Void> {

        private final AlarmsDatabase alarmsDatabase;
        private final OnAlarmDeletedListener onAlarmDeletedListener;

        public DeleteAlarmTask(AlarmsDatabase alarmsDatabase, OnAlarmDeletedListener listener) {
            this.alarmsDatabase = alarmsDatabase;
            this.onAlarmDeletedListener = listener;
        }

        @Override
        protected Void doInBackground(Alarm... alarms) {
            alarmsDatabase.getAlarmDao().deleteAlarm(alarms[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (onAlarmDeletedListener != null) {
                onAlarmDeletedListener.onAlarmDeleted();
            }
        }
    }

    public interface OnAlarmAddedListener {
        void onAlarmAdded(final long addedAlarmId);
    }

    public interface OnAlarmFoundByIdListener {
        void onAlarmFound(final Alarm alarm);

        void onError();
    }

    public interface OnAlarmDeletedListener{
        void onAlarmDeleted();
    }

}
