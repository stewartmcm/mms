package com.mms.manage_my_stuff.ui;

import android.databinding.BaseObservable;

public class RoomMenuItemViewModel extends BaseObservable {

    public String roomListItemText;

    public RoomMenuItemViewModel(String room) {
        this.roomListItemText = room;
    }

    public String getRoomListItemResId() {
        return roomListItemText;
    }

//    public boolean isValidRoomMenuItem() {
//        return roomListItemText > 0;
//    }
}
