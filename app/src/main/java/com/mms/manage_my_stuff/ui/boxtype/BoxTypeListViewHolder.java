package com.mms.manage_my_stuff.ui.boxtype;

import android.support.v7.widget.RecyclerView;

import com.mms.manage_my_stuff.databinding.ItemBoxTypeBinding;


public class BoxTypeListViewHolder extends RecyclerView.ViewHolder {

    final ItemBoxTypeBinding binding;

    public BoxTypeListViewHolder(ItemBoxTypeBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }
}
