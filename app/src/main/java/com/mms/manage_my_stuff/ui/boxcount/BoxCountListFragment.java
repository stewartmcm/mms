package com.mms.manage_my_stuff.ui.boxcount;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.FragmentBoxCountListBinding;
import com.mms.manage_my_stuff.models.Box;

public class BoxCountListFragment extends Fragment {

    private static final String KEY_ROOM_ID = "room_id";

    protected BoxCountListViewModel viewModel;

    private FragmentBoxCountListBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_box_count_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        BoxCountListViewModel.Factory factory = new BoxCountListViewModel.Factory(
                getActivity().getApplication(), getArguments().getInt(KEY_ROOM_ID));

        final BoxCountListViewModel viewModel =
                ViewModelProviders.of(this, factory).get(BoxCountListViewModel.class);

        LiveData<DataSnapshot> liveData = viewModel.getDataSnapShotLiveData();

        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    binding.setIsLoading(false);
                    viewModel.convertSnapshotToRoom(dataSnapshot);
                } else {
                    binding.setIsLoading(true);
                }
                binding.setViewModel(viewModel);
                binding.executePendingBindings();
            }
        });
    }

    private final BoxCountClickCallback boxCountClickCallback = new BoxCountClickCallback() {
        @Override
        public void onClick(Box box) {
            // no-op

        }
    };

    public static BoxCountListFragment forRoom(int roomId) {
        BoxCountListFragment fragment = new BoxCountListFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_ROOM_ID, roomId);
        fragment.setArguments(args);
        return fragment;
    }
}
