package com.mms.manage_my_stuff.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.FragmentEmptyBinding;
import com.mms.manage_my_stuff.ui.boxtype.BoxTypeListAdapter;

public class EmptyFragment extends ViewLifecycleFragment {

    private static final String KEY_BOX_ID = "room_id";

//    @Inject
//    protected BoxTypeListViewModel viewModel;

    private FragmentEmptyBinding binding;

    private BoxTypeListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_empty, container, false);
//        binding.setViewModel(viewModel);
//        recyclerView = binding.recyclerView;
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        adapter = new BoxTypeListAdapter(boxTypeClickCallback);
//        binding.boxTypeList.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        final BoxTypeListViewModel viewModel =
//                ViewModelProviders.of(getActivity()).get(BoxTypeListViewModel.class);
//
//        List<Box> boxTypes = viewModel.getBoxTypeList();
//        adapter.setBoxTypeList(boxTypes);
//        adapter.notifyDataSetChanged();
        binding.executePendingBindings();
    }

//    private final BoxTypeClickCallback boxTypeClickCallback = new BoxTypeClickCallback() {
//        @Override
//        public void onClick(Box box) {
//            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
//                ((RoomListActivity) getActivity()).showBoxDetails(box);
//            }
//        }
//    };

    /** Creates boxTypeList fragment for specific room ID */
    public static EmptyFragment forBox(int boxId) {
        EmptyFragment fragment = new EmptyFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_BOX_ID, boxId);
        fragment.setArguments(args);
        return fragment;
    }
}
