package com.mms.manage_my_stuff.ui;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mms.manage_my_stuff.databinding.ItemBoxTypeBinding;


public class BoxTypeListViewHolder extends RecyclerView.ViewHolder {

    private ItemBoxTypeBinding binding;

    public TextView itemText;

    public BoxTypeListViewHolder(ItemBoxTypeBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
        this.binding.executePendingBindings();
    }

    public void bind(ListItemViewModel itemViewModel, BoxTypeListViewModel viewModel) {
        binding.setListItemViewModel(itemViewModel);
        binding.setListViewModel(viewModel);
        binding.executePendingBindings();
    }
}
