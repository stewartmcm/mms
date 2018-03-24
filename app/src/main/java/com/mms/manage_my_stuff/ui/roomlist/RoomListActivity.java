package com.mms.manage_my_stuff.ui.roomlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.models.Room;
import com.mms.manage_my_stuff.ui.boxcount.BoxCountListFragment;
import com.mms.manage_my_stuff.ui.boxselection.BoxTypeListFragment;

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
    public void show(Room room) {

//        getSupportFragmentManager().popBackStack();

        BoxTypeListFragment boxTypeListFragment = BoxTypeListFragment.forRoom(room.getId());
        BoxCountListFragment boxCountListFragment = BoxCountListFragment.forRoom(room.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("left_frame")
                .replace(R.id.fragment_container_left, boxTypeListFragment, null)
                .addToBackStack("right_frame")
                .replace(R.id.fragment_container_right, boxCountListFragment, null).commit();

//        getSupportFragmentManager()
//                .beginTransaction()

    }
}
