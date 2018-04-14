package com.mms.manage_my_stuff.ui.boxdetails;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ItemBoxDetailsBinding;
import com.mms.manage_my_stuff.models.Item;

import java.util.List;
import java.util.Objects;

public class BoxDetailsListAdapter extends RecyclerView.Adapter<BoxDetailsListViewHolder> {

    private List<Item> mItemList;

    @Nullable
    private final BoxDetailsClickCallback boxDetailsClickCallback;

    public BoxDetailsListAdapter(BoxDetailsClickCallback boxDetailsClickCallback) {
        this.boxDetailsClickCallback = boxDetailsClickCallback;
    }

    public void setItemList(final List<Item> itemList) {
        if (mItemList == null) {
            mItemList = itemList;
            notifyItemRangeInserted(0, itemList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mItemList.size();
                }

                @Override
                public int getNewListSize() {
                    return itemList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mItemList.get(oldItemPosition).getId() ==
                            itemList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Item newItem = itemList.get(newItemPosition);
                    Item oldItem = mItemList.get(oldItemPosition);
                    return newItem.getId() == oldItem.getId()
//                            && Objects.equals(newPackedItem.getSize(), oldPackedItem.getSize())
                            && Objects.equals(newItem.getTitle(), oldItem.getTitle());
                }
            });
            mItemList = itemList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public BoxDetailsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBoxDetailsBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_box_details,
                        parent, false);
        binding.setCallback(boxDetailsClickCallback);
        return new BoxDetailsListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BoxDetailsListViewHolder holder, int position) {
        holder.binding.setItemDetails(mItemList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_box_details;
    }

}
