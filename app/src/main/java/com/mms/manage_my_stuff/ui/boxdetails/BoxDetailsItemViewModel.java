package com.mms.manage_my_stuff.ui.boxdetails;

import android.databinding.BaseObservable;

import javax.inject.Inject;

public class BoxDetailsItemViewModel extends BaseObservable {

    public String itemTitle;

    public BoxDetailsItemViewModel(String item) {
        this.itemTitle = item;
    }

    public String getRoomListItemResId() {
        return itemTitle;
    }

    public static class Factory {

        @Inject
        Factory() {
        }

        public BoxDetailsItemViewModel newInstance(String title) {
            return new BoxDetailsItemViewModel(title);
        }
    }

}
