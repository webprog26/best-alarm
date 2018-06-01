package com.example.webprog26.bestalarm;

import android.support.annotation.NonNull;

/**
 * Created by webprog26 on 01.06.18.
 */

public class MainFragmentPresenterImpl implements MainFragment.MainFragmentPresenter {

    private MainFragmentView mainFragmentView;

    @Override
    public void setMainFragmentView(@NonNull final MainFragmentView mainFragmentView) {
        this.mainFragmentView = mainFragmentView;
    }

    @Override
    public void controlClicked(int id) {

        if (mainFragmentView != null) {
            switch (id) {
                case R.id.btn_add_alarm:
                    mainFragmentView.addAlarmClicked();
                    break;
                case R.id.btn_alarm_settings:
                    mainFragmentView.showAlarmSettingsClicked();
                    break;
            }
        }
    }
}
