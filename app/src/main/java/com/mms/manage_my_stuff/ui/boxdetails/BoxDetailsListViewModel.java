package com.mms.manage_my_stuff.ui.boxdetails;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.mms.manage_my_stuff.ui.ListItemViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

//TODO: refactor into separate view models
public class BoxDetailsListViewModel extends AndroidViewModel {

    @Inject
    public BoxDetailsListViewModel(Application application) {
        super(application);
    }

    public BoxDetailsListAdapter getBoxDetailsListAdapter() {
        return new BoxDetailsListAdapter(this);
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

//    public static class Factory {
//
//        private final UnboundViewEventBus eventBus;
//
//        @Inject
//        public Factory(UnboundViewEventBus eventBus) {
//            this.eventBus = eventBus;
//        }
//
//        public BoxDetailsListViewModel newInstance(String title) {
//            return new BoxDetailsListViewModel(eventBus);
//        }
//
//    }
}
