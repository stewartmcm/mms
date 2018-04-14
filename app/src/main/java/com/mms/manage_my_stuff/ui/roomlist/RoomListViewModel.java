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

    private final FirebaseQueryLiveData liveData;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private ArrayList<String> defaultRooms = new ArrayList<>();
    private List<Room> firebaseRooms = new ArrayList<>();

    public RoomListViewModel(@NonNull Application application) {
        super(application);

        DatabaseReference roomsRef = FirebaseDatabase.getInstance().getReference("/users/" + getUserId());
        liveData = new FirebaseQueryLiveData(roomsRef);
        initRooms();
    }

    private void initRooms() {
        defaultRooms.clear();
        firebaseRooms.clear();

        defaultRooms.add("Mal's Room");
        defaultRooms.add("Rec Room");
        defaultRooms.add("Dining Room");
        defaultRooms.add("Russ's Room");
        defaultRooms.add("Master Bedroom");

        for (int i = 0; i < defaultRooms.size(); i++) {
            Room room = new Room(i, 0, defaultRooms.get(i), new ArrayList<>(), 0, false);
            firebaseRooms.add(i, room);
        }

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("users").child(getUserId()).setValue(firebaseRooms);
    }

    private String getUserId() {
        FirebaseUser user = auth.getCurrentUser();
        return user.getUid();
    }

    @NonNull
    LiveData<DataSnapshot> getDataSnapShotLiveData() {
        return liveData;
    }

    List<Room> convertSnapshotToRooms(DataSnapshot dataSnapshot) {
        if (dataSnapshot != null) {
            firebaseRooms.clear();
            for (DataSnapshot child : dataSnapshot.getChildren()) {
                Room room = child.getValue(Room.class);
                String title = room.getRoomType();
                firebaseRooms.add(room);
                Log.i(TAG, "convertSnapshotToJSON: " + title);
            }
        }
        return firebaseRooms;
    }
}
