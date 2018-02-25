package com.mms.manage_my_stuff.ui.boxdetails;

import com.mms.manage_my_stuff.BaseViewModel;
import com.mms.manage_my_stuff.TransientDataProvider;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.ui.ListItemViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

//TODO: refactor into separate view models
public class BoxDetailsListViewModel extends BaseViewModel {

    @Inject
    public BoxDetailsListViewModel(UnboundViewEventBus eventBus, TransientDataProvider transientDataProvider) {
        super(eventBus);
    }

//    public RoomListAdapter getRoomListAdapter() {
//        return new RoomListAdapter(this);
//    }
//
//    public BoxTypeListAdapter getBoxTypeListAdapter() {
//        return new BoxTypeListAdapter(this);
//    }
//
//    public BoxCountListAdapter getBoxCountListAdapter() {
//        return new BoxCountListAdapter(this);
//    }
//
    public BoxDetailsListAdapter getBoxDetailsListAdapter() {
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

    public List<ListItemViewModel> getBoxDetailsList() {
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

    public static class Factory {

        private final UnboundViewEventBus eventBus;
        private final TransientDataProvider transientDataProvider;

        @Inject
        public Factory(UnboundViewEventBus eventBus, TransientDataProvider transientDataProvider) {
            this.eventBus = eventBus;
            this.transientDataProvider = transientDataProvider;
        }

        public BoxDetailsListViewModel newInstance(String title, TransientDataProvider transientDataProvider) {
            return new BoxDetailsListViewModel(eventBus, transientDataProvider);
        }

    }
}
