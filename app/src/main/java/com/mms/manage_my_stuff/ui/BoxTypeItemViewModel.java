package com.mms.manage_my_stuff.ui;

import com.mms.manage_my_stuff.TransientDataProvider;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;

import javax.inject.Inject;

public class BoxTypeItemViewModel {

    private String title;
    private TransientDataProvider transientDataProvider;
    private UnboundViewEventBus eventBus;

    public BoxTypeItemViewModel(String title, TransientDataProvider transientDataProvider, UnboundViewEventBus eventBus) {
        this.title = title;
        this.transientDataProvider =transientDataProvider;
        this.eventBus = eventBus;
    }

    public String getTitle() {
        return title;
    }

//    public void launchRoomDetails() {
//        transientDataProvider.save(new RoomListUseCase(title));
//
//        StartActivityEvent event = StartActivityEvent.build(this).activityName(RoomActivity.class);
//        eventBus.send(event);
//    }

    public static class Factory {
        UnboundViewEventBus eventBus;
        TransientDataProvider transientDataProvider;

        @Inject
        Factory(UnboundViewEventBus eventBus, TransientDataProvider transientDataProvider) {
            this.eventBus = eventBus;
             this.transientDataProvider = transientDataProvider;
        }

        public BoxTypeItemViewModel newInstance(String title, TransientDataProvider transientDataProvider) {
            return new BoxTypeItemViewModel(title, transientDataProvider, eventBus);
        }
    }
}
