package com.mms.manage_my_stuff.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ItemBoxTypeBinding;

import java.util.List;

public class BoxTypeListAdapter extends RecyclerView.Adapter<BoxTypeListViewHolder> {

    private BoxTypeListViewModel viewModel;
    private List<BoxTypeItemViewModel> itemViewModelList;

    public BoxTypeListAdapter(BoxTypeListViewModel viewModel) {
        this.viewModel = viewModel;
        itemViewModelList = this.viewModel.getBoxTypeList();
    }

    @Override
    public BoxTypeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemBoxTypeBinding itemBinding = ItemBoxTypeBinding.inflate(layoutInflater, parent, false);
        return new BoxTypeListViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(BoxTypeListViewHolder boxTypeAdapterViewHolder, int position) {
        BoxTypeItemViewModel itemViewModel = itemViewModelList.get(position);
        boxTypeAdapterViewHolder.bind(itemViewModel, viewModel);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_room;
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
