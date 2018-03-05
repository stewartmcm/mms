package com.mms.manage_my_stuff.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.mms.manage_my_stuff.BaseViewModel;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.models.Box;
import com.mms.manage_my_stuff.models.Room;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BoxCountListViewModel extends BaseViewModel {

    protected Room room;
    private ArrayList<Box> boxes = new ArrayList<>();

    @Inject
    public BoxCountListViewModel(UnboundViewEventBus eventBus) {
        super(eventBus);
    }
    public BoxCountListAdapter getBoxCountListAdapter() {
        return new BoxCountListAdapter(this);
    }

    public List<BoxCountItemViewModel> getBoxCountList() {
//            boxes = room.getBoxes();
//            room = new Room("fix this", new Box[6], 0, false);

        List<BoxCountItemViewModel> boxCountItemViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            boxCountItemViewModelList.add(new BoxCountItemViewModel("box count" + i, eventBus));
        }

        return boxCountItemViewModelList;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void retrieveRoomDetailsAndUpdateBoxCountList() {
            boxes = room.getBoxes();
        }

    public static class Factory {

        private final UnboundViewEventBus eventBus;

        @Inject
        public Factory(UnboundViewEventBus eventBus) {
            this.eventBus = eventBus;
        }

        public BoxCountListViewModel newInstance(Box[] boxes) {
            return new BoxCountListViewModel(eventBus);
        }

    }
}
