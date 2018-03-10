package com.mms.manage_my_stuff.ui;

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
import com.mms.manage_my_stuff.databinding.FragmentBoxCountListBinding;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class BoxCountListFragment extends BaseFragment {

    private static final String KEY_ROOM_ID = "room_id";

    protected BoxCountListViewModel viewModel;

    private FragmentBoxCountListBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_box_count_list, container, false);
        binding.setViewModel(viewModel);
//        recyclerView = binding.recyclerView;
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return binding.getRoot();
    }

    @Nullable
    @Override
    protected CompositeSubscription registerUnboundViewEvents() {
        CompositeSubscription events = new CompositeSubscription();

        return events;
    }

    /** Creates product fragment for specific product ID */
    public static BoxCountListFragment forProduct(int roomId) {
        BoxCountListFragment fragment = new BoxCountListFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_ROOM_ID, roomId);
        fragment.setArguments(args);
        return fragment;
    }
}
