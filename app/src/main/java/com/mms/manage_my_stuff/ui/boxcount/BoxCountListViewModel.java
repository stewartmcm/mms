package com.mms.manage_my_stuff.ui.boxcount;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mms.manage_my_stuff.FirebaseQueryLiveData;
import com.mms.manage_my_stuff.models.Room;

import javax.inject.Inject;

public class BoxCountListViewModel extends AndroidViewModel {

    private final DatabaseReference roomQueryRef;
    private final FirebaseQueryLiveData liveData;
    private final int roomId;

    private Room firebaseRoom;

    @Inject
    public BoxCountListViewModel(Application application, final int roomId) {
        super(application);
        this.roomId = roomId;

        roomQueryRef = FirebaseDatabase.getInstance().getReference("/users/" + getUserId() + "/" + roomId);
        liveData = new FirebaseQueryLiveData(roomQueryRef);
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

    public String getCurrentRoomTitle() {
        if (firebaseRoom == null) {
            return "null firebaseroom";
        }

        return firebaseRoom.getTitle();
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
            return (T) new BoxCountListViewModel(application, roomId);
        }

    }
}
