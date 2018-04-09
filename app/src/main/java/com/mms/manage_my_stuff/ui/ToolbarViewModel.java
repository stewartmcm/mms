package com.mms.manage_my_stuff.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;


public class ToolbarViewModel extends AndroidViewModel {

    public ObservableBoolean navigationIconVisibility = new ObservableBoolean(false);
//    public ObservableInt navigationIcon = new ObservableInt(R.drawable.ic_back_arrow_white);
    public ObservableField<String> title = new ObservableField<>();
    public ObservableBoolean loading = new ObservableBoolean(false);

    public ToolbarViewModel(@NonNull Application application) {
        super(application);
    }

//    public void showNavigationIcon() {
//        navigationIconVisibility.set(true);
//    }
//
//    public void hideNavigationIcon() {
//        navigationIconVisibility.set(false);
//    }

//    public void hideToolbar() {
//        navigationIconVisibility.set(false);
//    }
//
//    public void setNavigationIcon(int navigationIcon) {
//        this.navigationIcon.set(navigationIcon);
//    }

    public void setTitle(String title) {
        this.title.set(title);
    }


}
