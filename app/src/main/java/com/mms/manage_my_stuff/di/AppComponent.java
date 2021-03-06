package com.mms.manage_my_stuff.di;

import com.mms.manage_my_stuff.MmsApp;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        MainAndroidViewModule.class})
public interface AppComponent extends AndroidInjector<MmsApp> {
}
