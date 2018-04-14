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
import com.mms.manage_my_stuff.models.Item;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

//TODO: refactor into separate view models
public class BoxDetailsListViewModel extends AndroidViewModel {

    protected BoxDetailsListAdapter boxDetailsListAdapter;

    private ArrayList<String> defaultItems = new ArrayList<>();
    private List<Item> firebaseItems = new ArrayList<>();

    private BoxDetailsItemViewModel.Factory boxDetailsItemViewModelFactory;
    private List<Item> items;
    private List<BoxDetailsItemViewModel> boxDetailsItemViewModelList = new ArrayList<>();

    private DatabaseReference boxQueryRef;
    private final FirebaseQueryLiveData liveData;
    private final int boxId;

    private Box firebaseBox;
    private DatabaseReference newBoxRef;

    @Inject
    public BoxDetailsListViewModel(Application application, final int boxId) {
        super(application);
        this.boxId = boxId;

//        boxQueryRef = FirebaseDatabase.getInstance().getReference("/users/" + getUserId() + "/" + boxId);
        initBox();
        liveData = new FirebaseQueryLiveData(newBoxRef);
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

    public List<Item> getItemsList() {

//        List<Item> kitchenItems = new ArrayList<>();
//        kitchenItems.add(new Item(0, 0,"Pots & Pans", false,false, false));
//        kitchenItems.add(new Item(1, 0,"Food", false, false, false));
//        kitchenItems.add(new Item(2, 0,"Dishes", false, false,false));
//
//        List<Item> livingRoomItems = new ArrayList<>();
//        livingRoomItems.add(new Item(0, 0,"Books", false, false,false));
//        livingRoomItems.add(new Item(1, 0,"Lamp", false, false,false));
//        livingRoomItems.add(new Item(2, 0,"Decorative Item", false, false,false));
//
//        List<Item> boxContentsItemViewModelList = new ArrayList<>();
//
//        String roomType = "kitchen";
//
//        switch (roomType) {
//            case "kitchen": boxContentsItemViewModelList.addAll(kitchenItems);
//            break;
//
//            case "livingroom": boxContentsItemViewModelList.addAll(livingRoomItems);
//            break;
//        }

        return firebaseItems;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void updateBoxDetailsList(List<Item> items) {
        this.items = items;
        boxDetailsItemViewModelList.clear();

        for (Item item : items) {
            boxDetailsItemViewModelList.add(boxDetailsItemViewModelFactory.newInstance(item.getTitle()));
        }

        boxDetailsListAdapter.notifyDataSetChanged();
    }

    private void initBox() {
        defaultItems.clear();
        firebaseItems.clear();

        defaultItems.add("forks");
        defaultItems.add("knives");
        defaultItems.add("spoons");
        defaultItems.add("tin foil");
        defaultItems.add("plates");

        for (int i = 0; i < defaultItems.size(); i++) {

            Item item = new Item(i, 0, defaultItems.get(i), false,false, false);
            firebaseItems.add(i, item);
        }

        Box box = new Box(0, 0, "null", "null", firebaseItems, false, false);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference boxesRef = db.child("boxes");

        newBoxRef = boxesRef.push();
        newBoxRef.setValue(box);
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
