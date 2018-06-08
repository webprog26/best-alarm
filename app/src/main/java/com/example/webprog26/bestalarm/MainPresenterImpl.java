package com.example.webprog26.bestalarm;

import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;

/**
 * Created by webprog26 on 01.06.18.
 */

public class MainPresenterImpl implements MainActivity.MainPresenter {

    private MainView mainView;

    @Override
    public void setMainView(@NonNull MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void loadAddAlarmView() {
        if (mainView != null) {

            loadAddAlarmFragment(mainView.getMainFragmentManager(),
                    mainView.getContainerResId(),
                    AddAlarmFragment.NEW_ALARM_ID);
        }
    }

    @Override
    public void loadAlarmSettingsView() {

    }

    @Override
    public void loadMainFragment() {
        if (mainView != null) {
            mainView.getMainFragmentManager().popBackStack();
        }
    }

    @Override
    public void loadAlarmEditor(int alarmId) {
        Log.i(MainActivity.MAIN_DEBUG, "loadAlarmEditor for alarm with id " + alarmId);
        loadAddAlarmFragment(mainView.getMainFragmentManager(),
                mainView.getContainerResId(),
                alarmId);
    }

    private static void loadAddAlarmFragment(@NonNull final FragmentManager fragmentManager,
                                             final int contanerResId,
                                             final int alarmId){
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(contanerResId, AddAlarmFragment.newInstance(alarmId)).addToBackStack(null)
                .commit();
    }
}
