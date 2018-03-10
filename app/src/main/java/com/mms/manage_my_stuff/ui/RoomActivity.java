package com.mms.manage_my_stuff.ui;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.mms.manage_my_stuff.BaseActivity;
import com.mms.manage_my_stuff.BaseFragment;
import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ActivityRoomBinding;
import com.mms.manage_my_stuff.events.StartFragmentEvent;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;
import com.mms.manage_my_stuff.ui.roomlist.RoomItemViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import rx.subscriptions.CompositeSubscription;

public class RoomActivity extends BaseActivity {

    private FirebaseAuth auth;

    @Inject
    ToolbarViewModel toolBarViewModel;

    @Inject
    RoomViewModel roomViewModel;

    private RoomItemViewModel roomItemViewModel;

    @Inject
    UnboundViewEventBus eventBus;

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

    @Override
    protected CompositeSubscription registerUnboundViewEvents() {
        CompositeSubscription events = new CompositeSubscription();

        events.add(eventBus.toast(toolBarViewModel).subscribe(this::showToast));
        events.add(eventBus.snackbar(toolBarViewModel).subscribe(this::showSnackbar));
        events.add(eventBus.startFragment().subscribe(this::startFragment));
        events.add(eventBus.startActivity(BoxCountListViewModel.class).subscribe(this::startActivity));
        events.add(eventBus.startActivity(BoxTypeListViewModel.class).subscribe(this::startActivity));
//        events.add(eventBus.finishActivity(BoxCountListViewModel.class).subscribe(this::finishActivity));
        events.add(eventBus.finishActivity(BoxTypeListViewModel.class).subscribe(this::finishActivity));

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
        BaseFragment fragment = new BoxTypeListFragment();
    }
}
