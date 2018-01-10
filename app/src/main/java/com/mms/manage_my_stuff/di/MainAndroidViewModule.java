package com.mms.manage_my_stuff.di;

import com.mms.manage_my_stuff.ui.MainActivity;
import com.mms.manage_my_stuff.ui.RoomMenuFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainAndroidViewModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract RoomMenuFragment bindRoomMenuFragment();
}
