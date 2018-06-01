package com.example.webprog26.bestalarm;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
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

    @Override
    protected View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_alarm, container, false);
    }

    @Override
    protected void initControlsListeners() {
        mBtnAlarmDone.setOnClickListener((v) -> {
            Log.i(MainActivity.MAIN_DEBUG, "alarm " + mTpAlarm.getHour() + ":" + mTpAlarm.getMinute());
        });
        mBtnCancel.setOnClickListener((v) -> {
            mainInteractor.loadMainFragment();
        });
        mSwIsVibrate.setOnCheckedChangeListener((button, isChecked) -> {
            button.setText(isChecked ? R.string.vibration_on: R.string.vibration_off);
        });
    }
}
