package com.mms.manage_my_stuff.ui.roomlist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mms.manage_my_stuff.FirebaseQueryLiveData;
import com.mms.manage_my_stuff.models.Room;

import java.util.ArrayList;
import java.util.List;

import static com.mms.manage_my_stuff.ui.roomlist.RoomListFragment.TAG;

public class RoomListViewModel extends AndroidViewModel {

    private FirebaseAuth auth;
    private final DatabaseReference HOT_STOCK_REF;
    private DatabaseReference database;
    private final FirebaseQueryLiveData liveData;

    private ArrayList<String> defaultRooms = new ArrayList<>();
    private LiveData<List<Room>> rooms;
    private List<Room> firebaseRooms = new ArrayList<>();
    private final MediatorLiveData<List<Room>> observableRooms;

    public RoomListViewModel(Application application) {
        super(application);
        auth = FirebaseAuth.getInstance();
        HOT_STOCK_REF = FirebaseDatabase.getInstance().getReference("/users/" + getUserId());
        liveData = new FirebaseQueryLiveData(HOT_STOCK_REF);

        observableRooms = new MediatorLiveData<>();
        observableRooms.setValue(null);

        initRooms();

        observableRooms.addSource(rooms, observableRooms::setValue);
    }

    public void initRooms() {
        defaultRooms.clear();
        firebaseRooms.clear();

        FirebaseUser user = auth.getCurrentUser();
        String userId = user.getUid();
        database = FirebaseDatabase.getInstance().getReference();

        defaultRooms.add("Mal's Room");
        defaultRooms.add("Rec Room");
        defaultRooms.add("Dining Room");
        defaultRooms.add("Russ's Room");
        defaultRooms.add("Master Bedroom");

        for (int i = 0; i < defaultRooms.size(); i++) {
            Room room = new Room(defaultRooms.get(i), new ArrayList<>(), 0, false);

            firebaseRooms.add(i, room);
        }
        database.child("users").child(userId).setValue(firebaseRooms);
    }

    public LiveData<List<Room>> getRooms() {
//        defaultRooms.clear();
        firebaseRooms.clear();

        if (rooms == null) {
            rooms = new MutableLiveData<List<Room>>();
        }

        FirebaseUser user = auth.getCurrentUser();
        String userId = user.getUid();
        database = FirebaseDatabase.getInstance().getReference();

        for (int i = 0; i < rooms.getValue().size(); i++) {
            Room room = new Room(defaultRooms.get(i), null, 0, false);

            firebaseRooms.add(i, room);
        }
//        database.child("users").child(userId).setValue(firebaseRooms);

        return rooms;
    }

    public String getUserId() {
        FirebaseUser user = auth.getCurrentUser();
        return user.getUid();
    }

    @NonNull
    public LiveData<DataSnapshot> getDataSnapShotLiveData() {
        return liveData;
    }

    public List<Room> convertSnapshotToRooms(DataSnapshot dataSnapshot) {
        if (dataSnapshot != null) {
            firebaseRooms.clear();
            for (DataSnapshot child : dataSnapshot.getChildren()) {
                Room room = child.getValue(Room.class);
                String title = room.getTitle();
                firebaseRooms.add(room);
                Log.i(TAG, "convertSnapshotToJSON: " + title);
            }
        }
        return firebaseRooms;
    }
}
