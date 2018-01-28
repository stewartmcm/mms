package com.mms.manage_my_stuff.ui;

import android.databinding.BaseObservable;

public class ListItemViewModel extends BaseObservable {

    public String roomListItemText;

    public ListItemViewModel(String room) {
        this.roomListItemText = room;
    }

    public String getRoomListItemResId() {
        return roomListItemText;
    }

}
