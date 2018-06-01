package com.example.webprog26.bestalarm;

import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
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
                mainView.getMainFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(mainView.getContainerResId(), new AddAlarmFragment()).addToBackStack(null)
                        .commit();
        }
    }

    @Override
    public void loadAlarmSettingsView() {

    }

    @Override
    public void loadMainFragment() {
        if (mainView != null) {
            Log.i(MainActivity.MAIN_DEBUG, "items " + mainView.getMainFragmentManager().getBackStackEntryCount());
            mainView.getMainFragmentManager().popBackStack();
        }
    }
}
