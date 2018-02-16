package com.mms.manage_my_stuff.ui;

import android.databinding.BaseObservable;

public class ListItemViewModel extends BaseObservable {

    public String itemText;

    public ListItemViewModel(String room) {
        this.itemText = room;
    }

    public String getRoomListItemResId() {
        return itemText;
    }

}
