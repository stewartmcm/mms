package com.mms.manage_my_stuff.ui.boxdetails;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.mms.manage_my_stuff.R;
import com.mms.manage_my_stuff.databinding.ActivityBoxDetailsBinding;
import com.mms.manage_my_stuff.ui.ToolbarViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import rx.subscriptions.CompositeSubscription;

public class BoxDetailsActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Inject
    ToolbarViewModel toolBarViewModel;

    private ActivityBoxDetailsBinding binding;

    public static void newInstance(final Activity activity) {
        Intent intent = new Intent(activity, BoxDetailsActivity.class);
        activity.startActivity(intent);
    }

    //region Lifecycle

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_box_details);
        toolBarViewModel.setLifecycle(getLifecycle());
        binding.setToolbarViewModel(toolBarViewModel);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //TODO: learn what this is used for
//        getSupportFragmentManager().findFragmentById(R.id.box_contents_fragment);

//        auth = FirebaseAuth.getInstance();
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


}
