package com.mms.manage_my_stuff.ui;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mms.manage_my_stuff.databinding.ItemBoxCountBinding;

public class BoxCountListViewHolder extends RecyclerView.ViewHolder {

    private ItemBoxCountBinding binding;

    public TextView roomTextView;

    public BoxCountListViewHolder(ItemBoxCountBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
        this.binding.executePendingBindings();
    }

    public void bind(ListItemViewModel itemViewModel, BoxCountListViewModel viewModel) {
        binding.setListItemViewModel(itemViewModel);
        binding.setListViewModel(viewModel);
        binding.executePendingBindings();
    }
}
