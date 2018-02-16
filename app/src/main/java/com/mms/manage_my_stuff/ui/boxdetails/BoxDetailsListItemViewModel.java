package com.mms.manage_my_stuff.ui.boxdetails;

import android.databinding.BaseObservable;

public class BoxDetailsListItemViewModel extends BaseObservable {

    public String itemTitle;

    public BoxDetailsListItemViewModel(String item) {
        this.itemTitle = item;
    }

    public String getRoomListItemResId() {
        return itemTitle;
    }

}
