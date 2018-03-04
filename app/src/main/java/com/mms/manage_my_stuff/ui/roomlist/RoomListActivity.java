package com.mms.manage_my_stuff.ui.roomlist;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.mms.manage_my_stuff.BaseActivity;
import com.mms.manage_my_stuff.BaseFragment;
import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ActivityRoomListBinding;
import com.mms.manage_my_stuff.events.StartFragmentEvent;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.models.Room;
import com.mms.manage_my_stuff.ui.ToolbarViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import rx.subscriptions.CompositeSubscription;

public class RoomListActivity extends BaseActivity {

//    private FirebaseAuth auth;
//    private DatabaseReference database;

    @Inject
    ToolbarViewModel toolBarViewModel;

    @Inject
    UnboundViewEventBus eventBus;

    private ActivityRoomListBinding binding;
    private ArrayList<String> defaultRooms = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();

    public static void newInstance(final Activity activity) {
        Intent intent = new Intent(activity, RoomListActivity.class);
        activity.startActivity(intent);
    }

    //region Lifecycle

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_room_list);
        toolBarViewModel.setLifecycle(getLifecycle());
        binding.setToolbarViewModel(toolBarViewModel);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //TODO: learn what this is used for
//        getSupportFragmentManager().findFragmentById(R.id.room_menu_fragment);

//        auth = FirebaseAuth.getInstance();
//        FirebaseUser user = auth.getCurrentUser();
//        String userId = user.getUid();
//        database = FirebaseDatabase.getInstance().getReference();
//
//        defaultRooms.add("Kitchen");
//        defaultRooms.add("Living Room");
//        defaultRooms.add("Dining Room");
//        defaultRooms.add("Bathroom");
//        defaultRooms.add("Master Bedroom");
//
//        for (int i = 0; i < defaultRooms.size(); i++) {
//            Room room = new Room(defaultRooms.get(i), null, 0, false);
//            rooms.add(room);
//        }
//        database.child("users").child(userId).setValue(rooms);
    }

    @Override
    protected void onStart() {
        super.onStart();

//        FirebaseUser currentUser = auth.getCurrentUser();

    }

    //endregion

    @Override
    protected CompositeSubscription registerUnboundViewEvents() {
        CompositeSubscription events = new CompositeSubscription();

        events.add(eventBus.toast(toolBarViewModel).subscribe(this::showToast));
        events.add(eventBus.snackbar(toolBarViewModel).subscribe(this::showSnackbar));
        events.add(eventBus.startFragment().subscribe(this::startFragment));

        return events;
    }

    private void updateToolbar(boolean showToolbar, String title, int navigationIcon) {
        if (!showToolbar) {
            toolBarViewModel.hideToolbar();
        } else {
            toolBarViewModel.showToolbar();
            toolBarViewModel.setTitle(title);
//            toolBarViewModel.showNavigationIcon(navigationIcon);
        }
    }

    public void startFragment(StartFragmentEvent event) {
        BaseFragment fragment = new RoomListFragment();
    }
}
