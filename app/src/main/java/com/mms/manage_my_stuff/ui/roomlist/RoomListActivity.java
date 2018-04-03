package com.mms.manage_my_stuff.ui.roomlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.models.Box;
import com.mms.manage_my_stuff.models.Room;
import com.mms.manage_my_stuff.ui.EmptyFragment;
import com.mms.manage_my_stuff.ui.boxcount.BoxCountListFragment;
import com.mms.manage_my_stuff.ui.boxdetails.BoxDetailsListFragment;
import com.mms.manage_my_stuff.ui.boxtype.BoxTypeListFragment;

public class RoomListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        if (savedInstanceState == null) {
            RoomListFragment fragment = new RoomListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_left, fragment, RoomListFragment.TAG).commit();
//            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    /** Shows the room detail fragment */
    public void showRoomDetails(Room room) {

//        getSupportFragmentManager().popBackStack();

        BoxTypeListFragment boxTypeListFragment = BoxTypeListFragment.forRoom(room.getId());
        BoxCountListFragment boxCountListFragment = BoxCountListFragment.forRoom(room.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("left_frame")
                .replace(R.id.fragment_container_left, boxTypeListFragment, null)
                .addToBackStack("right_frame")
                .replace(R.id.fragment_container_right, boxCountListFragment, null)
                .commit();

//        getSupportFragmentManager()
//                .beginTransaction()

    }

    public void showBoxDetails(Box box) {

//        BoxTypeListFragment boxTypeListFragment = BoxTypeListFragment.forRoom(box.getId());
//        BoxCountListFragment boxCountListFragment = BoxCountListFragment.forRoom(box.getId());
        BoxDetailsListFragment boxDetailsListFragment = BoxDetailsListFragment.forBox(box.getId());
        EmptyFragment emptyFragment = EmptyFragment.forBox(box.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("left_frame")
                .replace(R.id.fragment_container_left, boxDetailsListFragment, null)
                .addToBackStack("right_frame")
                .replace(R.id.fragment_container_right, emptyFragment, null)
                .commit();


    }
}
