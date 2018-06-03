package com.example.webprog26.bestalarm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;


/**
 * Created by webprog26 on 01.06.18.
 */

public class MainFragment extends BaseFragment implements View.OnClickListener, MainFragmentView {

    public static final String MAIN_FRAGMENT_TAG = "main_fragment";

    @BindView(R.id.btn_add_alarm)
    Button mBtnAddAlarm;

    @BindView(R.id.btn_alarm_settings)
    Button mBtnAlarmSettings;

    private MainFragmentPresenter mainFragmentPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainFragmentPresenter = new MainFragmentPresenterImpl();
        mainFragmentPresenter.setMainFragmentView(this);

        if (alarmsViewModel != null) {
            alarmsViewModel.getListAlarms().observe(this, alarms -> {
                for (final Alarm alarm: alarms) {
                    Log.i(MainActivity.MAIN_DEBUG, alarm.toString());
                }
            });
        }
    }

    @Override
    protected View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    protected void initControlsListeners() {
        mBtnAddAlarm.setOnClickListener(this);
        mBtnAlarmSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       if(mainFragmentPresenter != null) {
           mainFragmentPresenter.controlClicked(view.getId());
       }
    }


    public interface MainFragmentPresenter {

        void setMainFragmentView(@NonNull final MainFragmentView mainFragmentView);

        void controlClicked(final int id);
    }

    @Override
    public void addAlarmClicked() {
        if (mainInteractor != null) {
            mainInteractor.showAddAlarmUi();
        }
    }

    @Override
    public void showAlarmSettingsClicked() {
        if (mainInteractor != null) {
            mainInteractor.showAlarmSettingsUi();
        }
    }
}
