package com.mms.manage_my_stuff.ui.roomlist;

import com.mms.manage_my_stuff.RoomListUseCase;
import com.mms.manage_my_stuff.TransientDataProvider;
import com.mms.manage_my_stuff.events.StartActivityEvent;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.ui.RoomActivity;

import javax.inject.Inject;

public class RoomViewModel {

    private String title;
    private TransientDataProvider transientDataProvider;
    private UnboundViewEventBus eventBus;

    public RoomViewModel(String title, TransientDataProvider transientDataProvider, UnboundViewEventBus eventBus) {
        this.title = title;
        this.transientDataProvider =transientDataProvider;
        this.eventBus = eventBus;
    }

    public String getTitle() {
        return title;
    }

    public void launchRoomDetails() {
        transientDataProvider.save(new RoomListUseCase(title));

        StartActivityEvent event = StartActivityEvent.build(this).activityName(RoomActivity.class);
        eventBus.send(event);
    }

    public static class Factory {
        UnboundViewEventBus eventBus;
        TransientDataProvider transientDataProvider;

        @Inject
        Factory(UnboundViewEventBus eventBus, TransientDataProvider transientDataProvider) {
            this.eventBus = eventBus;
             this.transientDataProvider = transientDataProvider;
        }

        public RoomViewModel newInstance(String title, TransientDataProvider transientDataProvider) {
            return new RoomViewModel(title, transientDataProvider, eventBus);
        }
    }
}
