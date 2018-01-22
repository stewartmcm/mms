package com.mms.manage_my_stuff.ui;

import com.mms.manage_my_stuff.BaseViewModel;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RoomMenuViewModel extends BaseViewModel {

//    protected UnboundViewEventBus eventBus;

    @Inject
    public RoomMenuViewModel(UnboundViewEventBus eventBus) {
        super(eventBus);
//        this.eventBus = eventBus;
    }

    public RoomMenuAdapter getAdapter() {
        return new RoomMenuAdapter(this);
    }

    public List<RoomMenuItemViewModel> getRoomMenuList() {
        List<RoomMenuItemViewModel> roomMenuItemViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            roomMenuItemViewModelList.add(new RoomMenuItemViewModel("Room" + i));
        }

        return roomMenuItemViewModelList;
    }

    public void launchRoomContents() {
        startFragment(RoomMenuFragment.class);
    }
}
