package com.mms.manage_my_stuff.ui.box_contents;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ItemBoxContentsBinding;
import com.mms.manage_my_stuff.ui.ListViewModel;

import java.util.List;

public class BoxContentsListAdapter extends RecyclerView.Adapter<BoxContentsListViewHolder> {

    private ListViewModel viewModel;
    private List<BoxContentsListItemViewModel> itemViewModelList;

    public BoxContentsListAdapter(ListViewModel viewModel) {
        this.viewModel = viewModel;
        itemViewModelList = this.viewModel.getBoxContentsList();
    }

    @Override
    public BoxContentsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemBoxContentsBinding itemBinding = ItemBoxContentsBinding.inflate(layoutInflater, parent, false);
        return new BoxContentsListViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(BoxContentsListViewHolder listViewHolder, int position) {
        BoxContentsListItemViewModel itemViewModel = itemViewModelList.get(position);
        listViewHolder.bind(itemViewModel, viewModel);
    }

    @Override
    public int getItemCount() {
        return itemViewModelList.size();
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
