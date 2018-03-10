package com.mms.manage_my_stuff.ui.roomlist;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.FragmentRoomListBinding;
import com.mms.manage_my_stuff.models.Room;
import com.mms.manage_my_stuff.ui.RoomClickCallback;

import java.util.List;

public class RoomListFragment extends Fragment {

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
        final RoomListViewModel viewModel =
                ViewModelProviders.of(this).get(RoomListViewModel.class);

        LiveData<DataSnapshot> liveData = viewModel.getDataSnapShotLiveData();
        String userId = viewModel.getUserId();

        if (liveData.getValue() != null) {
            Log.i(TAG, liveData.getValue().child("users").toString());
        }
//        adapter.setRoomList(viewModel.getRooms().getValue());

        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    long count = dataSnapshot.child("users").child(userId).getChildrenCount();
                    binding.setIsLoading(false);
//                    adapter.setRoomList(viewModel.getRooms().getValue());
                }
            }
        });

//        subscribeUi(viewModel);
    }

    private void subscribeUi(RoomListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getRooms().observe(this, new Observer<List<Room>>() {
            @Override
            public void onChanged(@Nullable List<Room> myRooms) {
                if (myRooms != null) {
                    binding.setIsLoading(false);
                    adapter.setRoomList(myRooms);
                } else {
                    binding.setIsLoading(true);
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                binding.executePendingBindings();
            }
        });
    }

    private final RoomClickCallback roomClickCallback = new RoomClickCallback() {
        @Override
        public void onClick(Room room) {

            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((RoomListActivity) getActivity()).show(room);
            }
        }
    };
}

