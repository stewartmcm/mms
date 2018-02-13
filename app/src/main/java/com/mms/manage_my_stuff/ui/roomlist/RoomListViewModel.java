package com.mms.manage_my_stuff.ui.roomlist;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mms.manage_my_stuff.BaseFragment;
import com.mms.manage_my_stuff.BaseViewModel;
import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.TransientDataProvider;
import com.mms.manage_my_stuff.databinding.FragmentRoomListBinding;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.models.Room;
import com.mms.manage_my_stuff.ui.ListItemViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

//TODO: refactor into separate view models
public class RoomListViewModel extends BaseViewModel {

    protected RoomListAdapter roomListAdapter;
    protected RoomViewModel.Factory roomViewModelFactory;

    private final TransientDataProvider transientDataProvider;
    private List<RoomViewModel> roomViewModelList = new ArrayList<>();
    private List<Room> rooms;
    private ItemTouchHelper itemTouchHelper;

    @Inject
    public RoomListViewModel(TransientDataProvider transientDataProvider, RoomViewModel.Factory roomViewModelFactory) {
        super(eventBus);
        this.transientDataProvider = transientDataProvider;
        this.roomViewModelFactory = roomViewModelFactory;
        itemTouchHelper = initItemTouchHelper();

        roomListAdapter = new RoomListAdapter(this);
    }

    public RoomListAdapter getRoomListAdapter() {
        return new RoomListAdapter(this);
    }

    public List<ListItemViewModel> getRoomList() {
        List<ListItemViewModel> listItemViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            listItemViewModelList.add(new ListItemViewModel("Room " + i));
        }

        return listItemViewModelList;
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

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void updateRoomList(List<Room> rooms) {
        this.rooms = rooms;
        roomViewModelList.clear();

        for (Room room : rooms) {
            roomViewModelList.add(roomViewModelFactory.newInstance(room.getTitle(), transientDataProvider));
        }

        roomListAdapter.notifyDataSetChanged();
    }

    public List<ListItemViewModel> getBoxSelectionList() {
        List<ListItemViewModel> boxSelectionItemViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            boxSelectionItemViewModelList.add(new ListItemViewModel("Box type " + i));
        }

        return boxSelectionItemViewModelList;
    }

    public List<ListItemViewModel> getBoxCountList() {
        List<ListItemViewModel> boxCountItemViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            boxCountItemViewModelList.add(new ListItemViewModel((6 - i) + " Box type " + i + " packed"));
        }

        return boxCountItemViewModelList;
    }

    public List<ListItemViewModel> getBoxContentsList() {
        List<ListItemViewModel> kitchenItems = new ArrayList<>();
        kitchenItems.add(new ListItemViewModel("Pots & Pans"));
        kitchenItems.add(new ListItemViewModel("Food"));
        kitchenItems.add(new ListItemViewModel("Dishes"));

        List<ListItemViewModel> livingRoomItems = new ArrayList<>();
        livingRoomItems.add(new ListItemViewModel("Books"));
        livingRoomItems.add(new ListItemViewModel("Lamp"));
        livingRoomItems.add(new ListItemViewModel("Decorative Item"));

        List<ListItemViewModel> boxContentsItemViewModelList = new ArrayList<>();

        String roomType = "kitchen";

        switch (roomType) {
            case "kitchen": boxContentsItemViewModelList.addAll(kitchenItems);
            break;

            case "livingroom": boxContentsItemViewModelList.addAll(livingRoomItems);
            break;
        }

        return boxContentsItemViewModelList;
    }

//    public static class Factory {
//
//        private final UnboundViewEventBus eventBus;
//        private final TransientDataProvider transientDataProvider;
//
//        @Inject
//        public Factory(UnboundViewEventBus eventBus, TransientDataProvider transientDataProvider) {
//            this.eventBus = eventBus;
//            this.transientDataProvider = transientDataProvider;
//        }
//
//        public RoomListViewModel newInstance(Room[] rooms) {
//            return new RoomListViewModel(rooms, eventBus, transientDataProvider);
//        }
//
//    }

    public static class RoomListFragment extends BaseFragment {

        @Inject
        UnboundViewEventBus eventBus;

        @Inject
        protected RoomListViewModel viewModel;

        private FragmentRoomListBinding binding;
        private RecyclerView recyclerView;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_list, container, false);
            binding.setViewModel(viewModel);

            recyclerView = binding.recyclerView;
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            return binding.getRoot();
        }

        @Nullable
        @Override
        protected CompositeSubscription registerUnboundViewEvents() {
            CompositeSubscription events = new CompositeSubscription();

            events.add(eventBus.startActivity(RoomListViewModel.class).subscribe(this::startActivity));

            return events;
        }
    }
}
