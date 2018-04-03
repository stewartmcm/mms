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

//    public void launchRoomDetails() {
//
//        StartActivityEvent event = StartActivityEvent.build(this).activityName(RoomActivity.class);
//        eventBus.send(event);
//    }

    public static class Factory {

        @Inject
        Factory() {
        }

        public BoxTypeItemViewModel newInstance(String title) {
            return new BoxTypeItemViewModel(title);
        }
    }
}
