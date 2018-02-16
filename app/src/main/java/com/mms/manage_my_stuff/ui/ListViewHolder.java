package com.mms.manage_my_stuff.ui;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mms.manage_my_stuff.databinding.ItemRoomBinding;
import com.mms.manage_my_stuff.ui.roomlist.RoomListViewModel;

public class ListViewHolder extends RecyclerView.ViewHolder {

    private ItemRoomBinding binding;

    public TextView roomTextView;

    public ListViewHolder(ItemRoomBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
        this.binding.executePendingBindings();
    }

    public void bind(ListItemViewModel itemViewModel, RoomListViewModel viewModel) {
        binding.setListItemViewModel(itemViewModel);
        binding.setListViewModel(viewModel);
        binding.executePendingBindings();
    }
}
