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

    @Override
    protected View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_alarm, container, false);
    }

    @Override
    protected void initControlsListeners() {
        mBtnAlarmDone.setOnClickListener((v) -> {
            final Alarm alarm = createAlarm();
            alarmsViewModel.addAlarm(alarm);
            mainInteractor.loadMainFragment();
        });

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
        alarm.setLabel(String.valueOf(((TextView)mAlarmLabelContainer.findViewById(R.id.tv_alarm_label)).getText()));
        alarm.setHours(mTpAlarm.getCurrentHour());
        alarm.setMinutes(mTpAlarm.getCurrentMinute());
        alarm.setVibrate(mSwIsVibrate.isChecked());
        alarm.setRepeatable(mSwIsRepeatable.isChecked());
        alarm.setActive(true);

        return alarm;
    }
}
