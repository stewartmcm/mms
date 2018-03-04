package com.mms.manage_my_stuff.ui.roomlist;

import com.mms.manage_my_stuff.LaunchRoomDetailsUseCase;
import com.mms.manage_my_stuff.TransientDataProvider;
import com.mms.manage_my_stuff.events.StartActivityEvent;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.models.Room;
import com.mms.manage_my_stuff.ui.BoxCountListViewModel;
import com.mms.manage_my_stuff.ui.RoomActivity;

import javax.inject.Inject;

public class RoomItemViewModel {

    //    private String title;
    private Room room;
    protected BoxCountListViewModel.Factory boxCountListViewModelFactory;
    private final TransientDataProvider transientDataProvider;
    private UnboundViewEventBus eventBus;

    public RoomItemViewModel(Room room, TransientDataProvider transientDataProvider, UnboundViewEventBus eventBus) {
        this.room = room;
        this.transientDataProvider = transientDataProvider;
        this.eventBus = eventBus;
    }

    public String getTitle() {
        return room.getTitle();
    }

    public Room getRoom() {
        return room;
    }

    public void launchRoomDetails(Room room) {
        transientDataProvider.save(new LaunchRoomDetailsUseCase(room));

        StartActivityEvent event = StartActivityEvent.build(this).activityName(RoomActivity.class);
        eventBus.send(event);
    }

//    public BoxCountListViewModel getBoxCountListViewModel() {
//        BoxCountListViewModel boxCountListViewModel = boxCountListViewModelFactory.newInstance(room.getBoxes());
//        return boxCountListViewModel;
//    }

    public static class Factory {
        UnboundViewEventBus eventBus;
        TransientDataProvider transientDataProvider;

        @Inject
        Factory(UnboundViewEventBus eventBus, TransientDataProvider transientDataProvider) {
            this.eventBus = eventBus;
            this.transientDataProvider = transientDataProvider;
        }

        public RoomItemViewModel newInstance(Room room, TransientDataProvider transientDataProvider) {
            return new RoomItemViewModel(room, transientDataProvider, eventBus);
        }
    }
}
