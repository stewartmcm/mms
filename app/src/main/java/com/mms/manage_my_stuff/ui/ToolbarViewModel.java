package com.mms.manage_my_stuff.ui;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.mms.manage_my_stuff.BaseLifeCycleViewModel;

import javax.inject.Inject;


public class ToolbarViewModel extends BaseLifeCycleViewModel {

    public ObservableBoolean visible = new ObservableBoolean(false);
    public ObservableField<String> title = new ObservableField<>();
    public ObservableBoolean loading = new ObservableBoolean(false);

    @Inject
    ToolbarViewModel() { }

    public void showToolbar() {
        visible.set(true);
    }

    public void hideToolbar() {
        visible.set(false);
    }

    public void setTitle(String title) {
        this.title.set(title);
    }
}
