package com.mms.manage_my_stuff.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ActivityMainBinding;
import com.mms.manage_my_stuff.events.UnboundViewEventBus;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends BaseActivity {

    SharedPreferences prefs;
    private FirebaseAuth auth;

    @Inject
    ToolbarViewModel toolBarViewModel;

    @Inject
    UnboundViewEventBus eventBus;

//    private RoomMenuFragment roomMenuFragment = null;
    private ActivityMainBinding binding;

    public static void newInstance(final Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    //region Lifecycle

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        toolBarViewModel.setLifecycle(getLifecycle());
        binding.setViewModel(toolBarViewModel);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        getSupportFragmentManager().findFragmentById(R.id.room_menu);

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
}
