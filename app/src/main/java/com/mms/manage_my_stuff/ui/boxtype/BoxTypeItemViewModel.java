package com.mms.manage_my_stuff.ui.boxtype;

import javax.inject.Inject;

public class BoxTypeItemViewModel {

    private String title;

    public BoxTypeItemViewModel(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static class Factory {

        @Inject
        Factory() {
        }

        public BoxTypeItemViewModel newInstance(String title) {
            return new BoxTypeItemViewModel(title);
        }
    }
}
