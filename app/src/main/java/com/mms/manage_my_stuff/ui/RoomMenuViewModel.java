package com.mms.manage_my_stuff.ui;

import com.mms.manage_my_stuff.BaseViewModel;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;

import javax.inject.Inject;

public class RoomMenuViewModel extends BaseViewModel {

    protected UnboundViewEventBus eventBus;

    @Inject
    public RoomMenuViewModel(UnboundViewEventBus eventBus) {
        super(eventBus);
        this.eventBus = eventBus;
    }

    public void launchRoomContents() {
        startFragment(RoomMenuFragment.class);
    }
}
