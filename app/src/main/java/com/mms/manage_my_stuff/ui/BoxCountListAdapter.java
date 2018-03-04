package com.mms.manage_my_stuff.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ItemBoxCountBinding;

import java.util.List;

public class BoxCountListAdapter extends RecyclerView.Adapter<BoxCountListViewHolder> {

    private BoxCountListViewModel viewModel;
    private List<BoxCountItemViewModel> itemViewModelList;

    public BoxCountListAdapter(BoxCountListViewModel viewModel) {
        this.viewModel = viewModel;
        itemViewModelList = this.viewModel.getBoxCountList();
    }

    @Override
    public BoxCountListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemBoxCountBinding itemBinding = ItemBoxCountBinding.inflate(layoutInflater, parent, false);
        return new BoxCountListViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(BoxCountListViewHolder boxCountListViewHolder, int position) {
        BoxCountItemViewModel itemViewModel = itemViewModelList.get(position);
        boxCountListViewHolder.bind(itemViewModel, viewModel);
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
