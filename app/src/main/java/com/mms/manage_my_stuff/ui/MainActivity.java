package com.mms.manage_my_stuff.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ActivityMainBinding;
import com.mms.manage_my_stuff.models.Box;
import com.mms.manage_my_stuff.models.Room;
import com.mms.manage_my_stuff.ui.boxcount.BoxCountListFragment;
import com.mms.manage_my_stuff.ui.boxdetails.BoxDetailsListFragment;
import com.mms.manage_my_stuff.ui.boxtype.BoxTypeListFragment;
import com.mms.manage_my_stuff.ui.login.LoginActivity;
import com.mms.manage_my_stuff.ui.roomlist.RoomListFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ToolbarViewModel toolbarViewModel;
    RoomListFragment roomListFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        toolbarViewModel = ViewModelProviders.of(this).get(ToolbarViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.showOverflowMenu();

        binding.setToolbarViewModel(toolbarViewModel);

        if (savedInstanceState == null) {
            roomListFragment = new RoomListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_center, roomListFragment, RoomListFragment.TAG).commit();

            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            updateToolbarTitle(getString(R.string.app_name));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_menu_item:
                toolbarViewModel.logout();
                startActivity(new Intent(this, LoginActivity.class));

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Shows the room detail fragment */
    public void showRoomDetails(Room room) {
        BoxTypeListFragment boxTypeListFragment = BoxTypeListFragment.forRoom(room.getId());
        BoxCountListFragment boxCountListFragment = BoxCountListFragment.forRoom(room.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .remove(roomListFragment)
                .addToBackStack("left_frame")
                .replace(R.id.fragment_container_left, boxTypeListFragment, null)
                .addToBackStack("right_frame")
                .replace(R.id.fragment_container_right, boxCountListFragment, null)
                .commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void updateToolbarTitle(String title) {
        toolbarViewModel.setTitle(title);
    }
}
