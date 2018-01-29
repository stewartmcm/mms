package com.mms.manage_my_stuff.ui.box_contents;

import android.databinding.BaseObservable;

public class BoxContentsListItemViewModel extends BaseObservable {

    public String itemTitle;

    public BoxContentsListItemViewModel(String item) {
        this.itemTitle = item;
    }

    public String getRoomListItemResId() {
        return itemTitle;
    }

}
