package com.mms.manage_my_stuff.ui.roomlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ItemRoomBinding;
import com.mms.manage_my_stuff.ui.RoomListViewHolder;

import java.util.List;

public class RoomListAdapter extends RecyclerView.Adapter<RoomListViewHolder> {

    private RoomListViewModel viewModel;
    private List<RoomViewModel> roomViewModelList;

    public RoomListAdapter(RoomListViewModel viewModel) {
        this.viewModel = viewModel;
        roomViewModelList = this.viewModel.getRoomViewModelList();
    }

    @Override
    public RoomListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRoomBinding itemBinding = ItemRoomBinding.inflate(layoutInflater, parent, false);
        return new RoomListViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(RoomListViewHolder roomMenuAdapterViewHolder, int position) {
        RoomViewModel itemViewModel = roomViewModelList.get(position);
        roomMenuAdapterViewHolder.bind(itemViewModel, viewModel);
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
//        void onClick(Long room, BoxContentsRoomListViewHolder vh);
//    }
//

//
//    public void swapCursor(Cursor newCursor) {
//        cursor = newCursor;
//        notifyDataSetChanged();
//        emptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
//    }
}
