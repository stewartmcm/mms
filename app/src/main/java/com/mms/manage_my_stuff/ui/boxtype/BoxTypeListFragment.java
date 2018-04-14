package com.mms.manage_my_stuff.ui.boxtype;

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
import com.mms.manage_my_stuff.databinding.FragmentBoxTypeListBinding;
import com.mms.manage_my_stuff.models.Box;
import com.mms.manage_my_stuff.ui.MainActivity;
import com.mms.manage_my_stuff.ui.ViewLifecycleFragment;

import java.util.List;

import javax.inject.Inject;

public class BoxTypeListFragment extends ViewLifecycleFragment {

    private static final String KEY_ROOM_ID = "room_id";

    @Inject
    protected BoxTypeListViewModel viewModel;

    private FragmentBoxTypeListBinding binding;

    private BoxTypeListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_box_type_list, container, false);
        adapter = new BoxTypeListAdapter(boxTypeClickCallback);
        binding.boxTypeList.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        BoxCountListViewModel.Factory factory = new BoxCountListViewModel.Factory(
//                getActivity().getApplication(), getArguments().getInt(KEY_ROOM_ID));

        BoxTypeListViewModel.Factory factory = new BoxTypeListViewModel.Factory(getActivity().getApplication(), getArguments().getInt(KEY_ROOM_ID));

        final BoxTypeListViewModel viewModel =
                ViewModelProviders.of(this, factory).get(BoxTypeListViewModel.class);

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

        List<Box> boxTypes = viewModel.getBoxTypeList();
        adapter.setBoxTypeList(boxTypes);
        adapter.notifyDataSetChanged();
        binding.executePendingBindings();
    }

    private final BoxTypeClickCallback boxTypeClickCallback = new BoxTypeClickCallback() {
        @Override
        public void onClick(Box box) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).showBoxDetails(box);
            }
        }
    };

    /** Creates boxTypeList fragment for specific room ID */
    public static BoxTypeListFragment forRoom(int roomId) {
        BoxTypeListFragment fragment = new BoxTypeListFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_ROOM_ID, roomId);
        fragment.setArguments(args);
        return fragment;
    }
}
