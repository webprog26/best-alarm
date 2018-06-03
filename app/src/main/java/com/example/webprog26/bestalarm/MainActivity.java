package com.example.webprog26.bestalarm;


import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements MainView, BaseFragment.MainInteractor{

    public static final String MAIN_DEBUG = "main_debug";

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mainPresenter = new MainPresenterImpl();
        mainPresenter.setMainView(this);

        if(savedInstanceState == null) {
            final FragmentManager fragmentManager = getSupportFragmentManager();
            final BaseFragment mainFragment = (BaseFragment) fragmentManager.findFragmentByTag(MainFragment.MAIN_FRAGMENT_TAG);

            if (mainFragment == null) {
                fragmentManager.beginTransaction().add(getContainerResId(), new MainFragment()).commit();
            }
        }
    }

    @Override
    public void showAddAlarmUi() {
        Log.i(MAIN_DEBUG, "showAddAlarmUi");
        if (mainPresenter != null) {
            mainPresenter.loadAddAlarmView();
        }
    }

    @Override
    public void showAlarmSettingsUi() {
        Log.i(MAIN_DEBUG, "showAlarmSettingsUi");
    }

    @Override
    public void loadMainFragment() {
        if (mainPresenter != null) {
            mainPresenter.loadMainFragment();
        }
    }

    @NonNull
    @Override
    public FragmentManager getMainFragmentManager() {
        return getSupportFragmentManager();
    }

    @Override
    public int getContainerResId() {
        return R.id.main_content;
    }

    public interface MainPresenter {
        void setMainView(@NonNull final MainView mainView);

        void loadAddAlarmView();

        void loadAlarmSettingsView();

        void loadMainFragment();
    }
}
