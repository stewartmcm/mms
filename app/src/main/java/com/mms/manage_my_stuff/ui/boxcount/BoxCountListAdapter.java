package com.mms.manage_my_stuff.ui.boxcount;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ItemBoxCountBinding;
import com.mms.manage_my_stuff.models.Box;

import java.util.List;
import java.util.Objects;

public class BoxCountListAdapter extends RecyclerView.Adapter<BoxCountListViewHolder> {

    private BoxCountListViewModel viewModel;
    private List<BoxCountItemViewModel> itemViewModelList;
    private List<Box> mBoxCountList;

    @Nullable
    private final BoxCountClickCallback boxCountClickCallback;

    public BoxCountListAdapter(@Nullable BoxCountClickCallback boxCountClickCallback) {
        this.boxCountClickCallback = boxCountClickCallback;
    }

    public void setBoxCountList(final List<Box> boxCountList) {
        if (mBoxCountList == null) {
            mBoxCountList = boxCountList;
            notifyItemRangeInserted(0, boxCountList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mBoxCountList.size();
                }

                @Override
                public int getNewListSize() {
                    return boxCountList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mBoxCountList.get(oldItemPosition).getId() ==
                            boxCountList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Box newBoxCount = boxCountList.get(newItemPosition);
                    Box oldBoxCount = mBoxCountList.get(oldItemPosition);
                    return newBoxCount.getId() == oldBoxCount.getId()
                            && Objects.equals(newBoxCount.getSize(), oldBoxCount.getSize())
                            && Objects.equals(newBoxCount.getTitle(), oldBoxCount.getTitle());
                }
            });
            mBoxCountList = boxCountList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public BoxCountListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemBoxCountBinding itemBinding = ItemBoxCountBinding.inflate(layoutInflater, parent, false);
        return new BoxCountListViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(BoxCountListViewHolder holder, int position) {
        holder.binding.setBoxCount(mBoxCountList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_room;
    }

}
