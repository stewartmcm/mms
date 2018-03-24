package com.mms.manage_my_stuff.ui.boxcount;

import android.support.v7.widget.RecyclerView;

import com.mms.manage_my_stuff.databinding.ItemBoxCountBinding;

public class BoxCountListViewHolder extends RecyclerView.ViewHolder {

    final ItemBoxCountBinding binding;

//    public TextView title;

    public BoxCountListViewHolder(ItemBoxCountBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
//        this.binding.executePendingBindings();
    }

//    public void bind(BoxCountItemViewModel itemViewModel, BoxCountListViewModel viewModel) {
//        binding.setListItemViewModel(itemViewModel);
//        binding.setListViewModel(viewModel);
//        binding.executePendingBindings();
//    }
}
