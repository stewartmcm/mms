package com.mms.manage_my_stuff.ui.boxdetails;

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
import com.mms.manage_my_stuff.databinding.FragmentBoxDetailsListBinding;
import com.mms.manage_my_stuff.models.Item;

import java.util.List;

public class BoxDetailsListFragment extends Fragment {

    private static final String KEY_BOX_ID = "box_id";
    private static final String KEY_ROOM_TYPE = "room_type";

    protected BoxDetailsListViewModel viewModel;

    private FragmentBoxDetailsListBinding binding;

    private BoxDetailsListAdapter adapter;

//    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_box_details_list, container, false);
        adapter = new BoxDetailsListAdapter(boxDetailsClickCallback);
        binding.packedItemList.setAdapter(adapter);

        getActivity().setTitle("Box Contents");

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        BoxDetailsListViewModel.Factory factory = new BoxDetailsListViewModel.Factory(
                getActivity().getApplication(), getArguments().getInt(KEY_BOX_ID), getArguments().getString(KEY_ROOM_TYPE));

        final BoxDetailsListViewModel viewModel =
                ViewModelProviders.of(this, factory).get(BoxDetailsListViewModel.class);

        List<Item> items = viewModel.getItemsList();
        adapter.setItemList(items);
        adapter.notifyDataSetChanged();

        LiveData<DataSnapshot> liveData = viewModel.getDataSnapShotLiveData();

        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    binding.setIsLoading(false);
                } else {
                    binding.setIsLoading(true);
                }
                binding.setViewModel(viewModel);
                binding.executePendingBindings();
            }
        });
    }

    private final BoxDetailsClickCallback boxDetailsClickCallback = new BoxDetailsClickCallback() {
        @Override
        public void onClick(Item item) {
            // no-op

        }
    };

    public static BoxDetailsListFragment forBox(int boxId, String roomType) {
        BoxDetailsListFragment fragment = new BoxDetailsListFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_BOX_ID, boxId);
        args.putString(KEY_ROOM_TYPE, roomType);
        fragment.setArguments(args);
        return fragment;
    }
}
