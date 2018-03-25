package com.mms.manage_my_stuff.ui.boxcount;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mms.manage_my_stuff.FirebaseQueryLiveData;
import com.mms.manage_my_stuff.models.Box;
import com.mms.manage_my_stuff.models.Room;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BoxCountListViewModel extends AndroidViewModel {

    private FirebaseAuth auth;
    private final DatabaseReference HOT_STOCK_REF;
    private DatabaseReference database;
    private final FirebaseQueryLiveData liveData;


//    protected Room room;
    private final int roomId;
    private ArrayList<Box> boxes;

    private LiveData<Room> liveRoom;
    private final MediatorLiveData<Room> observableRoom;


    public ObservableField<Room> room = new ObservableField<>();

//    private BoxCountItemViewModel.Factory boxCountItemViewModelFactory;

    @Inject
    public BoxCountListViewModel(Application application, final int roomId) {
        super(application);
        this.roomId = roomId;

        auth = FirebaseAuth.getInstance();
        HOT_STOCK_REF = FirebaseDatabase.getInstance().getReference("/users/" + getUserId());
        liveData = new FirebaseQueryLiveData(HOT_STOCK_REF);

        observableRoom = new MediatorLiveData<>();
        observableRoom.setValue(null);
        observableRoom.addSource(liveRoom, observableRoom::setValue);
    }

//    public BoxCountListAdapter getBoxCountListAdapter() {
//        return new BoxCountListAdapter();
//    }

    public List<Box> getBoxCountList() {
//            boxes = room.getBoxes();
//            room = new Room("fix this", new Box[6], 0, false);

        List<Box> boxCountItemViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            boxCountItemViewModelList.add(new Box(i, "Box" + i, new ArrayList<>(), 5, false, false));
        }

        return boxCountItemViewModelList;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void retrieveRoomDetailsAndUpdateBoxCountList() {
        boxes = room.get().getBoxes();
    }

    public String getCurrentRoomTitle() {
        return room.get().getTitle();
    }

    public String getUserId() {
        FirebaseUser user = auth.getCurrentUser();
        return user.getUid();
    }

//    public MutableLiveData<String> getCurrentRoom() {
//        if (currentRoom == null) {
//            currentRoom = new MutableLiveData<>();
//            currentRoom.setValue("Kitchen");
//        }
//        return currentRoom;
//    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final int roomId;

        @Inject
        public Factory(@NonNull Application application, int roomId) {
            this.application = application;
            this.roomId = roomId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new BoxCountListViewModel(application, roomId);
        }

    }
}
