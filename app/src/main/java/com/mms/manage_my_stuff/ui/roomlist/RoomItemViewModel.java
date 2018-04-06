package com.mms.manage_my_stuff.ui.roomlist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mms.manage_my_stuff.models.Room;

import javax.inject.Inject;

public class RoomItemViewModel extends ViewModel {

    private Room room;
    private MutableLiveData<String> currentRoomTitle;

    public RoomItemViewModel() {
    }

    public String getTitle() {
        return "Room Title";
    }

    public Room getRoom() {
        return room;
    }

    public MutableLiveData<String> getCurrentRoomTitle() {
        if (currentRoomTitle == null) {
            currentRoomTitle = new MutableLiveData<>();
            currentRoomTitle.setValue("Kitchen");
        }
        return currentRoomTitle;
    }

    public static class Factory {

        @Inject
        Factory() {
        }

        public RoomItemViewModel newInstance() {
            return new RoomItemViewModel();
        }
    }
}
