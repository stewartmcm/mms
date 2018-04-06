package com.mms.manage_my_stuff.ui.boxdetails;

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
import com.mms.manage_my_stuff.models.PackedItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

//TODO: refactor into separate view models
public class BoxDetailsListViewModel extends AndroidViewModel {

    protected BoxDetailsListAdapter boxDetailsListAdapter;

    private BoxDetailsItemViewModel.Factory boxDetailsItemViewModelFactory;
    private List<PackedItem> packedItems;
    private List<BoxDetailsItemViewModel> boxDetailsItemViewModelList = new ArrayList<>();

    private final DatabaseReference boxQueryRef;
    private final FirebaseQueryLiveData liveData;
    private final int boxId;

    private Box firebaseBox;

    @Inject
    public BoxDetailsListViewModel(Application application, final int boxId) {
        super(application);
        this.boxId = boxId;

        boxQueryRef = FirebaseDatabase.getInstance().getReference("/users/" + getUserId() + "/" + boxId);
        liveData = new FirebaseQueryLiveData(boxQueryRef);
    }

    @NonNull
    LiveData<DataSnapshot> getDataSnapShotLiveData() {
        return liveData;
    }

    void convertSnapshotToBox(DataSnapshot dataSnapshot) {
        if (dataSnapshot != null) {
            firebaseBox = dataSnapshot.getValue(Box.class);
        }
    }

    public List<PackedItem> getPackedItemsList() {

        List<PackedItem> kitchenItems = new ArrayList<>();
        kitchenItems.add(new PackedItem(0, "Pots & Pans", false, false));
        kitchenItems.add(new PackedItem(1, "Food", false, false));
        kitchenItems.add(new PackedItem(2, "Dishes", false, false));

        List<PackedItem> livingRoomItems = new ArrayList<>();
        livingRoomItems.add(new PackedItem(0, "Books", false, false));
        livingRoomItems.add(new PackedItem(1, "Lamp", false, false));
        livingRoomItems.add(new PackedItem(2, "Decorative Item", false, false));

        List<PackedItem> boxContentsItemViewModelList = new ArrayList<>();

        String roomType = "kitchen";

        switch (roomType) {
            case "kitchen": boxContentsItemViewModelList.addAll(kitchenItems);
            break;

            case "livingroom": boxContentsItemViewModelList.addAll(livingRoomItems);
            break;
        }

        return boxContentsItemViewModelList;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void updateBoxDetailsList(List<PackedItem> packedItems) {
        this.packedItems = packedItems;
        boxDetailsItemViewModelList.clear();

        for (PackedItem packedItem : packedItems) {
            boxDetailsItemViewModelList.add(boxDetailsItemViewModelFactory.newInstance(packedItem.getTitle()));
        }

        boxDetailsListAdapter.notifyDataSetChanged();
    }

    private String getUserId() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        return user.getUid();
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final int boxId;

        @Inject
        public Factory(@NonNull Application application, int boxId) {
            this.application = application;
            this.boxId = boxId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new BoxDetailsListViewModel(application, boxId);
        }

    }
}
