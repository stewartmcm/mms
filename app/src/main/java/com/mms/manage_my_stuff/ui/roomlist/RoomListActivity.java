package com.mms.manage_my_stuff.ui.roomlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.models.Room;
import com.mms.manage_my_stuff.ui.BoxCountListFragment;

public class RoomListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

//        if (savedInstanceState == null) {
//            RoomListFragment fragment = new RoomListFragment();
//
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment_container, fragment, RoomListFragment.TAG).commit();
//        }
    }

    /** Shows the room detail fragment */
    public void show(Room room) {

        BoxCountListFragment boxCountListFragment = BoxCountListFragment.forProduct(room.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("room")
                .replace(R.id.fragment_container,
                        boxCountListFragment, null).commit();
    }
}
