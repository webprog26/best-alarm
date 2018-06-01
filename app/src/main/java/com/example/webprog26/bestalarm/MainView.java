package com.example.webprog26.bestalarm;

import android.app.FragmentManager;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

/**
 * Created by webprog26 on 01.06.18.
 */

public interface MainView {

    @NonNull
    FragmentManager getMainFragmentManager();

    @IdRes
    int getContainerResId();
}
