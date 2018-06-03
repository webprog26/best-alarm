package com.example.webprog26.bestalarm;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by webprog26 on 01.06.18.
 */

public abstract class BaseFragment extends Fragment {

    protected Unbinder mUnbinder;
    protected MainInteractor mainInteractor;

    protected AlarmsViewModel alarmsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alarmsViewModel = ViewModelProviders.of(this).get(AlarmsViewModel.class);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainInteractor) {
            this.mainInteractor = (MainInteractor) context;
            return;
        }
        throw new IllegalArgumentException("Parent Activity must implement MainView interface");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View contentView = getContentView(inflater, container, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, contentView);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initControlsListeners();
    }

    protected abstract void initControlsListeners();

    @Override
    public void onDetach() {
        super.onDetach();
        if (mainInteractor != null) {
            mainInteractor = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    protected abstract View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState);

    public interface MainInteractor {
        void showAddAlarmUi();

        void showAlarmSettingsUi();

        void loadMainFragment();
    }
}
