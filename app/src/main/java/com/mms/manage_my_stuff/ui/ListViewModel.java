package com.mms.manage_my_stuff.ui;

import com.mms.manage_my_stuff.BaseViewModel;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.ui.box_contents.BoxContentsListAdapter;
import com.mms.manage_my_stuff.ui.box_contents.BoxContentsListItemViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ListViewModel extends BaseViewModel {

    @Inject
    public ListViewModel(UnboundViewEventBus eventBus) {
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

    public BoxContentsListAdapter getBoxContentsListAdapter() {
        return new BoxContentsListAdapter(this);
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

    public List<BoxContentsListItemViewModel> getBoxContentsList() {
        List<BoxContentsListItemViewModel> kitchenItems = new ArrayList<>();
        kitchenItems.add(new BoxContentsListItemViewModel("Pots & Pans"));
        kitchenItems.add(new BoxContentsListItemViewModel("Food"));
        kitchenItems.add(new BoxContentsListItemViewModel("Dishes"));
        kitchenItems.add(new BoxContentsListItemViewModel("Glasses & Cups"));
        kitchenItems.add(new BoxContentsListItemViewModel("Silverware"));
        kitchenItems.add(new BoxContentsListItemViewModel("Knives & Utensils"));
        kitchenItems.add(new BoxContentsListItemViewModel("Plastics & Serving"));
        kitchenItems.add(new BoxContentsListItemViewModel("Kitchen Electrics"));
        kitchenItems.add(new BoxContentsListItemViewModel("Cleaning Supplies"));
        kitchenItems.add(new BoxContentsListItemViewModel("Miscellaneous"));

        List<BoxContentsListItemViewModel> livingRoomItems = new ArrayList<>();
        livingRoomItems.add(new BoxContentsListItemViewModel("Books"));
        livingRoomItems.add(new BoxContentsListItemViewModel("Lamp"));
        livingRoomItems.add(new BoxContentsListItemViewModel("Decorative Item"));

        List<BoxContentsListItemViewModel> boxContentsItemViewModelList = new ArrayList<>();

        String roomType = "kitchen";

        switch (roomType) {
            case "kitchen": boxContentsItemViewModelList.addAll(kitchenItems);
            break;

            case "livingroom": boxContentsItemViewModelList.addAll(livingRoomItems);
            break;
        }

        return boxContentsItemViewModelList;
    }

    public void launchRoomContents() {
        startFragment(RoomListFragment.class);
    }
}
