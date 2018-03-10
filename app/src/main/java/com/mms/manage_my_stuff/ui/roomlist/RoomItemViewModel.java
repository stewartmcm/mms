package com.mms.manage_my_stuff.ui.roomlist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mms.manage_my_stuff.events.StartActivityEvent;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.models.Room;
import com.mms.manage_my_stuff.ui.RoomActivity;

import javax.inject.Inject;

public class RoomItemViewModel extends ViewModel {

    private Room room;
    private UnboundViewEventBus eventBus;
    private MutableLiveData<String> currentRoomTitle;

    public RoomItemViewModel() {
    }

    public String getTitle() {
        return "Room Title";
    }

    public Room getRoom() {
        return room;
    }

    public void launchRoomDetails() {
        StartActivityEvent event = StartActivityEvent.build(this).activityName(RoomActivity.class);
        eventBus.send(event);
    }

    public MutableLiveData<String> getCurrentRoomTitle() {
        if (currentRoomTitle == null) {
            currentRoomTitle = new MutableLiveData<>();
            currentRoomTitle.setValue("Kitchen");
        }
        return currentRoomTitle;
    }

    public static class Factory {
        UnboundViewEventBus eventBus;

        @Inject
        Factory(UnboundViewEventBus eventBus) {
            this.eventBus = eventBus;
        }

        public RoomItemViewModel newInstance() {
            return new RoomItemViewModel();
        }
    }
}
