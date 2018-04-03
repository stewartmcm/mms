package com.mms.manage_my_stuff.ui.roomlist;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.FragmentRoomListBinding;
import com.mms.manage_my_stuff.models.Room;
import com.mms.manage_my_stuff.ui.room.RoomClickCallback;
import com.mms.manage_my_stuff.ui.ViewLifecycleFragment;

import java.util.List;

public class RoomListFragment extends ViewLifecycleFragment {

    public static final String TAG = "RoomListFragment";
    private RoomListAdapter adapter;
    private FragmentRoomListBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_list, container, false);

        adapter = new RoomListAdapter(roomClickCallback);
        binding.roomList.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RoomListViewModel viewModel =
                ViewModelProviders.of(this).get(RoomListViewModel.class);

        LiveData<DataSnapshot> liveData = viewModel.getDataSnapShotLiveData();

        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    binding.setIsLoading(false);
                    List<Room> rooms = viewModel.convertSnapshotToRooms(dataSnapshot);
                    adapter.setRoomList(rooms);
                    adapter.notifyDataSetChanged();
                } else {
                    binding.setIsLoading(true);
                }
                binding.executePendingBindings();
            }
        });
    }

    private final RoomClickCallback roomClickCallback = new RoomClickCallback() {
        @Override
        public void onClick(Room room) {

            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((RoomListActivity) getActivity()).showRoomDetails(room);
            }
        }
    };
}

