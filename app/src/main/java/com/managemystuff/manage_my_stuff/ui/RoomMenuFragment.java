package com.managemystuff.manage_my_stuff.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.managemystuff.manage_my_stuff.R;
import com.managemystuff.manage_my_stuff.databinding.FragmentRoomMenuBinding;

import dagger.android.support.AndroidSupportInjection;
import rx.subscriptions.CompositeSubscription;

public class RoomMenuFragment extends BaseFragment {

    protected RoomMenuViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        FragmentRoomMenuBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_menu, container, false);
        viewModel.setLifecycle(getLifecycle());
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Nullable
    @Override
    protected CompositeSubscription registerUnboundViewEvents() {
        return null;
    }
}
