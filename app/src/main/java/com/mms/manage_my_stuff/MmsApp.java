package com.mms.manage_my_stuff;

import android.app.Application;

public class MmsApp extends Application {

    private AppExecutors appExecutors;

    @Override
    public void onCreate() {
        super.onCreate();
        appExecutors = new AppExecutors();
    }
}
