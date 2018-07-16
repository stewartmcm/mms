package com.mms.manage_my_stuff.ui.roomlist;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ItemRoomBinding;
import com.mms.manage_my_stuff.models.Room;

import java.util.List;
import java.util.Objects;

public class RoomListAdapter extends RecyclerView.Adapter<RoomListViewHolder> {

    private List<? extends Room> roomList;

    @Nullable
    private final RoomClickCallback roomClickCallback;

    public RoomListAdapter(@Nullable RoomClickCallback roomClickCallback) {
        this.roomClickCallback = roomClickCallback;
    }

    public void setRoomList(final List<? extends Room> roomList) {
        if (this.roomList == null) {
            this.roomList = roomList;
            notifyItemRangeInserted(0, roomList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return RoomListAdapter.this.roomList.size();
                }

                @Override
                public int getNewListSize() {
                    return roomList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return RoomListAdapter.this.roomList.get(oldItemPosition).getId() ==
                            roomList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Room newRoom = roomList.get(newItemPosition);
                    Room oldRoom = RoomListAdapter.this.roomList.get(oldItemPosition);
                    return newRoom.getId() == oldRoom.getId()
                            && Objects.equals(newRoom.getBoxCount(), oldRoom.getBoxCount())
                            && Objects.equals(newRoom.getRoomType(), oldRoom.getRoomType());
                }
            });
            this.roomList = roomList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public RoomListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRoomBinding itemBinding = ItemRoomBinding.inflate(layoutInflater, parent, false);
        itemBinding.setCallback(roomClickCallback);
        return new RoomListViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(RoomListViewHolder holder, int position) {
        holder.binding.setRoom(roomList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return roomList == null ? 0 : roomList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_room;
    }

}
