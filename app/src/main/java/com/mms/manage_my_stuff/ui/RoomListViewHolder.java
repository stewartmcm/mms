package com.mms.manage_my_stuff.ui;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mms.manage_my_stuff.databinding.ItemRoomBinding;
import com.mms.manage_my_stuff.ui.roomlist.RoomListViewModel;
import com.mms.manage_my_stuff.ui.roomlist.RoomItemViewModel;

public class RoomListViewHolder extends RecyclerView.ViewHolder {

    private ItemRoomBinding binding;

    public TextView roomTextView;

    public RoomListViewHolder(ItemRoomBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
        this.binding.executePendingBindings();
    }

    public void bind(RoomItemViewModel itemViewModel, RoomListViewModel listViewModel) {
        binding.setItemViewModel(itemViewModel);
        binding.setListViewModel(listViewModel);
        binding.executePendingBindings();
    }
}
