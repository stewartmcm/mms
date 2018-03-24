package com.mms.manage_my_stuff.ui.boxcount;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.mms.manage_my_stuff.models.Box;
import com.mms.manage_my_stuff.models.Room;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BoxCountListViewModel extends AndroidViewModel {

    protected Room room;
    private ArrayList<Box> boxes;

//    private BoxCountItemViewModel.Factory boxCountItemViewModelFactory;

    @Inject
    public BoxCountListViewModel(Application application) {
        super(application);
    }

//    public BoxCountListAdapter getBoxCountListAdapter() {
//        return new BoxCountListAdapter();
//    }

    public List<Box> getBoxCountList() {
//            boxes = room.getBoxes();
//            room = new Room("fix this", new Box[6], 0, false);

        List<Box> boxCountItemViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            boxCountItemViewModelList.add(new Box(i, "Box" + i, null, 5, false, false));
        }

        return boxCountItemViewModelList;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void retrieveRoomDetailsAndUpdateBoxCountList() {
        boxes = room.getBoxes();
    }

    public String getCurrentRoomTitle() {
        return room.getTitle();
    }

//    public MutableLiveData<String> getCurrentRoom() {
//        if (currentRoom == null) {
//            currentRoom = new MutableLiveData<>();
//            currentRoom.setValue("Kitchen");
//        }
//        return currentRoom;
//    }

//    public static class Factory {
//
//        private final UnboundViewEventBus eventBus;
//
//        @Inject
//        public Factory(UnboundViewEventBus eventBus) {
//            this.eventBus = eventBus;
//        }
//
//        public BoxCountListViewModel newInstance(Box[] boxes) {
//            return new BoxCountListViewModel(eventBus);
//        }
//
//    }
}
