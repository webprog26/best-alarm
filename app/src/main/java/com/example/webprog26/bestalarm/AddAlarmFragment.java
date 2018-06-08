package com.example.webprog26.bestalarm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import butterknife.BindView;

/**
 * Created by webprog26 on 01.06.18.
 */

public class AddAlarmFragment extends BaseFragment {

    private static final String ALARM_ID = "alarm_id";
    public static final int NEW_ALARM_ID = -1;

    @BindView(R.id.tp_alarm)
    TimePicker mTpAlarm;

    @BindView(R.id.btn_done)
    ImageButton mBtnAlarmDone;

    @BindView(R.id.btn_cancel)
    ImageButton mBtnCancel;

    @BindView(R.id.sw_is_vibrate)
    SwitchCompat mSwIsVibrate;

    @BindView(R.id.sw_is_repeatable)
    SwitchCompat mSwIsRepeatable;

    @BindView(R.id.alarm_label_container)
    RelativeLayout mAlarmLabelContainer;

    @BindView(R.id.tv_alarm_label)
    TextView mTvAlarmLabel;

    public static AddAlarmFragment newInstance(final int alarmId) {
        Bundle args = new Bundle();
        args.putInt(ALARM_ID, alarmId);
        AddAlarmFragment addAlarmFragment = new AddAlarmFragment();
        addAlarmFragment.setArguments(args);
        return addAlarmFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle args = getArguments();

        if (args != null) {
            final int alarmId = args.getInt(ALARM_ID, NEW_ALARM_ID);

            if (alarmId != NEW_ALARM_ID) {
               if (alarmsViewModel != null) {
                   alarmsViewModel.getAlarmById(alarmId, new AlarmsViewModel.OnAlarmFoundByIdListener() {
                       @Override
                       public void onAlarmFound(Alarm alarm) {
                           if (alarm != null) {
                               mTpAlarm.setCurrentHour(alarm.getHours());
                               mTpAlarm.setCurrentMinute(alarm.getMinutes());
                               mSwIsVibrate.setChecked(alarm.isVibrate());
                               mSwIsRepeatable.setChecked(alarm.isRepeatable());
                               mTvAlarmLabel.setText(alarm.getLabel());
                           }
                       }

                       @Override
                       public void onError() {
                            Log.i(MainActivity.MAIN_DEBUG, "Error while loading alarm for editing");
                       }
                   });
               }
            }

            mBtnAlarmDone.setOnClickListener((v) -> {
                if (alarmId == NEW_ALARM_ID) {
                    alarmsViewModel.addAlarm(createAlarm());
                } else {
                    alarmsViewModel.updateAlarm(updateAlarm(alarmId));
                }

                mainInteractor.loadMainFragment();
            });
        }
    }

    @Override
    protected View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_alarm, container, false);
    }

    @Override
    protected void initControlsListeners() {

        mSwIsVibrate.setOnCheckedChangeListener((button, isChecked) -> {
            button.setText(isChecked ? R.string.vibration_on : R.string.vibration_off);
        });

        mSwIsRepeatable.setOnCheckedChangeListener((button, isChecked) -> {
            button.setText(isChecked ? R.string.repeat_on : R.string.repeat_off);
        });

        mBtnCancel.setOnClickListener((v) -> {
            mainInteractor.loadMainFragment();
        });

        mAlarmLabelContainer.setOnClickListener((v) -> {
            Log.i(MainActivity.MAIN_DEBUG, "Alarm label container clicked");
        });
    }

    private Alarm createAlarm(){
        final Alarm alarm = new Alarm();
        alarm.setLabel(String.valueOf(mTvAlarmLabel.getText()));
        alarm.setHours(mTpAlarm.getCurrentHour());
        alarm.setMinutes(mTpAlarm.getCurrentMinute());
        alarm.setVibrate(mSwIsVibrate.isChecked());
        alarm.setRepeatable(mSwIsRepeatable.isChecked());
        alarm.setActive(true);

        return alarm;
    }

    private Alarm updateAlarm(final int alarmId) {
        final Alarm alarm = new Alarm();

        alarm.setId(alarmId);
        alarm.setLabel(String.valueOf(mTvAlarmLabel.getText()));
        alarm.setHours(mTpAlarm.getCurrentHour());
        alarm.setMinutes(mTpAlarm.getCurrentMinute());
        alarm.setVibrate(mSwIsVibrate.isChecked());
        alarm.setRepeatable(mSwIsRepeatable.isChecked());
        alarm.setActive(true);

        return alarm;
    }
}
