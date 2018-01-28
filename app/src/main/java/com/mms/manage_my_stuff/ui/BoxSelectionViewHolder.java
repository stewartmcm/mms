package com.mms.manage_my_stuff.ui;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mms.manage_my_stuff.databinding.ItemRoomMenuBinding;

public class BoxSelectionViewHolder extends RecyclerView.ViewHolder {

    private ItemRoomMenuBinding binding;

    public TextView roomTextView;

    public BoxSelectionViewHolder(ItemRoomMenuBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
        this.binding.executePendingBindings();
    }

    public void bind(RoomMenuItemViewModel itemViewModel, RoomMenuViewModel viewModel) {
        binding.setRoomMenuItemViewModel(itemViewModel);
        binding.setRoomMenuViewModel(viewModel);
        binding.executePendingBindings();
    }
}
