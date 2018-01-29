package com.mms.manage_my_stuff.ui.box_contents;

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
import com.mms.manage_my_stuff.databinding.FragmentBoxContentsListBinding;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.ui.ListViewModel;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class BoxContentsListFragment extends BaseFragment {

    @Inject
    UnboundViewEventBus eventBus;

    @Inject
    protected ListViewModel viewModel;

    private FragmentBoxContentsListBinding binding;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_box_contents_list, container, false);
        binding.setViewModel(viewModel);

        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return binding.getRoot();
    }

    @Nullable
    @Override
    protected CompositeSubscription registerUnboundViewEvents() {
        CompositeSubscription events = new CompositeSubscription();

        events.add(eventBus.startActivity(ListViewModel.class).subscribe(this::startActivity));

        return events;
    }
}