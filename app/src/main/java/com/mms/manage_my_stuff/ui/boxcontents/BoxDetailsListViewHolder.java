package com.mms.manage_my_stuff.ui.boxcontents;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mms.manage_my_stuff.databinding.ItemBoxContentsBinding;
import com.mms.manage_my_stuff.ui.RoomListViewModel;

public class BoxDetailsListViewHolder extends RecyclerView.ViewHolder {

    private ItemBoxContentsBinding binding;

    public TextView roomTextView;

    public BoxDetailsListViewHolder(ItemBoxContentsBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
        this.binding.executePendingBindings();
    }

    public void bind(BoxDetailsListItemViewModel itemViewModel, RoomListViewModel viewModel) {
        binding.setListItemViewModel(itemViewModel);
        binding.setListViewModel(viewModel);
        binding.executePendingBindings();
    }
}
