package com.mms.manage_my_stuff;

import android.app.Application;

public class MmsApp extends Application {

//    protected AndroidInjector<? extends DaggerApplication> applicationInjector()  {
//        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
//        appComponent.inject(this);
//        return appComponent;
//    }

//    @Inject
//    DispatchingAndroidInjector<Activity> dispatchingAndroidActivityInjector;
//
//    @Inject
//    DispatchingAndroidInjector<Fragment> dispatchingAndroidFragmentInjector;

    private AppExecutors appExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        appExecutors = new AppExecutors();

//        DaggerAppComponent.builder().appModule(new AppModule(this)).build().inject(this);

//        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build;

    }

//    @Override
//    public AndroidInjector<Fragment> supportFragmentInjector() {
//        return dispatchingAndroidFragmentInjector;
//    }
//
//    @Override
//    public AndroidInjector<Activity> activityInjector() {
//        return dispatchingAndroidActivityInjector;
//    }
}
