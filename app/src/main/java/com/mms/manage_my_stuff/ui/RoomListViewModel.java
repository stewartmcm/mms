package com.mms.manage_my_stuff.ui;

import com.mms.manage_my_stuff.BaseViewModel;
import com.mms.manage_my_stuff.events.StartActivityEvent;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.ui.boxcontents.BoxDetailsListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

//TODO: refactor into separate view models
public class RoomListViewModel extends BaseViewModel {

    @Inject
    public RoomListViewModel(Room[] rooms, UnboundViewEventBus eventBus, TransientDataProvider transientDataProvider) {
        super(eventBus);
    }

    public RoomListAdapter getRoomListAdapter() {
        return new RoomListAdapter(this);
    }

    public BoxTypeListAdapter getBoxTypeListAdapter() {
        return new BoxTypeListAdapter(this);
    }

    public BoxCountListAdapter getBoxCountListAdapter() {
        return new BoxCountListAdapter(this);
    }

    public BoxDetailsListAdapter getBoxContentsListAdapter() {
        return new BoxDetailsListAdapter(this);
    }

    public List<ListItemViewModel> getRoomList() {
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

    public List<ListItemViewModel> getBoxContentsList() {
        List<ListItemViewModel> kitchenItems = new ArrayList<>();
        kitchenItems.add(new ListItemViewModel("Pots & Pans"));
        kitchenItems.add(new ListItemViewModel("Food"));
        kitchenItems.add(new ListItemViewModel("Dishes"));

        List<ListItemViewModel> livingRoomItems = new ArrayList<>();
        livingRoomItems.add(new ListItemViewModel("Books"));
        livingRoomItems.add(new ListItemViewModel("Lamp"));
        livingRoomItems.add(new ListItemViewModel("Decorative Item"));

        List<ListItemViewModel> boxContentsItemViewModelList = new ArrayList<>();

        String roomType = "kitchen";

        switch (roomType) {
            case "kitchen": boxContentsItemViewModelList.addAll(kitchenItems);
            break;

            case "livingroom": boxContentsItemViewModelList.addAll(livingRoomItems);
            break;
        }

        return boxContentsItemViewModelList;
    }

    public void launchRoomDetails() {
        transientDataProvider.save(this, Extras.Room, roomInfo.getRoomName());

        StartActivityEvent event = StartActivityEvent.build(this).activityName(RoomActivity.class);
        eventBus.send(event);
    }

    public static class Factory {

        private final UnboundViewEventBus eventBus;
        private final TransientDataProvider transientDataProvider;

        @Inject
        public Factory(UnboundViewEventBus eventBus, TransientDataProvider transientDataProvider) {
            this.eventBus = eventBus;
            this.transientDataProvider = transientDataProvider;
        }

        public RoomListViewModel newInstance(Room[] rooms) {
            return new RoomListViewModel(rooms, eventBus, transientDataProvider);
        }

    }
}
