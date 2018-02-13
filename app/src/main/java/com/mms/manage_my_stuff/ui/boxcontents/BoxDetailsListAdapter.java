package com.mms.manage_my_stuff.ui.boxcontents;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ItemBoxContentsBinding;
import com.mms.manage_my_stuff.ui.roomlist.RoomListViewModel;

import java.util.List;

public class BoxDetailsListAdapter extends RecyclerView.Adapter<BoxDetailsListViewHolder> {

    private RoomListViewModel viewModel;
    private List<BoxDetailsListItemViewModel> itemViewModelList;

    public BoxDetailsListAdapter(RoomListViewModel viewModel) {
        this.viewModel = viewModel;
        itemViewModelList = this.viewModel.getBoxContentsList();
    }

    @Override
    public BoxDetailsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemBoxContentsBinding itemBinding = ItemBoxContentsBinding.inflate(layoutInflater, parent, false);
        return new BoxDetailsListViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(BoxDetailsListViewHolder listViewHolder, int position) {
        BoxDetailsListItemViewModel itemViewModel = itemViewModelList.get(position);
        listViewHolder.bind(itemViewModel, viewModel);
    }

    @Override
    public int getItemCount() {
        return itemViewModelList.size();
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
