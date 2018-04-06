package com.mms.manage_my_stuff.ui.boxcount;

import android.support.v7.widget.RecyclerView;

import com.mms.manage_my_stuff.databinding.ItemBoxCountBinding;

public class BoxCountListViewHolder extends RecyclerView.ViewHolder {

    final ItemBoxCountBinding binding;

    public BoxCountListViewHolder(ItemBoxCountBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

}
