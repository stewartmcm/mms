package com.mms.manage_my_stuff.ui;

import com.mms.manage_my_stuff.events.UnboundViewEventBus;

import javax.inject.Inject;

public class BoxTypeItemViewModel {

    private String title;
    private UnboundViewEventBus eventBus;

    public BoxTypeItemViewModel(String title, UnboundViewEventBus eventBus) {
        this.title = title;
        this.eventBus = eventBus;
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
        UnboundViewEventBus eventBus;

        @Inject
        Factory(UnboundViewEventBus eventBus) {
            this.eventBus = eventBus;
        }

        public BoxTypeItemViewModel newInstance(String title) {
            return new BoxTypeItemViewModel(title, eventBus);
        }
    }
}
