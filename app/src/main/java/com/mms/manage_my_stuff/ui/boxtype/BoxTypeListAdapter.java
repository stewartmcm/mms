package com.mms.manage_my_stuff.ui.boxtype;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ItemBoxTypeBinding;
import com.mms.manage_my_stuff.models.Box;

import java.util.List;
import java.util.Objects;

public class BoxTypeListAdapter extends RecyclerView.Adapter<BoxTypeListViewHolder> {

    private List<Box> mBoxTypeList;

    @Nullable
    private final BoxTypeClickCallback boxTypeClickCallback;

    public BoxTypeListAdapter(@Nullable BoxTypeClickCallback boxTypeClickCallback) {
        this.boxTypeClickCallback = boxTypeClickCallback;
    }

    public void setBoxTypeList(final List<Box> boxTypeList) {
        if (mBoxTypeList == null) {
            mBoxTypeList = boxTypeList;
            notifyItemRangeInserted(0, boxTypeList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mBoxTypeList.size();
                }

                @Override
                public int getNewListSize() {
                    return boxTypeList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mBoxTypeList.get(oldItemPosition).getId() ==
                            boxTypeList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Box newBoxType = boxTypeList.get(newItemPosition);
                    Box oldBoxType = mBoxTypeList.get(oldItemPosition);
                    return newBoxType.getId() == oldBoxType.getId()
                            && Objects.equals(newBoxType.getSize(), oldBoxType.getSize())
                            && Objects.equals(newBoxType.getTitle(), oldBoxType.getTitle());
                }
            });
            mBoxTypeList = boxTypeList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public BoxTypeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBoxTypeBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_box_type,
                        parent, false);
        binding.setCallback(boxTypeClickCallback);
        return new BoxTypeListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BoxTypeListViewHolder holder, int position) {
        holder.binding.setBoxType(mBoxTypeList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mBoxTypeList == null ? 0 : mBoxTypeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_box_type;
    }

//        @Override
//        public void onClick(View view) {
//            int adapterPosition = getAdapterPosition();
//            cursor.moveToPosition(adapterPosition);
//            int boxTypeColumnIndex = cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DATE);
//            clickHandler.onClick(cursor.getLong(boxTypeColumnIndex), this);
//            System.out.println("***** Open BoxType Details Activity or Fragment *****");
//        }
//    }

//    public static interface BoxTypeMenuAdapterOnClickHandler {
//        void onClick(Long boxType, BoxContentsListViewHolder vh);
//    }
//

//
//    public void swapCursor(Cursor newCursor) {
//        cursor = newCursor;
//        notifyDataSetChanged();
//        emptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
//    }
}
