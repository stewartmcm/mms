package com.mms.manage_my_stuff.ui;

import com.mms.manage_my_stuff.BaseLifeCycleViewModel;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.ui.roomlist.RoomItemViewModel;

import javax.inject.Inject;

public class RoomViewModel extends BaseLifeCycleViewModel {

    protected UnboundViewEventBus eventBus;

    @Inject
    public RoomViewModel(UnboundViewEventBus eventBus, RoomItemViewModel.Factory roomItemViewModelFactory) {
        this.eventBus = eventBus;
    }

//    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
//        this.layoutManager = layoutManager;
//    }
//
//    public RecyclerView.LayoutManager getLayoutManager() {
//        return layoutManager;
//    }
//
//    public RoomListAdapter getRoomListAdapter() {
//        return new RoomListAdapter(this);
//    }
//
//    List<RoomItemViewModel> getRoomViewModelList() {
//        List<RoomItemViewModel> roomViewModelList = new ArrayList<>();
//        roomViewModelList.clear();
//        defaultRooms.clear();
//        rooms.clear();
//
//        auth = FirebaseAuth.getInstance();
//        FirebaseUser user = auth.getCurrentUser();
//        String userId = user.getUid();
//        database = FirebaseDatabase.getInstance().getReference();
//
//        defaultRooms.add("Kitchen");
//        defaultRooms.add("Living Room");
//        defaultRooms.add("Dining Room");
//        defaultRooms.add("Bathroom");
//        defaultRooms.add("Master Bedroom");
//
//        for (int i = 0; i < defaultRooms.size(); i++) {
//            Room room = new Room(defaultRooms.get(i), null, 0, false);
//            rooms.add(room);
//        }
//        database.child("users").child(userId).setValue(rooms);
//
//        for (Room room : rooms) {
//            roomViewModelList.add(roomItemViewModelFactory.newInstance(room,));
//        }
//
//        return roomViewModelList;
//    }
//
//    public ItemTouchHelper getItemTouchHelper() {
//        return itemTouchHelper;
//    }
//
//    public ItemTouchHelper initItemTouchHelper() {
//        return new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.UP | ItemTouchHelper.DOWN) {
//
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//            }
//        });
//    }
//
//    public void onItemSelected(Room room) {
//        StartActivityEvent event = StartActivityEvent.build(this).activityName(RoomActivity.class);
//        eventBus.send(event);
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    protected void updateRoomList(ArrayList<Room> rooms) {
//        this.rooms = rooms;
////        roomViewModelList.clear();
////
////        auth = FirebaseAuth.getInstance();
////        FirebaseUser user = auth.getCurrentUser();
////        String userId = user.getUid();
////        database = FirebaseDatabase.getInstance().getReference();
////
////        defaultRooms.add("Kitchen");
////        defaultRooms.add("Living Room");
////        defaultRooms.add("Dining Room");
////        defaultRooms.add("Bathroom");
////        defaultRooms.add("Master Bedroom");
////
////        for (int i = 0; i < defaultRooms.size(); i++) {
////            Room room = new Room(defaultRooms.get(i), null, 0, false);
////            rooms.add(room);
////        }
////        database.child("users").child(userId).setValue(rooms);
////
////        for (Room room : rooms) {
////            roomViewModelList.add(roomItemViewModelFactory.newInstance(room.getTitle(),));
////        }
////
////        roomListAdapter.notifyDataSetChanged();
//    }
}
