package com.mms.manage_my_stuff.ui.roomlist;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mms.manage_my_stuff.BaseLifeCycleViewModel;
import com.mms.manage_my_stuff.events.StartActivityEvent;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.models.Room;
import com.mms.manage_my_stuff.ui.RoomActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RoomListViewModel extends BaseLifeCycleViewModel {

    protected RoomListAdapter roomListAdapter;
    protected RoomItemViewModel.Factory roomItemViewModelFactory;
    protected UnboundViewEventBus eventBus;

    private RecyclerView.LayoutManager layoutManager;
    private ItemTouchHelper itemTouchHelper;
    private FirebaseAuth auth;
    private DatabaseReference database;
    private ArrayList<String> defaultRooms = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();

    @Inject
    public RoomListViewModel(UnboundViewEventBus eventBus,  RoomItemViewModel.Factory roomItemViewModelFactory) {
        this.eventBus = eventBus;
        this.roomItemViewModelFactory = roomItemViewModelFactory;
        itemTouchHelper = initItemTouchHelper();

        roomListAdapter = new RoomListAdapter(this);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public RoomListAdapter getRoomListAdapter() {
        return new RoomListAdapter(this);
    }

    List<RoomItemViewModel> getRoomViewModelList() {
        List<RoomItemViewModel> roomViewModelList = new ArrayList<>();
        roomViewModelList.clear();
        defaultRooms.clear();
        rooms.clear();

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String userId = user.getUid();
        database = FirebaseDatabase.getInstance().getReference();

        defaultRooms.add("Kitchen");
        defaultRooms.add("Living Room");
        defaultRooms.add("Dining Room");
        defaultRooms.add("Bathroom");
        defaultRooms.add("Master Bedroom");

        for (int i = 0; i < defaultRooms.size(); i++) {
            Room room = new Room(defaultRooms.get(i), null, 0, false);
            rooms.add(room);
        }
        database.child("users").child(userId).setValue(rooms);

        for (Room room : rooms) {
            roomViewModelList.add(roomItemViewModelFactory.newInstance(room));
        }

        return roomViewModelList;
    }

    public ItemTouchHelper getItemTouchHelper() {
        return itemTouchHelper;
    }

    public ItemTouchHelper initItemTouchHelper() {
        return new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.UP | ItemTouchHelper.DOWN) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            }
        });
    }

    public void onItemSelected(Room room) {
        StartActivityEvent event = StartActivityEvent.build(this).activityName(RoomActivity.class);
        eventBus.send(event);
    }
}
