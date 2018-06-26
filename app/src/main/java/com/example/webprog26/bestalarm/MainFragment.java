package com.example.webprog26.bestalarm;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    @BindView(R.id.rv_alarms)
    RecyclerView mRvAlarms;

    private MainFragmentPresenter mainFragmentPresenter;
    private AlarmsAdapter mAlarmsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainFragmentPresenter = new MainFragmentPresenterImpl();
        mainFragmentPresenter.setMainFragmentView(this);

        final Context activityContext = getActivity();

        if (activityContext != null) {
            this.mAlarmsAdapter = new AlarmsAdapter(activityContext, (alarmId) -> {
                mainFragmentPresenter.loadUiToEditAlarm(alarmId);
            });
        }

        if (alarmsViewModel != null) {
            alarmsViewModel.getListAlarms().observe(this, alarms -> {
                for (final Alarm alarm: alarms) {
                    Log.i(MainActivity.MAIN_DEBUG, "alarms count = " + alarms.size());
                    Log.i(MainActivity.MAIN_DEBUG, alarm.toString());
                }

                if (mAlarmsAdapter != null) {
                    mAlarmsAdapter.updateAlarmList(alarms);
                } else {
                    Log.i(MainActivity.MAIN_DEBUG, "mAlarmsAdapter is null");
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
        mRvAlarms.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvAlarms.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRvAlarms.setItemAnimator(new DefaultItemAnimator());
        mRvAlarms.setAdapter(mAlarmsAdapter);
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

        void loadUiToEditAlarm(final int alarmId);
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



    @Override
    public void loadAlarmEditor(int alarmId) {
        if (mainInteractor != null) {
            mainInteractor.loadAlarmEditor(alarmId);
        }
    }

    public interface OnAlarmClickedListener{
        void onAlarmClicked(final int alarmId);
    }
}
