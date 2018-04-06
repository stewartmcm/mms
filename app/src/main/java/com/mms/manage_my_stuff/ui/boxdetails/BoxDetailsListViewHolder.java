package com.mms.manage_my_stuff.ui.boxdetails;

import android.support.v7.widget.RecyclerView;

import com.mms.manage_my_stuff.databinding.ItemBoxDetailsBinding;

public class BoxDetailsListViewHolder extends RecyclerView.ViewHolder {

    final ItemBoxDetailsBinding binding;

    public BoxDetailsListViewHolder(ItemBoxDetailsBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

}
