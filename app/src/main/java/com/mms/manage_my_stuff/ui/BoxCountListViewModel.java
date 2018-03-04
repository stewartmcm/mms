package com.mms.manage_my_stuff.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.mms.manage_my_stuff.BaseViewModel;
import com.mms.manage_my_stuff.LaunchRoomDetailsUseCase;
import com.mms.manage_my_stuff.TransientDataProvider;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.models.Box;
import com.mms.manage_my_stuff.models.Room;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BoxCountListViewModel extends BaseViewModel {

    private TransientDataProvider transientDataProvider;

    protected Room room;
    private ArrayList<Box> boxes = new ArrayList<>();

    @Inject
    public BoxCountListViewModel(UnboundViewEventBus eventBus, TransientDataProvider transientDataProvider) {
        super(eventBus);
        this.transientDataProvider = transientDataProvider;
    }
    public BoxCountListAdapter getBoxCountListAdapter() {
        return new BoxCountListAdapter(this);
    }

    public List<BoxCountItemViewModel> getBoxCountList() {
        if (transientDataProvider.containsUseCase(LaunchRoomDetailsUseCase.class)) {
            LaunchRoomDetailsUseCase launchRoomDetailsUseCase = transientDataProvider.get(LaunchRoomDetailsUseCase.class);
            room = launchRoomDetailsUseCase.getRoom();
            boxes = room.getBoxes();
        } else {
//            room = new Room("fix this", new Box[6], 0, false);
        }

        List<BoxCountItemViewModel> boxCountItemViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            boxCountItemViewModelList.add(new BoxCountItemViewModel(room.getTitle() + i, transientDataProvider, eventBus));
        }

        return boxCountItemViewModelList;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void retrieveRoomDetailsAndUpdateBoxCountList() {
        if (transientDataProvider.containsUseCase(LaunchRoomDetailsUseCase.class)) {
            LaunchRoomDetailsUseCase launchRoomDetailsUseCase = transientDataProvider.get(LaunchRoomDetailsUseCase.class);
            room = launchRoomDetailsUseCase.getRoom();
            boxes = room.getBoxes();
        }

    }

    public static class Factory {

        private final UnboundViewEventBus eventBus;
        private final TransientDataProvider transientDataProvider;

        @Inject
        public Factory(UnboundViewEventBus eventBus, TransientDataProvider transientDataProvider) {
            this.eventBus = eventBus;
            this.transientDataProvider = transientDataProvider;
        }

        public BoxCountListViewModel newInstance(Box[] boxes) {
            return new BoxCountListViewModel(eventBus, transientDataProvider);
        }

    }
}
