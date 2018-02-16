package com.mms.manage_my_stuff.ui;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mms.manage_my_stuff.databinding.ItemRoomBinding;
import com.mms.manage_my_stuff.ui.roomlist.RoomListViewModel;
import com.mms.manage_my_stuff.ui.roomlist.RoomViewModel;

public class RoomListViewHolder extends RecyclerView.ViewHolder {

    private ItemRoomBinding binding;

    public TextView roomTextView;

    public RoomListViewHolder(ItemRoomBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
        this.binding.executePendingBindings();
    }

    public void bind(RoomViewModel itemViewModel, RoomListViewModel viewModel) {
        binding.setItemViewModel(itemViewModel);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
    }
}
