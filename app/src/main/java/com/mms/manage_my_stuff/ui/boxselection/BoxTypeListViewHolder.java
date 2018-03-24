package com.mms.manage_my_stuff.ui.boxselection;

import android.support.v7.widget.RecyclerView;

import com.mms.manage_my_stuff.databinding.ItemBoxTypeBinding;


public class BoxTypeListViewHolder extends RecyclerView.ViewHolder {

    final ItemBoxTypeBinding binding;

//    public TextView itemText;

    public BoxTypeListViewHolder(ItemBoxTypeBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
//        this.binding.executePendingBindings();
    }

//    public void bind(BoxTypeItemViewModel itemViewModel, BoxTypeListViewModel viewModel) {
//        binding.setItemViewModel(itemViewModel);
//        binding.setListViewModel(viewModel);
//        binding.executePendingBindings();
//    }
}
