package com.managemystuff.manage_my_stuff.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.managemystuff.manage_my_stuff.events.DialogEvent;
import com.managemystuff.manage_my_stuff.events.FinishActivityEvent;
import com.managemystuff.manage_my_stuff.events.SnackbarEvent;
import com.managemystuff.manage_my_stuff.events.StartActivityEvent;
import com.managemystuff.manage_my_stuff.events.ToastEvent;

import java.util.List;

import dagger.android.support.DaggerAppCompatActivity;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private CompositeSubscription lifecycleSubscriptions = new CompositeSubscription();

    private int NO_FLAGS = 0;

    protected abstract CompositeSubscription registerUnboundViewEvents();

    //region Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        subscribeToEventBus();
        super.onResume();
    }

    @Override
    protected void onPause() {
        lifecycleSubscriptions.clear();
        super.onPause();
    }

    //endregion

    protected void subscribeOnLifecycle(Subscription subscription) {
        lifecycleSubscriptions.add(subscription);
    }

    protected void showToast(ToastEvent event) {
        Toast.makeText(this, event.getToastText(), event.getToastLength()).show();
    }

    protected void showSnackbar(SnackbarEvent event) {
        Snackbar.make(findViewById(android.R.id.content), event.getTextId(), event.getLength()).show();
    }

    protected void startActivity(StartActivityEvent event) {
        if (event.isLaunchingExternalApplication()) {
            String action = event.getIntentAction();
            Intent intent = new Intent(action);
            intent.setData(Uri.parse(event.getIntentParameter()));
            startActivity(intent);
        } else if (event.getIntentFlags() == NO_FLAGS) {
            startActivity(new Intent(this, event.getStartActivity()));
        } else {
            startActivity(new Intent(this, event.getStartActivity()).setFlags(event.getIntentFlags()));
        }
    }

    private void subscribeToEventBus() {
        CompositeSubscription eventsSubscription = registerUnboundViewEvents();
        if (eventsSubscription != null) {
            lifecycleSubscriptions.add(eventsSubscription);
        }
    }

    protected void finishActivity(FinishActivityEvent event) {
        this.finish();
    }

    protected void showDialog(DialogEvent event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(event.getDialogTitle());

        if (event.hasList()) {
            createDialogButtonList(event, builder);
        } else if (event.hasBody()) {
            builder.setMessage(event.getDialogBody());
        }

        builder.create().show();
    }

    private void createDialogButtonList(DialogEvent event, AlertDialog.Builder builder) {
        List<Integer> buttonIds = event.getDialogList();
        CharSequence[] buttons = new CharSequence[buttonIds.size()];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = getString(buttonIds.get(i));
        }
        builder.setItems(buttons, event.getOnClickListener());
    }
}
