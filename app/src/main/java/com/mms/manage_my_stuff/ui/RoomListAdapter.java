package com.mms.manage_my_stuff.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ItemRoomMenuBinding;

import java.util.List;

public class RoomListAdapter extends RecyclerView.Adapter<ListViewHolder> {

    private ListViewModel viewModel;
    private List<ListItemViewModel> itemViewModelList;

    public RoomListAdapter(ListViewModel viewModel) {
        this.viewModel = viewModel;
        itemViewModelList = this.viewModel.getRoomList();
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRoomMenuBinding itemBinding = ItemRoomMenuBinding.inflate(layoutInflater, parent, false);
        return new ListViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(ListViewHolder roomMenuAdapterViewHolder, int position) {
        ListItemViewModel itemViewModel = itemViewModelList.get(position);
        roomMenuAdapterViewHolder.bind(itemViewModel, viewModel);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_room_menu;
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
