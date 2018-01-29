package com.mms.manage_my_stuff.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mms.manage_my_stuff.BaseActivity;
import com.mms.manage_my_stuff.BaseFragment;
import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ActivityRoomBinding;
import com.mms.manage_my_stuff.events.StartFragmentEvent;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import rx.subscriptions.CompositeSubscription;

public class RoomActivity extends BaseActivity {

    private FirebaseAuth auth;

    @Inject
    ToolbarViewModel toolBarViewModel;

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

        //TODO: learn what this is used for
//        getSupportFragmentManager().findFragmentById(R.id.box_selection_fragment);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = auth.getCurrentUser();

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
        BaseFragment fragment = new BoxTypeListFragment();
    }
}
