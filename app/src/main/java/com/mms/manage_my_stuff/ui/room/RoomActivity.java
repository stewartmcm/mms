package com.mms.manage_my_stuff.ui.room;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ActivityRoomBinding;
import com.mms.manage_my_stuff.ui.ToolbarViewModel;
import com.mms.manage_my_stuff.ui.roomlist.RoomItemViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class RoomActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Inject
    ToolbarViewModel toolBarViewModel;

    @Inject
    RoomViewModel roomViewModel;

    private RoomItemViewModel roomItemViewModel;

    private ActivityRoomBinding binding;

    public static void newInstance(final Activity activity) {
        Intent intent = new Intent(activity, RoomActivity.class);
        activity.startActivity(intent);
    }

    //region Lifecycle

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_room);
        toolBarViewModel.setLifecycle(getLifecycle());
        binding.setToolbarViewModel(toolBarViewModel);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        roomItemViewModel = ViewModelProviders.of(this).get(RoomItemViewModel.class);

        final Observer<String> currentRoomObserver = new Observer<String>() {
            @Override
            public void onChanged(String roomTitle) {
                Log.i("RoomActivity", roomTitle);
            }
        };

        roomItemViewModel.getCurrentRoomTitle().observe(this,currentRoomObserver);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    //endregion

    private void updateToolbar(boolean showToolbar, String title, int navigationIcon) {
        if (!showToolbar) {
            toolBarViewModel.hideToolbar();
        } else {
            toolBarViewModel.showToolbar();
            toolBarViewModel.setTitle(title);
//            toolBarViewModel.showNavigationIcon(navigationIcon);
        }
    }

//    public void startFragment(StartFragmentEvent event) {
//        BaseFragment fragment = new BoxTypeListFragment();
//    }
}
