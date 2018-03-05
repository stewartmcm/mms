package com.mms.manage_my_stuff.ui.roomlist;

import com.mms.manage_my_stuff.events.StartActivityEvent;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.models.Room;
import com.mms.manage_my_stuff.ui.RoomActivity;

import javax.inject.Inject;

public class RoomItemViewModel {

    private Room room;
    private UnboundViewEventBus eventBus;

    public RoomItemViewModel(Room room, UnboundViewEventBus eventBus) {
        this.room = room;
        this.eventBus = eventBus;
    }

    public String getTitle() {
        return room.getTitle();
    }

    public Room getRoom() {
        return room;
    }

    public void launchRoomDetails() {
        StartActivityEvent event = StartActivityEvent.build(this).activityName(RoomActivity.class);
        eventBus.send(event);
    }

    public static class Factory {
        UnboundViewEventBus eventBus;

        @Inject
        Factory(UnboundViewEventBus eventBus) {
            this.eventBus = eventBus;
        }

        public RoomItemViewModel newInstance(Room room) {
            return new RoomItemViewModel(room, eventBus);
        }
    }
}
