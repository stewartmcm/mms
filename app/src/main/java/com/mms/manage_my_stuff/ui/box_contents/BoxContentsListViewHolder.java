package com.mms.manage_my_stuff.ui.box_contents;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mms.manage_my_stuff.databinding.ItemBoxContentsBinding;
import com.mms.manage_my_stuff.ui.ListViewModel;

public class BoxContentsListViewHolder extends RecyclerView.ViewHolder {

    private ItemBoxContentsBinding binding;

    public TextView roomTextView;

    public BoxContentsListViewHolder(ItemBoxContentsBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
        this.binding.executePendingBindings();
    }

    public void bind(BoxContentsListItemViewModel itemViewModel, ListViewModel viewModel) {
        binding.setListItemViewModel(itemViewModel);
        binding.setListViewModel(viewModel);
        binding.executePendingBindings();
    }
}
