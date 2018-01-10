package com.managemystuff.manage_my_stuff.ui;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import javax.inject.Inject;


public class ToolbarViewModel extends BaseLifeCycleViewModel {

    public ObservableBoolean visible = new ObservableBoolean(false);
//    public ObservableInt navigationIcon = new ObservableInt(R.drawable.ic_up_arrow);
    public ObservableField<String> title = new ObservableField<>();
    public ObservableBoolean loading = new ObservableBoolean(false);

    @Inject
    ToolbarViewModel() { }

    void showToolbar() {
        visible.set(true);
    }

    void hideToolbar() {
        visible.set(false);
    }

//    void showNavigationIcon(int navigationIcon) {
//        this.navigationIcon.set(navigationIcon);
//    }

    void setTitle(String title) {
        this.title.set(title);
    }
}
