package com.mms.manage_my_stuff.ui.roomlist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
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
    private List<Room> firebaseRooms = new ArrayList<>();

    public RoomListViewModel(Application application) {
        super(application);
        auth = FirebaseAuth.getInstance();
        HOT_STOCK_REF = FirebaseDatabase.getInstance().getReference("/users/" + getUserId());
        liveData = new FirebaseQueryLiveData(HOT_STOCK_REF);

        initRooms();
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
            Room room = new Room(i, defaultRooms.get(i), new ArrayList<>(), 0, false);

            firebaseRooms.add(i, room);
        }
        database.child("users").child(userId).setValue(firebaseRooms);
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
