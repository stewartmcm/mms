package com.mms.manage_my_stuff.ui.boxdetails;

import android.databinding.BaseObservable;

import javax.inject.Inject;

public class BoxDetailsItemViewModel extends BaseObservable {

    private String title;
    private boolean isInBox;

    public BoxDetailsItemViewModel(String title, boolean isInBox) {
        this.title = title;
        this.isInBox = isInBox;
    }

    public String getRoomListItemTitle() {
        return title;
    }

    public static class Factory {

        @Inject
        Factory() {
        }

        public BoxDetailsItemViewModel newInstance(String title, boolean isInBox) {
            return new BoxDetailsItemViewModel(title, isInBox);
        }
    }

}
