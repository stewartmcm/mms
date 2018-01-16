package com.mms.manage_my_stuff.ui;

import android.databinding.ObservableInt;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;

public class RoomMenuViewModel extends BaseLifeCycleViewModel {

    private UnboundViewEventBus eventBus;

    public ObservableInt staticText = new ObservableInt(R.string.kitchen_text);

    public RoomMenuViewModel() {
    }

    public void launchRoomContents() {

    }
}
