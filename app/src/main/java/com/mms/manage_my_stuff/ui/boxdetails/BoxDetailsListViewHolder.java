package com.mms.manage_my_stuff.ui.boxdetails;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mms.manage_my_stuff.databinding.ItemBoxDetailsBinding;
import com.mms.manage_my_stuff.ui.ListItemViewModel;
import com.mms.manage_my_stuff.ui.roomlist.RoomListViewModel;

public class BoxDetailsListViewHolder extends RecyclerView.ViewHolder {

    private ItemBoxDetailsBinding binding;

    public TextView roomTextView;

    public BoxDetailsListViewHolder(ItemBoxDetailsBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
        this.binding.executePendingBindings();
    }

    public void bind(ListItemViewModel itemViewModel, BoxDetailsListViewModel viewModel) {
        binding.setListItemViewModel(itemViewModel);
        binding.setListViewModel(viewModel);
        binding.executePendingBindings();
    }
}
