package com.mms.manage_my_stuff.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toolbar;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ActivityMainBinding;
import com.mms.manage_my_stuff.models.Box;
import com.mms.manage_my_stuff.models.Room;
import com.mms.manage_my_stuff.ui.boxcount.BoxCountListFragment;
import com.mms.manage_my_stuff.ui.boxdetails.BoxDetailsListFragment;
import com.mms.manage_my_stuff.ui.boxtype.BoxTypeListFragment;
import com.mms.manage_my_stuff.ui.roomlist.RoomListFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ToolbarViewModel toolbarViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        toolbarViewModel = ViewModelProviders.of(this).get(ToolbarViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.setToolbarViewModel(toolbarViewModel);

        if (savedInstanceState == null) {
            RoomListFragment fragment = new RoomListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_left, fragment, RoomListFragment.TAG).commit();

            getActionBar().setDisplayHomeAsUpEnabled(false);
            updateToolbarTitle(getString(R.string.app_name));
        }
    }

    /** Shows the room detail fragment */
    public void showRoomDetails(Room room) {
        BoxTypeListFragment boxTypeListFragment = BoxTypeListFragment.forRoom(room.getId());
        BoxCountListFragment boxCountListFragment = BoxCountListFragment.forRoom(room.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("left_frame")
                .replace(R.id.fragment_container_left, boxTypeListFragment, null)
                .addToBackStack("right_frame")
                .replace(R.id.fragment_container_right, boxCountListFragment, null)
                .commit();

        getActionBar().setDisplayHomeAsUpEnabled(true);
        updateToolbarTitle(room.getRoomType() + " Contents");
    }

    public void showBoxDetails(Box box, String roomType) {
        BoxDetailsListFragment boxDetailsListFragment = BoxDetailsListFragment.forBox(box.getId(), roomType);
        EmptyFragment emptyFragment = EmptyFragment.forBox(box.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("left_frame")
                .replace(R.id.fragment_container_left, boxDetailsListFragment, null)
                .addToBackStack("right_frame")
                .replace(R.id.fragment_container_right, emptyFragment, null)
                .commit();

        getActionBar().setDisplayHomeAsUpEnabled(true);
        updateToolbarTitle("Box Contents");
    }

    public void updateToolbarTitle(String title) {

        toolbarViewModel.setTitle(title);
    }
}
