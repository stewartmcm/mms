package com.mms.manage_my_stuff;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.mms.manage_my_stuff.di.AppModule;
import com.mms.manage_my_stuff.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MmsApp extends Application implements HasActivityInjector, HasSupportFragmentInjector {

//    protected AndroidInjector<? extends DaggerApplication> applicationInjector()  {
//        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
//        appComponent.inject(this);
//        return appComponent;
//    }

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidActivityInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidFragmentInjector;

//    protected static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder().appModule(new AppModule(this)).build().inject(this);

//        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build;

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidFragmentInjector;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidActivityInjector;
    }
}
