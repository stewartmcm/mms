package com.mms.manage_my_stuff;

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

    protected void subscribeOnLifecycle(Subscription subscription) {
        lifecycleSubscriptions.add(subscription);
    }
}
