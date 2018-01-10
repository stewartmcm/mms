package com.managemystuff.manage_my_stuff.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BaseLifeCycleViewModel implements LifecycleObserver {

    private CompositeSubscription lifecycleSubscriptions = new CompositeSubscription();

    //region Lifecycle Methods

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        lifecycleSubscriptions.clear();
    }

    //endregion

    public void setLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    public void subscribeOnLifecycle(Subscription subscription) {
        lifecycleSubscriptions.add(subscription);
    }
}
