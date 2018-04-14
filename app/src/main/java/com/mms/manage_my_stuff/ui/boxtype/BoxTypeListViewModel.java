package com.mms.manage_my_stuff.ui.boxtype;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mms.manage_my_stuff.FirebaseQueryLiveData;
import com.mms.manage_my_stuff.models.Box;
import com.mms.manage_my_stuff.models.Room;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BoxTypeListViewModel extends AndroidViewModel {

    private final DatabaseReference roomQueryRef;
    private final FirebaseQueryLiveData liveData;

    private Room firebaseRoom;

    protected BoxTypeListAdapter boxTypeListAdapter;

    private BoxTypeItemViewModel.Factory boxTypeItemViewModelFactory;
    private List<Box> boxes;
    private List<BoxTypeItemViewModel> boxTypeItemViewModelList = new ArrayList<>();

    @Inject
    public BoxTypeListViewModel(Application application, final int roomId) {
        super(application);

        roomQueryRef = FirebaseDatabase.getInstance().getReference("/users/" + getUserId() + "/" + roomId);
        liveData = new FirebaseQueryLiveData(roomQueryRef);
    }

    public List<Box> getBoxTypeList() {
        List<Box> boxList = new ArrayList<>();

        boxList.add(new Box(0, 0,"Dish-pack; Drum; Barrel", "null",null,false,false));
        boxList.add(new Box(1, 0,"Carton: 1 1/2 cubic feet","null",null,false,false));
        boxList.add(new Box(2, 0,"Carton: 3 cubic feet","null",null, false,false));
        boxList.add(new Box(3, 0,"Carton: 4 1/2 cubic feet","null",null,false,false));
        boxList.add(new Box(4, 0,"Carton: 6 cubic feet","null",null,false,false));
        boxList.add(new Box(5, 0,"Corrugated Mirror Carton","null",null, false,false));

        return boxList;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void updateBoxTypeList(List<Box> boxes) {
        this.boxes = boxes;
        boxTypeItemViewModelList.clear();

        for (Box box : boxes) {
            boxTypeItemViewModelList.add(boxTypeItemViewModelFactory.newInstance(box.getType()));
        }

        boxTypeListAdapter.notifyDataSetChanged();
    }

    @NonNull
    LiveData<DataSnapshot> getDataSnapShotLiveData() {
        return liveData;
    }

    void convertSnapshotToRoom(DataSnapshot dataSnapshot) {
        if (dataSnapshot != null) {
            firebaseRoom = dataSnapshot.getValue(Room.class);
        }
    }

    public String getCurrentRoomType() {
        if (firebaseRoom == null) {
            return "null firebaseroom";
        }

        return firebaseRoom.getRoomType();
    }

    private String getUserId() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        return user.getUid();
    }

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
            return (T) new BoxTypeListViewModel(application, roomId);
        }

    }
}
