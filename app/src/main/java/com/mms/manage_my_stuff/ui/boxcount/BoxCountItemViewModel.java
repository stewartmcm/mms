package com.mms.manage_my_stuff.ui.boxcount;

import com.mms.manage_my_stuff.events.UnboundViewEventBus;

import javax.inject.Inject;

public class BoxCountItemViewModel {

    private String title;
    private UnboundViewEventBus eventBus;


    public BoxCountItemViewModel(String title, UnboundViewEventBus eventBus) {
        this.title = title;
        this.eventBus = eventBus;
    }

    public String getTitle() {
        return title;
    }

//    public void launchRoomDetails() {
//        .save(new RoomListUseCase(title));
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

        public BoxCountItemViewModel newInstance(String title) {
            return new BoxCountItemViewModel(title, eventBus);
        }
    }
}
