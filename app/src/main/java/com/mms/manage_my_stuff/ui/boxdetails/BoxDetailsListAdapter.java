package com.mms.manage_my_stuff.ui.boxdetails;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ItemBoxDetailsBinding;
import com.mms.manage_my_stuff.models.PackedItem;

import java.util.List;
import java.util.Objects;

public class BoxDetailsListAdapter extends RecyclerView.Adapter<BoxDetailsListViewHolder> {

    private List<PackedItem> mPackedItemList;

    @Nullable
    private final BoxDetailsClickCallback boxDetailsClickCallback;

    public BoxDetailsListAdapter(BoxDetailsClickCallback boxDetailsClickCallback) {
        this.boxDetailsClickCallback = boxDetailsClickCallback;

//        this.viewModel = viewModel;
//        itemViewModelList = this.viewModel.getBoxDetailsList();
    }

    public void setPackedItemList(final List<PackedItem> packedItemList) {
        if (mPackedItemList == null) {
            mPackedItemList = packedItemList;
            notifyItemRangeInserted(0, packedItemList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mPackedItemList.size();
                }

                @Override
                public int getNewListSize() {
                    return packedItemList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mPackedItemList.get(oldItemPosition).getId() ==
                            packedItemList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    PackedItem newPackedItem = packedItemList.get(newItemPosition);
                    PackedItem oldPackedItem = mPackedItemList.get(oldItemPosition);
                    return newPackedItem.getId() == oldPackedItem.getId()
//                            && Objects.equals(newPackedItem.getSize(), oldPackedItem.getSize())
                            && Objects.equals(newPackedItem.getTitle(), oldPackedItem.getTitle());
                }
            });
            mPackedItemList = packedItemList;
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
        holder.binding.setBoxDetails(mPackedItemList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mPackedItemList == null ? 0 : mPackedItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_box_details;
    }

//        @Override
//        public void onClick(View view) {
//            int adapterPosition = getAdapterPosition();
//            cursor.moveToPosition(adapterPosition);
//            int roomColumnIndex = cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DATE);
//            clickHandler.onClick(cursor.getLong(roomColumnIndex), this);
//            System.out.println("***** Open Room Details Activity or Fragment *****");
//        }
//    }

//    public static interface RoomMenuAdapterOnClickHandler {
//        void onClick(Long room, BoxContentsListViewHolder vh);
//    }
//

//
//    public void swapCursor(Cursor newCursor) {
//        cursor = newCursor;
//        notifyDataSetChanged();
//        emptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
//    }
}
