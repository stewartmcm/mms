package com.mms.manage_my_stuff;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import com.mms.manage_my_stuff.events.SnackbarEvent;
import com.mms.manage_my_stuff.events.StartActivityEvent;

import dagger.android.support.AndroidSupportInjection;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment extends Fragment{

    private CompositeSubscription lifecycleSubscriptions = new CompositeSubscription();

    private int NO_FLAGS = 0;

    @Nullable
    protected abstract CompositeSubscription registerUnboundViewEvents();

    //region Lifecycle


    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        subscribeToEventBus();
        super.onResume();
    }

    @Override
    public void onPause() {
        lifecycleSubscriptions.clear();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //endregion

    protected void startActivity(StartActivityEvent event) {
        if (event.isLaunchingExternalApplication()) {
            String action = event.getIntentAction();
            Intent intent = new Intent(action);
            intent.setData(Uri.parse(event.getIntentParameter()));
            startActivity(intent);
        } else if (event.getIntentFlags() == NO_FLAGS) {
            startActivity(new Intent(getContext(), event.getStartActivity()));
        } else {
            startActivity(new Intent(this.getActivity(), event.getStartActivity()).setFlags(event.getIntentFlags()));
        }
    }

//    protected void startFragment(Class<? extends BaseFragment> fragmentToStart) {
//        StartFragmentEvent event = StartFragmentEvent.build().fragmentName(fragmentToStart);
//        eventBus.send(event);
//    }

    protected void subscribeOnLifecycle(Subscription subscription) {
        lifecycleSubscriptions.add(subscription);
    }

    protected void showSnackbar(SnackbarEvent event) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), event.getTextId(), event.getLength()).show();
    }

    private void subscribeToEventBus() {
        CompositeSubscription eventsSubscription = registerUnboundViewEvents();
        if (eventsSubscription != null) {
            lifecycleSubscriptions.add(eventsSubscription);
        }
    }
}