package com.mms.manage_my_stuff.ui;

import com.mms.manage_my_stuff.BaseViewModel;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ListViewModel extends BaseViewModel {

    @Inject
    public ListViewModel(UnboundViewEventBus eventBus) {
        super(eventBus);
    }

    public RoomListAdapter getRoomMenuAdapter() {
        return new RoomListAdapter(this);
    }

    public BoxTypeListAdapter getBoxSelectionAdapter() {
        return new BoxTypeListAdapter(this);
    }

    public BoxCountListAdapter getBoxCountAdapter() {
        return new BoxCountListAdapter(this);
    }

    public List<ListItemViewModel> getRoomMenuList() {
        List<ListItemViewModel> listItemViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            listItemViewModelList.add(new ListItemViewModel("Room " + i));
        }

        return listItemViewModelList;
    }

    public List<ListItemViewModel> getBoxSelectionList() {
        List<ListItemViewModel> boxSelectionItemViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            boxSelectionItemViewModelList.add(new ListItemViewModel("Box type " + i));
        }

        return boxSelectionItemViewModelList;
    }

    public List<ListItemViewModel> getBoxCountList() {
        List<ListItemViewModel> boxCountItemViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            boxCountItemViewModelList.add(new ListItemViewModel((6 - i) + " Box type " + i + " packed"));
        }

        return boxCountItemViewModelList;
    }

    public void launchRoomContents() {
        startFragment(RoomListFragment.class);
    }
}
