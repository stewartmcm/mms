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
import com.mms.manage_my_stuff.BaseLifeCycleViewModel;
import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.RoomListUseCase;
import com.mms.manage_my_stuff.TransientDataProvider;
import com.mms.manage_my_stuff.databinding.FragmentRoomListBinding;
import com.mms.manage_my_stuff.events.StartActivityEvent;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.models.Room;
import com.mms.manage_my_stuff.ui.RoomActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

//TODO: refactor into separate view models
public class RoomListViewModel extends BaseLifeCycleViewModel {

    protected RoomListAdapter roomListAdapter;
    protected RoomItemViewModel.Factory roomItemViewModelFactory;
    protected UnboundViewEventBus eventBus;

    private RecyclerView.LayoutManager layoutManager;
    private TransientDataProvider transientDataProvider;
    private List<RoomItemViewModel> roomViewModelList = new ArrayList<>();
    private List<Room> rooms;
    private ItemTouchHelper itemTouchHelper;

    @Inject
    public RoomListViewModel(UnboundViewEventBus eventBus, TransientDataProvider transientDataProvider, RoomItemViewModel.Factory roomItemViewModelFactory) {
        this.eventBus = eventBus;
        this.transientDataProvider = transientDataProvider;
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

    public List<RoomItemViewModel> getRoomViewModelList() {
        List<RoomItemViewModel> roomViewModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            roomViewModelList.add(new RoomItemViewModel("Room " + i, transientDataProvider, eventBus));
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

    public void onItemSelected(String text) {
        transientDataProvider.save(new RoomListUseCase(text));
        StartActivityEvent event = StartActivityEvent.build(this).activityName(RoomActivity.class);
        eventBus.send(event);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void updateRoomList(List<Room> rooms) {
        this.rooms = rooms;
        roomViewModelList.clear();

        for (Room room : rooms) {
            roomViewModelList.add(roomItemViewModelFactory.newInstance(room.getTitle(), transientDataProvider));
        }

        roomListAdapter.notifyDataSetChanged();
    }



//    public static class Factory {
//        private final UnboundViewEventBus eventBus;
//        private final TransientDataProvider transientDataProvider;
//
//        @Inject
//        public Factory(UnboundViewEventBus eventBus) {
//            this.eventBus = eventBus;
//        }
//
//        public RoomListViewModel newInstance(Room[] rooms) {
//            return new RoomListViewModel(roomItemViewModelFactory);
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
