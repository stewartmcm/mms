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
    }

    public RoomMenuAdapter getRoomMenuAdapter() {
        return new RoomMenuAdapter(this);
    }

    public BoxSelectionAdapter getBoxSelectionAdapter() {
        return new BoxSelectionAdapter(this);
    }

    public BoxCountAdapter getBoxCountAdapter() {
        return new BoxCountAdapter(this);
    }

    public List<RoomMenuItemViewModel> getRoomMenuList() {
        List<RoomMenuItemViewModel> roomMenuItemViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            roomMenuItemViewModelList.add(new RoomMenuItemViewModel("Room " + i));
        }

        return roomMenuItemViewModelList;
    }

    public List<RoomMenuItemViewModel> getBoxSelectionList() {
        List<RoomMenuItemViewModel> boxSelectionItemViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            boxSelectionItemViewModelList.add(new RoomMenuItemViewModel("Box type " + i));
        }

        return boxSelectionItemViewModelList;
    }

    public List<RoomMenuItemViewModel> getBoxCountList() {
        List<RoomMenuItemViewModel> boxCountItemViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            boxCountItemViewModelList.add(new RoomMenuItemViewModel((6 - i) + " Box type " + i + " packed"));
        }

        return boxCountItemViewModelList;
    }

    public void launchRoomContents() {
        startFragment(RoomMenuFragment.class);
    }
}
