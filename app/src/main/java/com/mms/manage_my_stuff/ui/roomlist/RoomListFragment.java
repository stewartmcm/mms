package com.mms.manage_my_stuff.ui.roomlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mms.manage_my_stuff.BaseFragment;
import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.FragmentRoomListBinding;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class RoomListFragment extends BaseFragment {

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

