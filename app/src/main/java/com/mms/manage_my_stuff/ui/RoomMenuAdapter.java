package com.mms.manage_my_stuff.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ItemRoomMenuBinding;

import java.util.List;

public class RoomMenuAdapter extends RecyclerView.Adapter<RoomMenuViewHolder> {

    private RoomMenuViewModel viewModel;
    private List<RoomMenuItemViewModel> itemViewModelList;

    public RoomMenuAdapter(RoomMenuViewModel viewModel) {
        this.viewModel = viewModel;
        itemViewModelList = this.viewModel.getRoomMenuList();
    }

    @Override
    public RoomMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRoomMenuBinding itemBinding = ItemRoomMenuBinding.inflate(layoutInflater, parent, false);
        return new RoomMenuViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(RoomMenuViewHolder roomMenuAdapterViewHolder, int position) {
        RoomMenuItemViewModel itemViewModel = itemViewModelList.get(position);
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
//        void onClick(Long room, RoomMenuViewHolder vh);
//    }
//

//
//    public void swapCursor(Cursor newCursor) {
//        cursor = newCursor;
//        notifyDataSetChanged();
//        emptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
//    }
}